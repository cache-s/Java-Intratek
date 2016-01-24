package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class Message
{
    public String title;
    @SerializedName("user")
    public Sender sender;
    public String content;
    public String date;
}
