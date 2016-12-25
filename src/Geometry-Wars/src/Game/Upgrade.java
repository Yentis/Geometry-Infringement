package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Upgrade {
    //region Instance Variables

    private int nr;
    private String naam;
    private String foto = "";
    private int kost = 100;

    //endregion

    //region Constructors

    Upgrade(int nr, String naam, String foto, int kost){
        this.nr = nr;
        this.naam = naam;
        this.foto = foto;
        this.kost = kost;
    }

    //endregion

    //region Getters & Setters

    public String getFoto() {
        return foto;
    }

    public int getKost() {
        return kost;
    }

    public int getNr() {
        return nr;
    }

    public String getNaam() {
        return naam;
    }

    //endregion
}
