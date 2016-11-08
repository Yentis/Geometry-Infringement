package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Speler {
    //region Instance Variables
    private int spelernr;
    private String gebruikersnaam;
    private String wachtwoord;
    private String email;
    private int level = 1;
    private int experience = 0;
    private String profielfoto;
    private int rank = 0;
    private int nuggets = 0;
    private int gnuggets = 0;
    //endregion

    //region Constructors
    public Speler(String gebruikersnaam, String wachtwoord, String email){
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.email = email;
    }
    //endregion

    //region Properties
    
    //endregion
}
