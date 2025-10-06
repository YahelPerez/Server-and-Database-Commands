package model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the "versions" object, containing the link
 * to the Google Scholar page listing all versions of an article.
 */
public class Versions {

    @SerializedName("link")
    private String link;

    public String getLink() {
        return link;
    }
}