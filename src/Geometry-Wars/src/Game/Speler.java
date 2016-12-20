package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Speler {
    //region Instance Variables
    private int nr;
    private String gebruikersnaam;
    private String wachtwoord;
    private String email;
    private int level = 1;
    private int experience = 0;
    private String rank = "Bronze 1";
    private int nuggets = 0;
    private int gnuggets = 0;
    private String comment = "";
    //endregion

    //region Constructors

    public Speler(int nr, String gebruikersnaam, String wachtwoord, String email, int level, int experience, String rank, int nuggets, int gnuggets, String comment){
        this.nr = nr;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.email = email;
        this.level = level;
        this.experience = experience;
        this.rank = rank;
        this.nuggets = nuggets;
        this.gnuggets = gnuggets;
        this.comment = comment;
    }

    public Speler(){
        this.nr = 0;
        this.gebruikersnaam = "Placeholder";
        this.wachtwoord = "Placeholder";
        this.email = "Placeholder@placeholder.com";
    }

    //endregion

    //region Properties


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

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    //endregion

    //region Behaviour

    public void levelUp() {
        this.level += 1;
    }

    public void experienceUp(int amount) {
        this.experience += amount;
    }

    public void addNuggets(int amount) {
        this.nuggets += amount;
    }

    public void addGNuggets(int amount) {
        this.gnuggets += amount;
    }

    //endregion
}
