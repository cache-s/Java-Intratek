package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyUser
{
    public String login;
    public String title;
    @SerializedName("internal_email")
    public String internalEmail;
    @SerializedName("lastname")
    public String lastName;
    @SerializedName("firstname")
    public String firstName;
    @SerializedName("userinfo")
    public UserInfo userInfo;
    @SerializedName("referent_used")
    public boolean refUsed;
    public String picture;
    @SerializedName("picture_fun")
    public String pictureFun;
    public int promo;
    public int semester;
    public int uid;
    public int gid;
    public String location;
    public String documents;
    public String userdocs;
    public String shell;
    public boolean close;
    public String ctime;
    public String mtime;
    @SerializedName("id_promo")
    public String idPromo;
    @SerializedName("id_history")
    public String idHistory;
    @SerializedName("course_code")
    public String courseCode;
    @SerializedName("school_code")
    public String schoolCode;
    @SerializedName("school_title")
    public String schoolTitle;
    @SerializedName("old_id_promo")
    public String oldIdPromo;
    @SerializedName("old_id_location")
    public String oldIdLocation;
    public Rights rights;
    public boolean invited;
    @SerializedName("studentyear")
    public int studentYear;
    public boolean admin;
    public boolean editable;
    public String locations;
    public List<Group> groups;
    public List<Event> events;
    public int credits;
    public List<Gpa> gpa;
    public String spice;
    public NsStat nsstat;
}
