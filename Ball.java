import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

public class Ball {
    
    private double pos_x;
    private double pos_y;
    
    private double size;
    
    private double speed_x;
    private double speed_y;
    
    public Ball() {
        
        pos_x = 445.0;
        pos_y = 220.0;
        
        size = 10.0;
        
        speed_x = 5.0;
        speed_y = 5.0;
        
        System.out.println("Ball created.");
        
    }
    
    public void drawBall(Graphics2D g) {
        
        Ellipse2D.Double b = new Ellipse2D.Double(pos_x, pos_y, size, size);
        
        g.fill(b);
        
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
                
        pos_x = 445.0;
        pos_y = rand.nextInt(400) + 25;
        
    }
    
    public double getPosX() {
        
        return pos_x;
        
    }
    
    public double getPosY() {
        
        return pos_y;
        
    }
    
    public double getSize() {
        
        return size;
        
    }

    public double getSpeedX() {
        
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
