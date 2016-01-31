package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Susies
{
    public String title;
    @SerializedName("creneau_link")
    public String slotLink;
    @SerializedName("timeline_start")
    public String timelineStart;
    @SerializedName("timeline_barre")
    public String timelineBarre;
    @SerializedName("timeline_end")
    public String timelineEnd;
    @SerializedName("salle")
    public String room;
    @SerializedName("intervenant")
    public String speaker;
    @SerializedName("etat")
    public String state;
    public String type;
}
