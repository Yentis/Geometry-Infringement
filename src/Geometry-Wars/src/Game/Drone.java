package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Drone {
    //region Instance Variables

    private int dronenr;
    private String naam;
    private String beschrijving;
    private int hp = 100;
    private int kracht = 2;
    private String uiterlijk;

    //endregion

    //region Constructors

    public Drone(int dronenr, String naam, String beschrijving, int hp, int kracht, String uiterlijk){
        this.dronenr = dronenr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.uiterlijk = uiterlijk;
    }

    public Drone(){
        this.dronenr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }

    //endregion
}
