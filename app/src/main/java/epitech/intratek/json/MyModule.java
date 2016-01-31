package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dardaxe on 24/01/2016.
 */
public class MyModule
{
    @SerializedName("scolaryear")
    public int scolarYear;
    @SerializedName("id_user_history")
    public String idUserHistory;
    @SerializedName("codemodule")
    public String codeModule;
    @SerializedName("codeinstance")
    public String codeInstance;
    public String title;
    @SerializedName("id_instance")
    public String idInstance;
    @SerializedName("date_ins")
    public String dateIns;
    public String cycle;
    public String grade;
    public String credits;
    public String flags;
    public float barrage;
    @SerializedName("instance_id")
    public String instandeId;
    @SerializedName("module_rating")
    public String moduleRating;
    public float semester;
}
