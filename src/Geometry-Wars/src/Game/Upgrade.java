package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Upgrade {
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int level = 1;
    private String foto = "";
    private int kost = 100;
    //private boolean active;

    //endregion

    //region Constructors

    public Upgrade(int nr, String naam, String beschrijving, int level, String foto, int kost){
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.level = level;
        this.foto = foto;
        this.kost = kost;
        //active = false;
    }

    public Upgrade(){
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
    }

    //endregion

    //region Getters

   // public boolean isActive() {
   //     return active;
   // }

    //endregion




}
