package epitech.intratek.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Board
{
    @SerializedName("projets")
    private List<Projects> projects;
    private List<Notes> notes;
    private List<Susies> susies;
    private List<Activities> activities;
    private List<Modules> modules;
    private List<Stages> stages;
    private List<Tickets> tickets;
}
