package app;

import com.formdev.flatlaf.FlatDarculaLaf; // Import the Look and Feel
import controller.AuthorController;
import database.DatabaseManager;
import view.AuthorView;
import javax.swing.SwingUtilities;

public class Application {

    public static void main(String[] args) {
        // --- NEW: Set the FlatLaf Darcula dark theme ---
        // This line must be called before any Swing components are created.
        FlatDarculaLaf.setup();

        // Initialize the database and create tables if they don't exist.
        DatabaseManager.initializeDatabase();

        SwingUtilities.invokeLater(() -> {
            // 1. Create the View
            AuthorView view = new AuthorView();
            // 2. Create the Controller and link it to the View
            new AuthorController(view);
            // 3. Make the View visible
            view.setVisible(true);
        });
    }
}