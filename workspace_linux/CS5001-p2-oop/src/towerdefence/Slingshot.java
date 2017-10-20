package towerdefence;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public class Slingshot extends Tower {
    /**
     * Constructor.
     * 
     * @param position
     *            The position of tower
     */
    public Slingshot(int position) {
        this.damage = SLINGSHOT_DAMAGE;
        this.loadingTime = SLINGSHOT_LOADINGTIME;
        this.position = position;
        this.cost = SLINGSHOT_COST;
        // TODO Auto-generated constructor stub
    }

}
