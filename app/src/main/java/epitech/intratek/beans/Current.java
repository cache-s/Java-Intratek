package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Current
{
    @SerializedName("credits_min")
    private String creditsMin;
    @SerializedName("credits_norm")
    private String creditsNorm;
    @SerializedName("credits_obj")
    private String creditsObj;
    @SerializedName("nslog_min")
    private String nslogMin;
    @SerializedName("nslog_norm")
    private String nslogNorm;
    private String credits;
    private String grade;
    private String cycle;
    @SerializedName("code_module")
    private String codeModule;
    @SerializedName("current_cycle")
    private String currentCycle;
    @SerializedName("semester_code")
    private String semesterCode;
    @SerializedName("semester_num")
    private String semesterNum;
    @SerializedName("active_log")
    private String activeLog;
}
