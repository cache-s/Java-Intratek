package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Activities
{
    private String title;
    private String module;
    @SerializedName("module_link")
    private String moduleLink;
    @SerializedName("title_link")
    private String titleLink;
    @SerializedName("timeline_start")
    private String timelineStart;
    @SerializedName("timeline_end")
    private String timelineEnd;
    @SerializedName("timeline_barre")
    private String timelineBarre;
    @SerializedName("date_inscription")
    private String subDate;
    @SerializedName("salle")
    private String room;
    @SerializedName("intervenant")
    private String speaker;
    private String token;
    @SerializedName("token_link")
    private String tokenLink;
    @SerializedName("register_link")
    private String registerLink;
}
