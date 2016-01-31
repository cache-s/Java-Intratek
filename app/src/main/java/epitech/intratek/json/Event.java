package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dardaxe on 22/01/2016.
 */
public class Event
{
    @SerializedName("id_event_failed")
    public String idEventFailed;
    @SerializedName("id_user")
    public String idUser;
    public String begin;
    @SerializedName("id_activite_failed")
    public String idActivityFailed;
}
