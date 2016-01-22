package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

public class Infos
{
    public String id;
    public String login;
    public String title;
    public String email;
    @SerializedName("internal_email")
    public String internalEmail;
    @SerializedName("lastname")
    public String lastName;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("userinfo")
    public String userInfo;
    @SerializedName("referent_used")
    public boolean referentUsed;
    public String picture;
    @SerializedName("picture_fun")
    public String pictureFun;
    @SerializedName("email_referent")
    public String emailRef;
    @SerializedName("pass_referent")
    public String passRef;
    @SerializedName("scolaryear")
    public String scolarYear;
    public int promo;
    public int semester;
    public int uid;
    public int gid;
    public String location;
    public String documents;
    @SerializedName("userdocs")
    public String userDocs;
    public String shell;
    public String netsoul;
    public boolean   close;
    @SerializedName("close_reason")
    public String closeReason;
    public String ctime;
    public String mtime;
    public String comment;
    @SerializedName("id_promo")
    public String idPromo;
    @SerializedName("id_history")
    public String idHistory;
    @SerializedName("course_code")
    public String courseCode;
    @SerializedName("semester_code")
    public String semesterCode;
    @SerializedName("school_id")
    public String schoolId;
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
    public double decoded;
}
