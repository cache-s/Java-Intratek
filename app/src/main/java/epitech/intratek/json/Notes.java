package epitech.intratek.json;
import com.google.gson.annotations.SerializedName;

public class Notes
{
    public String title;
    @SerializedName("title_link")
    public String titleLink;
    public String name;
    @SerializedName("noteur")
    public String marker;
}
