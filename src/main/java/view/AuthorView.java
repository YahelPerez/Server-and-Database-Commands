package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the View in the MVC pattern.
 * This class is responsible for the user interface. It extends JFrame to act as a window.
 */
public class AuthorView extends JFrame {

    // UI components that the user will interact with.
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultsArea;

    /**
     * Constructor to set up the user interface.
     */
    public AuthorView() {
        // --- Window Setup ---
        this.setTitle("Google Scholar Author Search");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // --- Top Panel for Search Input ---
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search for an author:"));

        searchField = new JTextField(20);
        topPanel.add(searchField);

        searchButton = new JButton("Search");
        topPanel.add(searchButton);

        // --- Center Area for Results ---
        resultsArea = new JTextArea();
        resultsArea.setEditable(false); // User should not be able to type here.
        JScrollPane scrollPane = new JScrollPane(resultsArea); // Add a scrollbar.

        // --- Add Panels to the Window (This is the crucial part!) ---
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // --- Getters ---
    // These methods allow the Controller to access the UI components.

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextArea getResultsArea() {
        return resultsArea;
    }
}