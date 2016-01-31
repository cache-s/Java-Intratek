package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Item
{
    public String title;
    public String login;
    @SerializedName("prenom")
    public String firstName;
    @SerializedName("nom")
    public String lastName;
    public String picture;
    public String location;
}
