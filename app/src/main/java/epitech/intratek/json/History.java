package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class History
{
    public String title;
    public User user;
    public String content;
    public String date;
    public String id;
    public String visible;
    @SerializedName("id_activite")
    public String activityId;
    @SerializedName("class")
    public String type;
}

