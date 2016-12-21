package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Renzie on 21/12/2016.
 */
public class InGameUpgrade {
    private int nr;
    private String naam;
    private String beschrijving;
    private String foto = "";
    private boolean active;
    private Image image;


    public InGameUpgrade(int nr, String naam, String foto) {
        this.nr = nr;
        this.naam = naam;
        this.foto = foto;
        active = false;
    }

    public InGameUpgrade(){
        active = false;
    }

    //region getters
    public int getNr() {
        return nr;
    }

    public String getNaam() {
        return naam;
    }

    public Image getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }



    //endregion

    //region setters

    public void setActive(boolean active) {
        this.active = active;
    }

    //endregion

    public void doFunction(){
        // overrideable
    }


    public class LifeSteal extends InGameUpgrade{
        private Schip schip;
        private Image image;

        public LifeSteal(int nr, String naam, String foto, Schip schip) {
            super(nr, naam, foto);
            this.schip = schip;
            ImageIcon ii = new ImageIcon(foto);
            this.image = ii.getImage();
        }

        @Override
        public Image getImage() {
            return image;
        }

        @Override
        public void doFunction(){
            if (isActive()){
                schip.addHp(2);
            }
        }
    }
}
