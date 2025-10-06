package model;
import com.google.gson.annotations.SerializedName;
public class CitedBy {
    @SerializedName("total")
    private int total;
    public int getTotal() { return total; }
}