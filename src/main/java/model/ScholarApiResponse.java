package model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Represents the top-level structure of the JSON response from the Google Scholar API.
 */

public class ScholarApiResponse {

    @SerializedName("organic_results")
    private List<Article> organicResults;

    // --- Getter ---
    public List<Article> getOrganicResults() {
        return organicResults;
    }
}