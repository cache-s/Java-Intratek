package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class NsStat
{
    public float active;
    public float idle;
    @SerializedName("out_active")
    public float outActive;
    @SerializedName("out_idle")
    public float outIdle;
    @SerializedName("nslog_norm")
    public float nsLogNorm;
}
