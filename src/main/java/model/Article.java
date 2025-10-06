package model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Article {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("publication_info")
    private PublicationInfo publicationInfo;
    @SerializedName("snippet")
    private String snippet;
    @SerializedName("inline_links")
    private InlineLinks inlineLinks;

    // This is a placeholder as the main search doesn't provide keywords per article
    private List<String> keywords = List.of();

    // Helper to get citation count from nested object
    public int getCitedByCount() {
        if (inlineLinks != null && inlineLinks.getCitedBy() != null) {
            return inlineLinks.getCitedBy().getTotal();
        }
        return 0;
    }

    // --- Getters ---
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public PublicationInfo getPublicationInfo() { return publicationInfo; }
    public String getSnippet() { return snippet; }
    public InlineLinks getInlineLinks() { return inlineLinks; }
    public List<String> getKeywords() { return keywords; }

    @Override
    public String toString() {
        return title;
    }
}