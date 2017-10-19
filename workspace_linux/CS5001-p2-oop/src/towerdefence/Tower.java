package towerdefence;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public abstract class Tower {
    /**
     * Static certain value 5 of damage of tower of type of catapult.
     */
    public static final int CATAPULT_DAMAGE = 5;
    /**
     * Static certain value 5 of loading time of tower of type of catapult.
     */
    public static final int CATAPULT_LOADINGTIME = 3;
    /**
     * Static certain value 5 of damage of tower of type of sling shot.
     */
    public static final int SLINGSHOT_DAMAGE = 1;
    /**
     * Static certain value 5 of loading time of tower of type of sling shot.
     */
    public static final int SLINGSHOT_LOADINGTIME = 1;

    protected int damage;
    protected int position;
    protected int loadingTime;

    /**
     * Get damage of tower.
     * 
     * @return Return the damage of tower
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get position of tower.
     * 
     * @return Return the position of tower
     */
    public int getPosition() {
        return position;
    }

    /**
     * Method to justify if the tower will fire.
     * 
     * @param timeStep
     *            The running time of game
     * @return Return true of false value to indicate if tower will fire
     */
    public boolean willFire(int timeStep) {
        return timeStep % loadingTime == 0;
    }
}
