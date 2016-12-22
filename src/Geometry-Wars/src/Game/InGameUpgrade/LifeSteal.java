package Game.InGameUpgrade;

import Game.Schip;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Renzie on 21/12/2016.
 */
public class LifeSteal extends InGameUpgrade{
    private Schip schip;

    public LifeSteal(int nr, String naam, String foto, Schip schip) {
        super(nr, naam, foto);
        this.schip = schip;
    }

    @Override
    public void doFunction(){
        if (isActive()){
            schip.addHp(2);
        }
    }
}