package towerdefence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.jws.soap.SOAPBinding;

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
    protected static ArrayList<Enemy> enemies = new ArrayList<>();
    protected static ArrayList<Tower> towers = new ArrayList<>();
    @SuppressWarnings("rawtypes")
    protected static ArrayList towerPosition = new ArrayList();
    protected static int budget = INITIAL_BUDGET;
    protected static int corriderlength = INITIAL_CORRIDERLENGTH;
    protected static int round = 0;
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, String> costlist = new HashMap<String, String>();

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
        this.corriderlength = corridorLength;
        for (int i = 0; i < corridorLength + 1; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < corridorLength; i++) {
            System.out.print(" ");
        }
        System.out.println("*");
        for (int i = 0; i < corridorLength + 1; i++) {
            System.out.print("*");
        }
        System.out.println();
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
             */
            for (int x = 0; x < enemies.size(); x++) {
                Enemy e = enemies.get(x);
                if (e instanceof Elephant && timeStep % 2 == 0 && timeStep != 0) {
                    e.advance();
                    if (e.getPosition() >= corriderlength) {
                        System.out.println("YOU LOSE!");
                        System.exit(0);
                    }
                } else if (e instanceof Rat && timeStep % 1 == 0) {
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
                if (t.willFire(timeStep)) {
                    hitEnemy(t, enemies);
                }
            }
            timeStep++;
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
            enemy.remove(ne);
            budget += ne.bonus;
        }
    }

    @SuppressWarnings("unchecked")
    public static void buildTower() {

        costlist.put("1", "10");
        costlist.put("2", "5");
        costlist.put("3", "30");

        System.out.println(
                "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 per 5 timestep");
        System.out.println("Please select tower");
        String selection = sc.nextLine();
        while (budget < Integer.valueOf(costlist.get(selection))) {
            System.out.println("Insufficient budget");
            System.out.println(
                    "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 per 5 timestep");
            System.out.println("Please select tower");
            selection = sc.nextLine();
        }

        System.out.println("Please select position (0~50)");
        String sposition = sc.nextLine();
        int sPosition = Integer.valueOf(sposition);
        while (towerPosition.contains(sPosition)) {
            System.out.println("Occupied position");
            System.out.println(
                    "Tower:\n1 Catapult: 10 bonus 5 damage per 3 timestep\n2 Slingshot: 5 bonus 1 damage per timestep\n3 Fordring: 30 per 5 timestep");
            System.out.println("Please select tower");
            sposition = sc.nextLine();
            sPosition = Integer.valueOf(sposition);
        }

        towerPosition.add(sPosition);
        System.out.println("START");

        switch (selection) {
        default:
            System.out.println();
            System.exit(0);
        case "1":
            Catapult c = new Catapult(sPosition);
            budget = budget - c.cost;
            towers.add(c);
            for (int i = 0; i < sPosition - 1; i++) {
                System.out.print(" ");
            }
            System.out.println("C");
            break;
        case "2":
            Slingshot s = new Slingshot(sPosition);
            budget = budget - s.cost;
            towers.add(s);
            for (int i = 0; i < sPosition - 1; i++) {
                System.out.print(" ");
            }
            System.out.println("S");
            break;
        case "3":
            Fordring f = new Fordring(sPosition);
            budget = budget - f.cost;
            towers.add(f);
            for (int i = 0; i < sPosition - 1; i++) {
                System.out.print(" ");
            }
            System.out.println("F");
            break;
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

        System.out.println("Please Zoom your terminal to FULL SCREEN and Press ENTER");
        sc.nextLine();

        System.out.println("You have 5 initial budget to build towers, you will get bonus after kill enemies");
        System.out.println(
                "There are 5 rounds. You can only build after each round. \nYour tower can only hit enemies who is in front of it");
        buildTower();
        Game game = new Game(corriderlength);
        Rat r = new Rat();
        Elephant ele = new Elephant();
        enemies.add(r);
        enemies.add(ele);
        game.advance();
    }

}
