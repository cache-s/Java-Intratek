package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Modules
{
    private String title;
    @SerializedName("title_link")
    private String titleLink;
    @SerializedName("timeline_start")
    private String timelineStart;
    @SerializedName("timeline_end")
    private String timelineEnd;
    @SerializedName("timeline_barre")
    private String timelineBarre;
    @SerializedName("date_inscription")
    private String registerDate;
}
