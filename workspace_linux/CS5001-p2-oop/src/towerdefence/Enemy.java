package towerdefence;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public abstract class Enemy {
    /**
     * Static certain value 10 of health of enemy of type of elephant.
     */
    public static final int ELEPHANT_HEALTH = 10;
    /**
     * Static certain value 0.5 of speed of enemy of type of elephant.
     */
    public static final float ELEPHANT_SPEED = 0.5f;
    /**
     * Static certain value 1 of health of enemy of type of rat.
     */
    public static final int RAT_HEALTH = 1;
    /**
     * Static certain value 2 of health of enemy of type of rat.
     */
    public static final float RAT_SPEED = 2;

    protected int health;
    protected int position;
    protected float speed;
    /**
     * Get health of enemy.
     * 
     * @return Return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get position of enemy.
     * 
     * @return Return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Method for hit enemies.
     * 
     * @param t
     *            Parameter of tower
     */
    public void hit(Tower t) {
        this.health -= t.getDamage();
    }

    /**
     * Method for enemy to move forward.
     */
    public void advance() {
        this.position += speed;
    }
}
