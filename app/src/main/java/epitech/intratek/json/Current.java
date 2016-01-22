package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Current
{
    @SerializedName("credits_min")
    public String creditsMin;
    @SerializedName("credits_norm")
    public String creditsNorm;
    @SerializedName("credits_obj")
    public String creditsObj;
    @SerializedName("nslog_min")
    public String nslogMin;
    @SerializedName("nslog_norm")
    public String nslogNorm;
    public String credits;
    public String grade;
    public String cycle;
    @SerializedName("code_module")
    public String codeModule;
    @SerializedName("current_cycle")
    public String currentCycle;
    @SerializedName("semester_code")
    public String semesterCode;
    @SerializedName("semester_num")
    public String semesterNum;
    @SerializedName("active_log")
    public String activeLog;
}
