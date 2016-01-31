package epitech.intratek.beans;

/**
 * Created by Dardaxe on 22/01/2016.
 */
public class Mark
{
    private int scolarYear;
    private String codeModule;
    private String titleModule;
    private String codeInstance;
    private String codeActi;
    private String title;
    private String correct;
    private Float finalNote;
    private String comment;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Mark()
    {

    }
    public Float getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(Float finalNote) {
        this.finalNote = finalNote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScolarYear() {
        return scolarYear;
    }

    public void setScolarYear(int scolarYear) {
        this.scolarYear = scolarYear;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getTitleModule() {
        return titleModule;
    }

    public void setTitleModule(String titleModule) {
        this.titleModule = titleModule;
    }

    public String getCodeInstance() {
        return codeInstance;
    }

    public void setCodeInstance(String codeInstance) {
        this.codeInstance = codeInstance;
    }

    public String getCodeActi() {
        return codeActi;
    }

    public void setCodeActi(String codeActi) {
        this.codeActi = codeActi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }



}
