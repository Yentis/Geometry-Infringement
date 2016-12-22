package Game.InGameUpgrade;


import Game.Schip;

/**
 * Created by Renzie on 21/12/2016.
 */
public class SlowEnemies extends InGameUpgrade{

    private Schip schip;

    public SlowEnemies(int nr, String naam, String foto, Schip schip) {
        super(nr, naam, foto);
        this.schip = schip;
    }

    @Override
    public void doFunction(){
        //TODO
    }
}
