package towerdefence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    public static final int INITIAL_BUDGET = 5;
    /**
     * Define 50 as initial length for corridor.
     */
    public static final int INITIAL_CORRIDERLENGTH = 50;
    /**
     * Define 5000 for 5 second use for making a pause after each round.
     */
    public static final int ROUND_PAUSE = 5000;
    /**
     * Define 1000 for 1 second use for making a pause after each time step.
     */
    public static final int TIME_STEP_PAUSE = 500;
    protected static ArrayList<Enemy> enemies = new ArrayList<>();
    protected static ArrayList<Tower> towers = new ArrayList<>();
    // Temper list used for displaying tower
    protected static ArrayList<Tower> tmpTL = new ArrayList<>();
    @SuppressWarnings("rawtypes")
    protected static ArrayList towerPosition = new ArrayList();
    protected static int budget = INITIAL_BUDGET;
    protected static int corriderlength = INITIAL_CORRIDERLENGTH;
    protected static int round = 0;
    protected static Scanner sc = new Scanner(System.in);
    // A hash map storing the tower and its cost
    protected static HashMap<String, String> costlist = new HashMap<String, String>();
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
    @SuppressWarnings("static-access")
    public Game(int corridorLength) {
        this.corriderlength = corridorLength; // Get corridor length
        int tempp = corridorLength;
        int frontT = 0;
        int printP = 0;
        // Display the tower along the corridor wall (indicate by *)
        // Use a temper list to avoid influencing the tower list
        for (int p = 0; p < towers.size(); p++) {
            Tower tmpT = towers.get(p);
            tmpTL.add(tmpT);
        }
        /*
         * Display towers
         */
        while (!tmpTL.isEmpty()) {
            // Find the position order
            for (int m = 0; m < tmpTL.size(); m++) {
                Tower tempt = tmpTL.get(m);
                if (tempt.getPosition() <= tempp) {
                    tempp = tempt.getPosition();
                    frontT = m;
                }
            }
            // Print space for empty position
            for (int n = 0; n < (tempp - printP) - 1; n++) {
                System.out.print(" ");
            }
            // Use initial to indicate the type of tower
            if (tmpTL.get(frontT) instanceof Catapult) {
                System.out.print("C");
            } else if (tmpTL.get(frontT) instanceof Slingshot) {
                System.out.print("S");
            } else if (tmpTL.get(frontT) instanceof Fordring) {
                System.out.print("F");
            }
            // Get printed length, start next displaying by tailing it
            printP = tempp;
            // Remove displayed tower
            tmpTL.remove(frontT);
            // Reset temper position
            tempp = corridorLength;
        }
        System.out.println();

        // Display the corridor wall
        for (int i = 0; i < corridorLength; i++) {
            System.out.print("*");
        }
        System.out.println();
        // Reset the temper tower list for next round
        tmpTL = new ArrayList<>();
    }

    /**
     * Get programming running time.
     * 
     * @return the current real time minus time step
     */
    public int getTime() {
        return timeStep;
    }

    /**
     * Method for game to run forward.
     */
    public void advance() {

        while (!enemies.isEmpty()) {
            /*
             * Enemy move. If any enemy move over the corridor, the game is over
             * Display enemy move by print space in front of its position
             */
            for (int x = 0; x < enemies.size(); x++) {
                Enemy e = enemies.get(x);
                if (e instanceof Elephant && timeStep % 2 == 0 && timeStep != 0) {
                    e.advance();
                    for (int y = 0; y < e.getPosition() - 1; y++) {
                        System.out.print(" ");
                    }
                    System.out.println("Elephant: HP " + e.getHealth());
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                } else if (e instanceof Rat && timeStep % 1 == 0 && timeStep != 0) {
                    e.advance();
                    for (int y = 0; y < e.getPosition() - 1; y++) {
                        System.out.print(" ");
                    }
                    System.out.println("Rat: HP " + e.getHealth());
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                } else if (e instanceof Arthas && timeStep != 0) {
                    e.advance();
                    for (int y = 0; y < e.getPosition() - 1; y++) {
                        System.out.print(" ");
                    }
                    System.out.println("Arthas: HP " + e.getHealth());
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                }
                // System.out.println();
            }
            /*
             * Tower fire.
             */
            for (int i = 0; i < towers.size(); i++) {
                Tower t = towers.get(i);
                if (t.willFire(timeStep)) {
                    hitEnemy(t, enemies);
                }
            }
            timeStep++;
            // Make a half second pause after each time step so that user can
            // see the game running
            try {
                Thread.sleep(TIME_STEP_PAUSE);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("YOU WIN!");
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
        // Hit the enemy if it is in front of the tower
        Enemy ne = enemy.get(index);
        ne.hit(t);
        if (ne.health <= 0) {
            budget = budget + ne.bonus; // Get reward bonus for killing enemy
            enemy.remove(ne); // Remove dead enemy
        }
    }

    /**
     * Method for building the tower.
     */
    @SuppressWarnings("unchecked")
    public static void buildTower() {
        // Store budget into hash map
        costlist.put("1", "10");
        costlist.put("2", "5");
        costlist.put("3", "30");
        /*
         * Show tower menu and let user to chose new tower and its position new
         * selection have to be under budget and at an empty position
         */
        System.out.println(
                "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 bonus 10 damage per 5 timestep");
        System.out.println("Please select tower");
        String selection = sc.nextLine();
        // Check if it is out of budget
        while (budget < Integer.valueOf(costlist.get(selection))) {
            System.out.println("Insufficient budget");
            System.out.println(
                    "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 bonus 10 damage per 5 timestep");
            System.out.println("Please select tower");
            selection = sc.nextLine();
        }
        // Check if new position is occupied
        System.out.println("Please select position (0~50)");
        String sposition = sc.nextLine();
        int sPosition = Integer.valueOf(sposition);
        while (towerPosition.contains(sPosition)) {
            System.out.println("Occupied position");
            System.out.println(
                    "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 bonus 10 damage per 5 timestep");
            System.out.println("Please select tower");
            sposition = sc.nextLine();
            sPosition = Integer.valueOf(sposition);
        }
        // Record tower position for next checking
        towerPosition.add(sPosition);
        System.out.println("\033[H\033[2J"); // Flush terminal
        System.out.println("START");
        // Add new tower according to user choice
        switch (selection) {
        default:
            System.out.println();
            System.exit(0);
        case "1":
            Catapult c = new Catapult(sPosition);
            budget = budget - c.cost;
            towers.add(c);
            break;
        case "2":
            Slingshot s = new Slingshot(sPosition);
            budget = budget - s.cost;
            towers.add(s);
            break;
        case "3":
            Fordring f = new Fordring(sPosition);
            budget = budget - f.cost;
            towers.add(f);
            break;
        }
    }

    /**
     * Main method for the program.
     * 
     * @param args
     *            initial user input.
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Display game instruction
        System.out.println("\033[H\033[2J"); // Flush terminal
        System.out.println("You have 5 initial budget to build towers, you will get bonus after kill enemies.");
        System.out.println(
                "There are 3 rounds. You can only build one tower after each round. \nYour tower can only hit enemies who is in front of it.");
        System.out.println("Please Zoom your terminal to FULL SCREEN and Press ENTER to start");
        sc.nextLine();
        // Round one
        System.out.println("Round 1: 5 Rats and 2 Elephant");
        System.out.println("Budget: " + budget);
        Rat r1 = new Rat();
        Rat r2 = new Rat();
        Rat r3 = new Rat();
        Rat r4 = new Rat();
        Rat r5 = new Rat();
        Elephant ele1 = new Elephant();
        Elephant ele2 = new Elephant();
        enemies.add(r1);
        enemies.add(r2);
        enemies.add(r3);
        enemies.add(r4);
        enemies.add(r5);
        enemies.add(ele1);
        enemies.add(ele2);
        round();
        // Give a pause before next round
        try {
            Thread.sleep(ROUND_PAUSE);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Round two
        System.out.println("\033[H\033[2J"); // Flush terminal
        System.out.println("Round 2: 5 Rats and 4 Elephant");
        System.out.println("Budget: " + budget);
        Rat r6 = new Rat();
        Rat r7 = new Rat();
        Rat r8 = new Rat();
        Rat r9 = new Rat();
        Rat r10 = new Rat();
        Elephant ele3 = new Elephant();
        Elephant ele4 = new Elephant();
        Elephant ele5 = new Elephant();
        Elephant ele6 = new Elephant();
        enemies.add(r6);
        enemies.add(r7);
        enemies.add(r8);
        enemies.add(r9);
        enemies.add(r10);
        enemies.add(ele3);
        enemies.add(ele4);
        enemies.add(ele5);
        enemies.add(ele6);
        round();
        // Give a pause before next round
        try {
            Thread.sleep(ROUND_PAUSE);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\033[H\033[2J"); // Flush terminal
        System.out.println("Round 3: The Lich King is COMING!!!");
        System.out.println("Budget: " + budget);
        Arthas a = new Arthas();
        enemies.add(a);
        round();
    }

    /**
     * Method for generating each round of the game.
     */
    public static void round() {
        buildTower();
        Game game = new Game(corriderlength);
        game.advance();
    }

}
