package ui;

import model.Article;
import javax.swing.*;
import java.awt.*;

public class ArticleRenderer extends JPanel implements ListCellRenderer<Article> {

    private final JLabel lblTitle = new JLabel();
    private final JLabel lblSummary = new JLabel();

    public ArticleRenderer() {
        // Make the panel transparent
        setOpaque(false);
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSummary.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSummary.setForeground(Color.LIGHT_GRAY);

        add(lblTitle, BorderLayout.NORTH);
        add(lblSummary, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Article> list, Article article, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        lblTitle.setText("<html><body style='width: 500px;'>" + article.getTitle() + "</body></html>");
        if (article.getPublicationInfo() != null) {
            lblSummary.setText(article.getPublicationInfo().getSummary());
        }

        // Change title color on selection for better visibility
        if (isSelected) {
            lblTitle.setForeground(Color.WHITE);
        } else {
            lblTitle.setForeground(new Color(173, 216, 230)); // Light blue for unselected links
        }

        return this;
    }
}