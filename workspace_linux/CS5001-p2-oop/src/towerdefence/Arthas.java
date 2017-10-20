package towerdefence;

/**
 * Arthas, the Lich King. Boss of enemies
 * 
 * @author bl41
 *
 */
public class Arthas extends Enemy {
    /**
     * Constructor for Arthas.
     */
    public Arthas() {
        this.health = ARTHAS_HEALTH;
        this.speed = ARTHAS_SPEED;
        this.position = 0;
        this.bonus = ARTHAS_BONUS;
    }
}
