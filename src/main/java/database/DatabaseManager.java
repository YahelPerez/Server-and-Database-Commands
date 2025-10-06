package database;

import model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/scholar_app";
    private static final String USER = "postgres";
    private static final String PASSWORD = "8hUwen@7163"; // Make sure this is your real password!

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error connecting to the PostgreSQL database: " + e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS articles ("
                + " id SERIAL PRIMARY KEY,"
                + " title TEXT NOT NULL,"
                + " authors TEXT,"
                + " publication_date TEXT,"
                + " abstract TEXT,"
                + " link TEXT UNIQUE,"
                + " keywords TEXT,"
                + " cited_by INTEGER"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            if (conn != null) {
                stmt.execute(sql);
                System.out.println("PostgreSQL database and 'articles' table have been initialized successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing the database: " + e.getMessage());
        }
    }

    public static void insertArticle(Article article) {
        // --- UPDATED SQL statement to include all fields ---
        String sql = "INSERT INTO articles(title, authors, publication_date, abstract, link, keywords, cited_by) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?) "
                + "ON CONFLICT(link) DO NOTHING";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn != null) {
                String summary = (article.getPublicationInfo() != null) ? article.getPublicationInfo().getSummary() : "";

                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, summary); // Use the summary for the 'authors' field
                pstmt.setString(3, summary); // ALSO use the summary for the 'publication_date' field
                pstmt.setString(4, article.getSnippet());
                pstmt.setString(5, article.getLink());

                // Since keywords are not provided by the API, we insert null.
                pstmt.setString(6, null);

                pstmt.setInt(7, article.getCitedByCount());

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error inserting article '" + article.getTitle() + "': " + e.getMessage());
        }
    }
}