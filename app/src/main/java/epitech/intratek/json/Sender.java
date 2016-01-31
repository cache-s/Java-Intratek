package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class Sender
{
    public String picture;
    @SerializedName("title")
    public String senderName;
    @SerializedName("url")
    public String senderLink;
}
