package view;

import model.Article;
import ui.ArticleRenderer;
import ui.GradientPanel;
import ui.LoadingSpinner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthorView extends JFrame {

    // --- UI Component Declarations ---
    private final JTextField searchField;
    private final JButton searchByAuthorButton;
    private final JButton searchByTopicButton;
    private final JButton clearButton;
    private final JList<Article> resultsList;
    private final CardLayout cardLayout;
    private final JPanel resultsPanel;
    private final LoadingSpinner loadingSpinner;

    public static final String RESULTS_CARD = "Results";
    public static final String LOADING_CARD = "Loading";

    public AuthorView() {
        this.setTitle("Google Scholar Explorer");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center the window on screen

        // --- NEW: Apply gradient to the entire window's content pane ---
        Color darkPurple = new Color(28, 0, 51);
        Color midPurple = new Color(51, 0, 102);
        this.setContentPane(new GradientPanel(darkPurple, midPurple));
        this.setLayout(new BorderLayout());

        // --- Redesigned Top Panel ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Make the panel transparent to show the gradient behind it
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Title Label
        JLabel titleLabel = new JLabel("Google Scholar Explorer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // 2. Search Controls Panel
        JPanel searchControlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchControlsPanel.setOpaque(false);
        searchField = new JTextField(30);
        searchControlsPanel.add(searchField);

        // 3. Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        searchByAuthorButton = new JButton("Search Author / ID");
        searchByTopicButton = new JButton("Search Topic");
        clearButton = new JButton("Clear");
        buttonPanel.add(searchByAuthorButton);
        buttonPanel.add(searchByTopicButton);
        buttonPanel.add(clearButton);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(searchControlsPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // --- Main Panel with CardLayout to switch between results and loading ---
        cardLayout = new CardLayout();
        resultsPanel = new JPanel(cardLayout);
        resultsPanel.setOpaque(false);

        // Card 1: Results List (now fully transparent)
        resultsList = new JList<>();
        resultsList.setCellRenderer(new ArticleRenderer());
        resultsList.setOpaque(false);
        resultsList.setBackground(new Color(0,0,0,0)); // Ensure background is transparent
        JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Card 2: The custom loading spinner
        loadingSpinner = new LoadingSpinner();
        JPanel loadingPanel = new JPanel(new GridBagLayout());
        loadingPanel.setOpaque(false);
        loadingPanel.add(loadingSpinner);

        resultsPanel.add(scrollPane, RESULTS_CARD);
        resultsPanel.add(loadingPanel, LOADING_CARD);

        // Add all components to the main frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(resultsPanel, BorderLayout.CENTER);

        // --- Mouse Click Functionality (no changes) ---
        resultsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Article selectedArticle = resultsList.getSelectedValue();
                    if (selectedArticle != null) {
                        String urlToOpen = null;
                        if (selectedArticle.getInlineLinks() != null &&
                                selectedArticle.getInlineLinks().getVersions() != null &&
                                selectedArticle.getInlineLinks().getVersions().getLink() != null) {
                            urlToOpen = selectedArticle.getInlineLinks().getVersions().getLink();
                        } else if (selectedArticle.getLink() != null) {
                            urlToOpen = selectedArticle.getLink();
                        }
                        if (urlToOpen != null) {
                            try {
                                Desktop.getDesktop().browse(new URI(urlToOpen));
                            } catch (IOException | URISyntaxException ex) {
                                System.err.println("Could not open link: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });
    }

    // --- Public methods for the Controller ---

    public void startLoadingAnimation() {
        loadingSpinner.start();
        cardLayout.show(resultsPanel, LOADING_CARD);
    }

    public void stopLoadingAnimation() {
        loadingSpinner.stop();
        cardLayout.show(resultsPanel, RESULTS_CARD);
    }

    // --- Getters for UI components ---
    public JTextField getSearchField() { return searchField; }
    public JButton getSearchByAuthorButton() { return searchByAuthorButton; }
    public JButton getSearchByTopicButton() { return searchByTopicButton; }
    public JButton getClearButton() { return clearButton; }
    public JList<Article> getResultsList() { return resultsList; }
}