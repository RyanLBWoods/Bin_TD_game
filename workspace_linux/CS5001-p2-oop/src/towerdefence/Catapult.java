package towerdefence;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public class Catapult extends Tower {
    /**
     * Constructor.
     * 
     * @param position
     *            The position of tower
     */
    public Catapult(int position) {
        this.damage = CATAPULT_DAMAGE;
        this.loadingTime = CATAPULT_LOADINGTIME;
        this.position = position;
        this.cost = CATAPULT_COST;
        // TODO Auto-generated constructor stub
    }
}
