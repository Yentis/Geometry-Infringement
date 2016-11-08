package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Upgrade {
    //region Instance Variables

    private int upgradenr;
    private String naam;
    private String beschrijving;
    private int level = 1;
    private String foto = "";
    private int kost = 100;

    //endregion

    //region Constructors

    public Upgrade(int upgradenr, String naam, String beschrijving, int level, String foto, int kost){
        this.upgradenr = upgradenr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.level = level;
        this.foto = foto;
        this.kost = kost;
    }

    public Upgrade(){
        this.upgradenr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
    }

    //endregion
}
