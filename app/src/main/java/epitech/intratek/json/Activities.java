package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Activities
{
    public String title;
    public String module;
    @SerializedName("module_link")
    public String moduleLink;
    @SerializedName("title_link")
    public String titleLink;
    @SerializedName("timeline_start")
    public String timelineStart;
    @SerializedName("timeline_end")
    public String timelineEnd;
    @SerializedName("timeline_barre")
    public String timelineBarre;
    @SerializedName("date_inscription")
    public String subDate;
    @SerializedName("salle")
    public String room;
    @SerializedName("intervenant")
    public String speaker;
    public String token;
    @SerializedName("token_link")
    public String tokenLink;
    @SerializedName("register_link")
    public String registerLink;
}
