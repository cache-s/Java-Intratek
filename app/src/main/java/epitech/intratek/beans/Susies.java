package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Susies
{
    private String title;
    @SerializedName("creneau_link")
    private String slotLink;
    @SerializedName("timeline_start")
    private String timelineStart;
    @SerializedName("timeline_barre")
    private String timelineBarre;
    @SerializedName("timeline_end")
    private String timelineEnd;
    @SerializedName("salle")
    private String room;
    @SerializedName("intervenant")
    private String speaker;
    @SerializedName("etat")
    private String state;
    private String type;
}
