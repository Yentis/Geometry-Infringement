package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Drone {
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int hp = 100;
    private int kracht = 2;
    private String uiterlijk;

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int hp, int kracht, String uiterlijk){
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.uiterlijk = uiterlijk;
    }

    public Drone(){
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }

    //endregion
}
