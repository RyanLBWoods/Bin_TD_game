package towerdefence;
/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public abstract class Enemy {

    private int health;
    private int position;
    private float speed;

    /**
     * Get enemy object.
     * 
     * @param health
     *            Get the health of enemy
     * @param speed
     *            Get moving speed of enemy
     */
    public Enemy(int health, float speed) {
        this.health = health;
        this.speed = speed;
        this.position = 0;
    }

    /**
     * Get health of enemy.
     * 
     * @return Return the health
     */
    int getHealth() {
        return health;
    }

    /**
     * Get position of enemy.
     * 
     * @return Return the position
     */
    int getPosition() {
        return position;
    }

    /**
     * Method for hit enemies.
     * 
     * @param t
     */
    void hit(Tower t) {
        this.health -= t.getDamage();
    }

    /**
     * Method for enemy to move forward.
     */
    void advance() {
        this.position += speed;
    }
}
