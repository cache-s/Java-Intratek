package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Mark
{
    @SerializedName("scolaryear")
    public int scolarYear;
    @SerializedName("codemodule")
    public String codeModule;
    @SerializedName("titlemodule")
    public String titleModule;
    @SerializedName("codeinstance")
    public String codeInstance;
    @SerializedName("codeacti")
    public String codeActi;
    public String title;
    public String date;
    public String correcteur;
    @SerializedName("final_note")
    public float finalNote;
    public String comment;
}
