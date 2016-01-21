package epitech.intratek.beans;
import com.google.gson.annotations.SerializedName;

public class Notes
{
    private String title;
    @SerializedName("title_link")
    private String titleLink;
    private String name;
    @SerializedName("noteur")
    private String marker;
}
