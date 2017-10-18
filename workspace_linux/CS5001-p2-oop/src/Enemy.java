
public abstract class Enemy {
    
    int position = 0;
    
    public Enemy(){
        
    }
    
    int getHealth(){
        return 0;
    }
    
    int getPosition(){
        return position;
    }
    
    void hit (Tower t){
        t.getDamage();
    }
    
    void advance(){
        
    }
}
