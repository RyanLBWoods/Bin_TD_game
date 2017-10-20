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
     * Static certain value 5 of bonus of enemy of type of elephant.
     */
    public static final int ELEPHANT_BONUS = 5;
    /**
     * Static certain value 1 of health of enemy of type of rat.
     */
    public static final int RAT_HEALTH = 1;
    /**
     * Static certain value 2 of health of enemy of type of rat.
     */
    public static final float RAT_SPEED = 2;
    /**
     * Static certain value 1 of bonus of enemy of type of rat.
     */
    public static final int RAT_BONUS = 1;
    /**
     * Static certain value 100 of health of enemy of type of boss Arthas.
     */
    public static final int ARTHAS_HEALTH = 100;
    /**
     * Static certain value 0.1 of speed of enemy of type of boss Arthas.
     */
    public static final float ARTHAS_SPEED = 0.2f;
    /**
     * Static certain value 100 of bonus of enemy of type of boss Arthas.
     */
    public static final int ARTHAS_BONUS = 100;

    protected int health;
    protected float position;
    protected float speed;
    protected int bonus;

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
        return (int) position;
    }

    /**
     * Method for hit enemies.
     * 
     * @param t
     *            Parameter of tower
     */
    public void hit(Tower t) {
        if (t.getPosition() >= position) {
            this.health -= t.getDamage();
        }
    }

    /**
     * Method for enemy to move forward.
     */
    public void advance() {
        position += speed;
    }
}
