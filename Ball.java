import java.util.Random;

public class Ball {
    
    private int pos_x;
    private int pos_y;
    
    private int size;
    
    private int speed_x;
    private int speed_y;
    
    public Ball() {
        
        pos_x = 445;
        pos_y = 220;
        
        size = 10;
        
        speed_x = 5;
        speed_y = 5;
        
        System.out.println("Ball created.");
        
    }
    
    public void updatePos() {
        
        pos_x += speed_x;
        
        pos_y += speed_y;
        
    }
    
    public void invertSpeedX() {
        
        speed_x = -speed_x;
        
    }
    
    public void invertSpeedY() {
        
        speed_y = -speed_y;
        
    }
    
    public void setSpeed(int newSpeed) {
        
        speed_x = newSpeed;
        speed_y = newSpeed;
        
    }
    
    public void resetPos() {
        
        Random rand = new Random();
                
        pos_x = 445;
        pos_y = rand.nextInt(400) + 25;
        
    }
    
    public int getPosX() {
        
        return pos_x;
        
    }
    
    public int getPosY() {
        
        return pos_y;
        
    }
    
    public int getSize() {
        
        return size;
        
    }

    public int getSpeedX() {
        
        return speed_x;
        
    }   
    
     public void increaseSpeed() {
        
        if (speed_x > 0){
            speed_x++;
        }
        else{
            speed_x--;
        }
    
        if (speed_y > 0){
            speed_y++;
        }
        else{
            speed_y--;
        }    
    
        
    }      
    
}
