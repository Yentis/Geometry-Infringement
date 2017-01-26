package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Speler {
    //region Instance Variables

    private int nr;
    private String gebruikersnaam;
    private int level = 1;
    private int experience = 0;
    private String rank = "Unranked";
    private int nuggets = 0;
    private int gnuggets = 0;
    private int highscore = 0;
    private String activeDrone = "No drone";

    //endregion

    //region Constructors

    Speler(int nr, String gebruikersnaam, int level, int experience, String rank, int nuggets, int gnuggets, int highscore){
        this.nr = nr;
        this.gebruikersnaam = gebruikersnaam;
        this.level = level;
        this.experience = experience;
        this.rank = rank;
        this.nuggets = nuggets;
        this.gnuggets = gnuggets;
        this.highscore = highscore;
    }

    //endregion

    //region Getters & Setters

    public int getNr() {
        return nr;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getGnuggets() {
        return gnuggets;
    }

    public int getNuggets() {
        return nuggets;
    }

    public String getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getActiveDrone() {
        return activeDrone;
    }

    public void setActiveDrone(String activeDrone) {
        this.activeDrone = activeDrone;
    }

    //endregion
}