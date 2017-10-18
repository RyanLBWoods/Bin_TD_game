import java.util.ArrayList;

public class Game {
    
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();
    final static int buget = 10;
    public static int timeStep = 0;
    
    public Game(int corridorLength){
        
    }
    
    public int getTime(){
        return (int) (System.currentTimeMillis() - timeStep);
    }
    
    public void advance(){
        timeStep = (int) System.currentTimeMillis();
        while(getTime() == 1){
            
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
