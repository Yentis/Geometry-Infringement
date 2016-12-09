package Game;

import java.awt.*;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Drone extends Sprite{
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int hp = 100;
    private int kracht = 2;
    private String uiterlijk;
    private int level;
    private int experience;
    private Point location = new Point();
    private double locationX = location.getX();
    private double locationY = location.getY();

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int hp, int kracht, String uiterlijk, int level, int experience){
        super(uiterlijk);
        locationX = 600;
        locationY = 300;
        location.setLocation(locationX, locationY);
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
        super("");
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }

    //endregion

    public Point getLocation(){
        return location;
    }
}
