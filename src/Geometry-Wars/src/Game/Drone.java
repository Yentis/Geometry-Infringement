package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
    /*private Point location = new Point();
    private double locationX = location.getX();
    private double locationY = location.getY();*/
    private double currentAngle;

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int hp, int kracht, String uiterlijk, int level, int experience){
        super(uiterlijk);
        x = 600;
        y = 300;
        currentLocation = new Point();
        currentLocation.setLocation(x, y);
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.kracht = kracht;
        this.uiterlijk = uiterlijk;
        this.level = level;
        this.experience = experience;
        currentAngle = 0;
    }

    public Drone(){
        super("");
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    public void drawAccordingToShip(Graphics2D g2d, Schip schip){
        g2d.translate(schip.getCurrentLocation().getX(), schip.getCurrentLocation().getY());
        g2d.rotate(Math.toRadians(this.getCurrentAngle()) , schip.getWidth() / 2, schip.getHeight()  / 2);
        g2d.translate(-schip.getCurrentLocation().getX(), -schip.getCurrentLocation().getY() );
    }

    //endregion

    /*public Point getLocation(){
        return location;
    }*/
}
