package model;
import com.google.gson.annotations.SerializedName;
public class InlineLinks {
    @SerializedName("versions")
    private Versions versions;
    @SerializedName("cited_by")
    private CitedBy citedBy; // Add this
    public Versions getVersions() { return versions; }
    public CitedBy getCitedBy() { return citedBy; } // Add this
}