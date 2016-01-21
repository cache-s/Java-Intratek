package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class History
{
private String title;
    private User user;
    private String content;
    private String date;
    private String id;
    private String visible;
    @SerializedName("id_activite")
    private String activityId;
    @SerializedName("class")
    private String type;
}

