package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

public class Infos
{
    private String id;
    private String login;
    private String title;
    private String email;
    @SerializedName("internal_email")
    private String internalEmail;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("userinfo")
    private String userInfo;
    @SerializedName("referent_used")
    private String referentUsed;
    private String picture;
    @SerializedName("picture_fun")
    private String pictureFun;
    @SerializedName("email_referent")
    private String emailRef;
    @SerializedName("pass_referent")
    private String passRef;
    @SerializedName("scolaryear")
    private String scolarYear;
    private String promo;
    private String semester;
    private String uid;
    private String gid;
    private String location;
    private String documents;
    @SerializedName("userdocs")
    private String userDocs;
    private String shell;
    private String netsoul;
    private String close;
    @SerializedName("close_reason")
    private String closeReason;
    private String ctime;
    private String mtime;
    private String comment;
    @SerializedName("id_promo")
    private String idPromo;
    @SerializedName("id_history")
    private String idHistory;
    @SerializedName("course_code")
    private String courseCode;
    @SerializedName("semester_code")
    private String semesterCode;
    @SerializedName("school_id")
    private String schoolId;
    @SerializedName("school_code")
    private String schoolCode;
    @SerializedName("school_title")
    private String schoolTitle;
    @SerializedName("old_id_promo")
    private String oldIdPromo;
    @SerializedName("old_id_location")
    private String oldIdLocation;
    private Rights rights;
    private String invited;
    @SerializedName("studentyear")
    private String studentYear;
    private String admin;
    private String decoded;
}
