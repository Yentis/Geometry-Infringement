package Game;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Schip {
    //region Instance Variables

    private int nr;
    private int hp = 100;
    private int kracht = 10;

    //endregion

    //region Constructors

    public Schip(int nr, int hp, int kracht){
        this.nr = nr;
        this.hp = hp;
        this.kracht = kracht;
    }

    //endregion

    //region Properties

    public void setHp(int hp) {
        this.hp = hp;
    }

    //endregion

    //region Behaviour

    public void addKracht(int amount) {
        this.kracht += amount;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    //endregion
}
