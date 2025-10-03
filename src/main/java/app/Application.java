package app;

import controller.AuthorController; // Import the controller
import view.AuthorView;
import javax.swing.SwingUtilities;

/**
 * Main class to run the application.
 */
public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Create the View
            AuthorView view = new AuthorView();

            // 2. Create the Controller and link it to the View
            new AuthorController(view); // This line is new/updated

            // 3. Make the View visible
            view.setVisible(true);
        });
    }
}