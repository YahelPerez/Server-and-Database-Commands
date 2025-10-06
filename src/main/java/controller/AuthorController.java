package controller;

import com.google.gson.Gson;
import database.DatabaseManager;
import model.Article;
import model.ScholarApiResponse;
import service.ScholarApiService;
import view.AuthorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AuthorController {

    private final AuthorView view;
    private final ScholarApiService apiService;
    private final Gson gson;

    public AuthorController(AuthorView view) {
        this.view = view;
        this.apiService = new ScholarApiService();
        this.gson = new Gson();

        // Add listeners for all three buttons
        this.view.getSearchByAuthorButton().addActionListener(new SearchListener(this::searchByAuthorOrId));
        this.view.getSearchByTopicButton().addActionListener(new SearchListener(this::searchByTopic));

        this.view.getClearButton().addActionListener(e -> {
            view.getSearchField().setText("");
            view.getResultsList().setModel(new DefaultListModel<>());
        });
    }

    // --- Search Logic Methods ---
    private String searchByAuthorOrId() throws Exception {
        String searchText = view.getSearchField().getText();
        if (searchText.matches("^[a-zA-Z0-9_-]{12}$")) {
            return apiService.searchAuthorById(searchText);
        } else {
            return apiService.searchAuthorByName(searchText);
        }
    }

    private String searchByTopic() throws Exception {
        String searchText = view.getSearchField().getText();
        return apiService.searchByTopic(searchText);
    }


    /**
     * A reusable ActionListener for search actions.
     */
    class SearchListener implements ActionListener {
        private final SearchAction searchAction;

        public SearchListener(SearchAction searchAction) {
            this.searchAction = searchAction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = view.getSearchField().getText();
            if (searchText == null || searchText.trim().isEmpty()) {
                view.getResultsList().setModel(new DefaultListModel<>());
                return;
            }

            view.startLoadingAnimation();
            view.getSearchByAuthorButton().setEnabled(false);
            view.getSearchByTopicButton().setEnabled(false);
            view.getClearButton().setEnabled(false);

            SwingWorker<ScholarApiResponse, Void> worker = new SwingWorker<>() {
                @Override
                protected ScholarApiResponse doInBackground() throws Exception {
                    Thread.sleep(1000); // Artificial delay
                    String jsonResponse = searchAction.execute();
                    return gson.fromJson(jsonResponse, ScholarApiResponse.class);
                }

                @Override
                protected void done() {
                    try {
                        ScholarApiResponse response = get();
                        DefaultListModel<Article> listModel = new DefaultListModel<>();

                        if (response != null && response.getOrganicResults() != null) {
                            List<Article> articles = response.getOrganicResults();

                            // --- SAVE TO DATABASE ---
                            int articlesToSave = Math.min(articles.size(), 3); // Save up to 3 articles
                            System.out.println("--- Saving top " + articlesToSave + " articles to database ---");
                            for (int i = 0; i < articlesToSave; i++) {
                                Article article = articles.get(i);
                                DatabaseManager.insertArticle(article);
                                System.out.println("Saved: " + article.getTitle());
                            }
                            System.out.println("------------------------------------");

                            // Populate the list model for the UI
                            for (Article article : articles) {
                                listModel.addElement(article);
                            }
                        }
                        view.getResultsList().setModel(listModel);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(view, "An error occurred: \n" + ex.getCause().getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        view.stopLoadingAnimation();
                        view.getSearchByAuthorButton().setEnabled(true);
                        view.getSearchByTopicButton().setEnabled(true);
                        view.getClearButton().setEnabled(true);
                    }
                }
            };
            worker.execute();
        }
    }
}