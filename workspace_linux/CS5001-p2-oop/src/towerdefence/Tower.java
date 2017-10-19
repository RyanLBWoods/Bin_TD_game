package towerdefence;
/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public abstract class Tower {

    private int damage;
    private int position;
    private int loadingTime;

    /**
     * Tower constructor.
     * 
     * @param damage
     *            Damage of the tower
     * @param loadingTime
     *            Time request for loading
     */
    public Tower(int damage, int loadingTime) {
        this.damage = damage;
        this.position = 0;
        this.loadingTime = loadingTime;
    }

    /**
     * Get damage of tower.
     * 
     * @return Return the damage of tower
     */
    int getDamage() {
        return damage;
    }

    /**
     * Get position of tower.
     * 
     * @return Return the position of tower
     */
    int getPosition() {
        return position;
    }

    /**
     * Method to justify if the tower will fire.
     * 
     * @param timeStep
     *            The running time of game
     * @return Return true of false value to indicate if tower will fire
     */
    boolean willFire(int timeStep) {
        return timeStep % loadingTime == 0;
    }
}
