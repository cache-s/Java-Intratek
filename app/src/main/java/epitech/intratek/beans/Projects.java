package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Projects
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
    @SerializedName("date_insciption")
    private String dateInscription;
    @SerializedName("id_activite")
    private String activityId;
    @SerializedName("soutenance_name")
    private String defenseName;
    @SerializedName("soutenance_link")
    private String defenseLink;
    @SerializedName("soutenance_date")
    private String defenseDate;
    @SerializedName("soutenance_salle")
    private String defenseRoom;
}
