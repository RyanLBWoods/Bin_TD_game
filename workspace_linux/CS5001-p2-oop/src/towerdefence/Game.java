package towerdefence;

import java.util.ArrayList;

/**
 * CS5001 - P2: Tower Defence game.
 * 
 * @author bl41
 *
 */
public class Game {
    /**
     * Define 10 as initial budget.
     */
    public static final int INITIAL_BUDGET = 10;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();
    private int budget = INITIAL_BUDGET;
    private int corriderlength;
    /**
     * Global variable for getting running time.
     */
    private static int timeStep = 0;

    /**
     * Method for game running.
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
        return (int) ((System.currentTimeMillis() / 1000) - timeStep);
    }

    /**
     * Method for game to run forward.
     */
    public void advance() {

        timeStep = (int) System.currentTimeMillis() / 1000;
        while (!enemies.isEmpty()) {
            /*
             * Enemy move. If any enemy move over the corridor, the game is over
             */
            for (int x = 0; 0 < enemies.size(); x++) {
                Enemy e = enemies.get(x);
                if (e instanceof Elephant && (int) (System.currentTimeMillis() / 1000 - timeStep) % 2 == 0) {
                    e.advance();
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                } else if (e instanceof Rat) {
                    e.advance();
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                }
            }
            /*
             * Tower fire.
             */
            for (int i = 0; i < towers.size(); i++) {
                Tower t = towers.get(i);
                if (t instanceof Catapult && t.willFire((int) (System.currentTimeMillis() / 1000 - timeStep))) {
                    hitEnemy(t, enemies);
                } else if (t instanceof Slingshot && t.willFire((int) (System.currentTimeMillis() / 1000 - timeStep))) {
                    hitEnemy(t, enemies);
                }
            }
        }
    }

    /**
     * Method for hitting enemy.
     * 
     * @param t
     *            The current Tower
     * @param enemy
     *            The list of enemies
     */
    private void hitEnemy(Tower t, ArrayList<Enemy> enemy) {
        // TODO Auto-generated method stub
        // Find the nearest enemy
        int nearest = 0;
        int index = 0;
        for (int a = 0; a < enemy.size(); a++) {
            Enemy temp = enemy.get(a);
            if (temp.getPosition() > nearest) {
                nearest = temp.getPosition();
                index = a;
            }
        }
        // Hit the enemy
        Enemy ne = enemy.get(index);
        if (ne.getPosition() <= t.getPosition()) {
            ne.hit(t);
            if (ne.health <= 0) {
                enemy.remove(ne);
            }
        }
    }

    /**
     * Main method for the program.
     * 
     * @param args
     *            initial user input.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
