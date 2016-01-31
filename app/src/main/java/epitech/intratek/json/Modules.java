package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Modules
{
    public String title;
    @SerializedName("title_link")
    public String titleLink;
    @SerializedName("timeline_start")
    public String timelineStart;
    @SerializedName("timeline_end")
    public String timelineEnd;
    @SerializedName("timeline_barre")
    public String timelineBarre;
    @SerializedName("date_inscription")
    public String registerDate;
}
