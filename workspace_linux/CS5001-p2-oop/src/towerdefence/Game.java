package towerdefence;
import java.util.ArrayList;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public class Game {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();
    private int budget = 10;
    private int corriderlength;
    /**
     * Global variable for getting running time.
     */
    private static int timeStep = 0;

    /**
     * Mehtod for game running.
     * 
     * @param corridorLength
     *            The length of game corridor
     */
    public Game(int corridorLength) {
        this.corriderlength = corridorLength;
    }

    /**
     * Get programming running time.
     * 
     * @return the current real time minus time step
     */
    public int getTime() {
        return (int) (System.currentTimeMillis() - timeStep);
    }

    /**
     * Method for game to run forward.
     */
    public void advance() {

        timeStep = (int) System.currentTimeMillis();

        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).willFire((int) System.currentTimeMillis() - timeStep)) {
                for (int j = 0; j < enemies.size(); j++) {

                    if (towers.get(i).getPosition() >= enemies.get(j).getPosition()) {
                        enemies.get(j).hit(towers.get(i));
                    }

                    if (enemies.get(j).getHealth() != 0) {
                        enemies.get(j).advance();
                    }
                }
            }
        }

    }

    /**
     * Main method for the program.
     * @param args initial user input.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
