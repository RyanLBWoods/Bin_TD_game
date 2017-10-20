package towerdefence;

/**
 * Fordring, the lord. the Ultimate Tower.
 * 
 * @author bl41
 *
 */
public class Fordring extends Tower {
    /**
     * Constructor for Fordring.
     * 
     * @param position
     *            The position of tower
     */
    public Fordring(int position) {
        this.damage = FORDRING_DAMAGE;
        this.loadingTime = FORDRING_LOADINGTIME;
        this.position = position;
        this.cost = FORDRING_COST;
    }
}
