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
    private int level;
    private int experience;

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int hp, int kracht, String uiterlijk, int level, int experience){
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.uiterlijk = uiterlijk;
        this.level = level;
        this.experience = experience;
    }

    public Drone(){
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }

    //endregion
}
