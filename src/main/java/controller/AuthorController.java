package controller;

import service.ScholarApiService;
import view.AuthorView;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class AuthorController {

    private final AuthorView view;
    private final ScholarApiService apiService;

    public AuthorController(AuthorView view) {
        this.view = view;
        this.apiService = new ScholarApiService();
        this.view.getSearchButton().addActionListener(new SearchListener());
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String authorName = view.getSearchField().getText();
            if (authorName == null || authorName.trim().isEmpty()) {
                view.getResultsArea().setText("Please enter an author's name to search.");
                return;
            }

            // DEBUG: Print to console when the button is clicked.
            System.out.println("Search button clicked for: " + authorName);

            view.getResultsArea().setText("Searching, please wait...");

            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    // DEBUG: Print a message just before making the API call.
                    System.out.println("Calling API service...");
                    return apiService.searchAuthor(authorName);
                }

                @Override
                protected void done() {
                    try {
                        String jsonResponse = get();
                        // DEBUG: Print a message when the API call is successful.
                        System.out.println("API call successful. Response received.");
                        view.getResultsArea().setText(jsonResponse);
                    } catch (InterruptedException | ExecutionException ex) {
                        // --- IMPROVED ERROR HANDLING ---
                        // This will print the full error details to the console.
                        System.err.println("An error occurred during the API call:");
                        ex.printStackTrace();

                        // Also, display a more informative message in the UI.
                        Throwable cause = ex.getCause(); // Get the original exception
                        view.getResultsArea().setText("An error occurred: \n" + (cause != null ? cause.getMessage() : ex.getMessage()));
                    }
                }
            };

            worker.execute();
        }
    }
}