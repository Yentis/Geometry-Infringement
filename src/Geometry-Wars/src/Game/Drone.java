package Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Drone extends Sprite{
    //region Instance Variables

    private int nr;
    private String naam;
    private String beschrijving;
    private int type;
    private int kracht = 2;
    private int level = 0;
    private int currentXp = 0;
    private double maxXp = 1000;
    private double currentAngle;
    private int fireSpeed = 500; // default om de 500ms
    private ArrayList<Kogel> kogels = new ArrayList<>();
    private String imageString;

    //endregion

    //region Constructors

    public Drone(int nr, String naam, String beschrijving, int kracht, String uiterlijk, int type){
        super(uiterlijk);
        imageString = uiterlijk;
        x = 600;
        y = 300;
        currentLocation = new Point();
        currentLocation.setLocation(x, y);
        currentAngle = 0;

        this.nr = nr;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.type= type;
        this.kracht = kracht;
    }

    //endregion

    //region Getters & Setters

    public int getLevel() {
        return level;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public ArrayList<Kogel> getKogels() {
        return kogels;
    }

    public double getMaxXp() {
        return maxXp;
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

    public int getType() {
        return type;
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

    //endregion

    //region Behaviour

    private void addLevel() {
        this.level += 1;
    }

    public void addCurrentXp(int xp) {
        this.currentXp += xp;
    }

    private void resetCurrentXp() { this.currentXp = 0;}

    public void checkLevel(){
        if(getCurrentXp() >= getMaxXp()){
            addLevel();
            setMaxXp(getLevel());
            checkLevel();
            resetCurrentXp();
        }
    }

    public void drawAccordingToShip(Graphics2D g2d, Schip schip){
        g2d.translate(schip.getCurrentLocation().getX(), schip.getCurrentLocation().getY());
        g2d.rotate(Math.toRadians(this.getCurrentAngle()) , schip.getWidth() / 2, schip.getHeight()  / 2);
        g2d.translate(-schip.getCurrentLocation().getX(), -schip.getCurrentLocation().getY() );
    }

    private void setMaxXp(int level) {
        this.maxXp = Math.pow(2,level) * 1000;
    }

    //endregion
}
