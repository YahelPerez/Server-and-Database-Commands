package model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the "publication_info" object within an Article.
 */

public  class PublicationInfo {

    @SerializedName("summary")
    private String summary;

    // ---Getter---
    public String getSummary(){
        return summary;
    }
}