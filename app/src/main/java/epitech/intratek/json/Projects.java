package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Projects
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
    @SerializedName("date_insciption")
    public boolean dateInscription;
    @SerializedName("id_activite")
    public String activityId;
    @SerializedName("soutenance_name")
    public boolean defenseName;
    @SerializedName("soutenance_link")
    public boolean defenseLink;
    @SerializedName("soutenance_date")
    public boolean defenseDate;
    @SerializedName("soutenance_salle")
    public boolean defenseRoom;
}
