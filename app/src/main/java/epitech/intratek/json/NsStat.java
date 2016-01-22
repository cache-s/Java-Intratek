package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class NsStat
{
    public int active;
    public int idle;
    @SerializedName("out_active")
    public int outActive;
    @SerializedName("out_idle")
    public int outIdle;
    @SerializedName("nslog_norm")
    public int nsLogNorm;
}
