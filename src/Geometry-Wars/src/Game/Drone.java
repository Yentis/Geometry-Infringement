package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
    private int level = 0;
    private int currentXp = 0;
    private double maxXp = 1000;
    private double currentAngle;
    private int fireSpeed = 500; // om de 0,5 seconden
    private ArrayList<Kogel> kogels = new ArrayList<Kogel>();
    private String imageString;

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int hp, String uiterlijk){
        super(uiterlijk);
        imageString = uiterlijk;
        x = 600;
        y = 300;
        currentLocation = new Point();
        currentLocation.setLocation(x, y);
        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.hp = hp;
        this.uiterlijk = uiterlijk;
        currentAngle = 0;
    }

    public Drone(){
        super("");
        this.nr = 0;
        this.naam = "Placeholder";
        this.beschrijving = "Placeholder";
        this.uiterlijk = "Vierkant";
    }
    // region level properties
    public int getLevel() {
        return level;
    }

    public void addLevel() {
        this.level += 1;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void addCurrentXp(int xp) {
        this.currentXp += xp;
    }

    public void resetCurrentXp() { this.currentXp = 0;}

    public double getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(int level) {
        this.maxXp = Math.pow(2,level) * 1000;
    }

    public void checkLevel(){
        if(getCurrentXp() >= getMaxXp()){
            addLevel();
            setMaxXp(getLevel());
            checkLevel();
            resetCurrentXp();
        }

        /*switch (getLevel()){
            case 1:
                setKracht(75);
                break;
            case 2:
                setKracht(100);
                break;
            case 3:
                setKracht(150);
                break;
            case 4:
                setKracht(200);
                break;
            case 5:
                setKracht(250);
                break;
        }*/
    }
    // end region

    //region new region
    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    public String getImageString() {
        return imageString;
    }

    public int getNr() {
        return nr;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getHp() {
        return hp;
    }

    public int getKracht() {
        return kracht;
    }

    public int getFireSpeed() {
        return fireSpeed;
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
}
