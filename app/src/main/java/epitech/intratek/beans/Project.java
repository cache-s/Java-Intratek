package epitech.intratek.beans;

/**
 * Created by Dardaxe on 24/01/2016.
 */
public class Project
{
    private String  titleModule;
    private String  titleProject;
    private String  scolarYear;
    private String  codeModule;
    private String  codeInstance;
    private String description;

    public String getScolarYear() {
        return scolarYear;
    }

    public void setScolarYear(String scolarYear) {
        this.scolarYear = scolarYear;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
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

    private String  codeActi;

    public String getTitleModule() {
        return titleModule;
    }

    public void setTitleModule(String titleModule) {
        this.titleModule = titleModule;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }


}
