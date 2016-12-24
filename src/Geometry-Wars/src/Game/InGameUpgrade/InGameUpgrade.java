package Game.InGameUpgrade;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Renzie on 21/12/2016.
 */
public class InGameUpgrade {
    private int nr;
    private String naam;
    private String foto = "";
    private boolean active;
    private Image image;

    public InGameUpgrade(int nr, String naam, String foto) {
        this.nr = nr;
        this.naam = naam;
        this.foto = foto;
        active = false;
        ImageIcon ii = new ImageIcon(foto);
        this.image = ii.getImage();
    }

    public InGameUpgrade(){
        active = false;
    }

    //region getters

    public int getNr() {
        return nr;
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
}
