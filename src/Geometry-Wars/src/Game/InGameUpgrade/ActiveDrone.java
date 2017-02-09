package Game.InGameUpgrade;

import Game.Schip;

import java.io.InputStream;

/**
 * Created by Renzie on 21/12/2016.
 */
public class ActiveDrone extends InGameUpgrade{
    private Schip schip;

    public ActiveDrone(int nr, String naam, String foto, Schip schip) {
        super(nr, naam, foto);
    }

    @Override
    public void doFunction(){
        //TODO
    }
}
