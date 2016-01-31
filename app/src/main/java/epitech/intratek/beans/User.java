package epitech.intratek.beans;
import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("ip")
    private String ip;
    private Board board;
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String gpa;
    private String credits;
    private String logTime;
    private String profilePicture;

    public User()
    {

    }

    public User(String id, String firstName, String lastName, String login, String gpa, String credits, String logTime, String profilePicture)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.gpa = gpa;
        this.credits = credits;
        this.logTime = logTime;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilPicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


}
