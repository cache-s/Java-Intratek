package epitech.intratek.json;


import com.google.gson.annotations.SerializedName;

public class Stages
{
    public String company;
    public String link;
    @SerializedName("timeline_start")
    public String timelineStart;
    @SerializedName("timeline_end")
    public String timelineEnd;
    @SerializedName("can_note")
    public boolean canNote;
    @SerializedName("company_can_note")
    public boolean companyCanNote;
    public String status;
    public boolean mandatory;
}
