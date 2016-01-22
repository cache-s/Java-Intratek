package epitech.intratek.beans;


import com.google.gson.annotations.SerializedName;

public class Stages
{
    private String company;
    private String link;
    @SerializedName("timeline_start")
    private String timelineStart;
    @SerializedName("timeline_end")
    private String timelineEnd;
    @SerializedName("can_note")
    private boolean canNote;
    @SerializedName("company_can_note")
    private boolean companyCanNote;
    private String status;
    private boolean mandatory;
}
