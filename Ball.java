import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

public class Ball extends GameObject {
    
    private double pos_x;
    private double pos_y;
    
    private double size;

    private double defaultSpeed = 5.0;
    private double speed_x;
    private double speed_y;
    
    Ellipse2D.Double b;
    
    public Ball(int ID) {
        
        OID = ID;
        NAME = "Ball";
        TYPE = "BALL";
        
        pos_x = 445.0;
        pos_y = 220.0;
        
        size = 10.0;
        
        speed_x = defaultSpeed;
        speed_y = defaultSpeed;
        
        b = new Ellipse2D.Double(pos_x, pos_y, size, size);
        
        System.out.println("Ball created.");
        
        VISIBLE = true;
        SOLID = true;
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        //Ellipse2D.Double b = new Ellipse2D.Double(pos_x, pos_y, size, size);
        b.setFrame(pos_x, pos_y, size, size);
        
        g.setColor(Color.white);
        g.fill(b);
        
    }
    
    public void updatePos() {
        
        pos_x += speed_x;        
        pos_y += speed_y;
        
    }
    
    public void updatePos(double n) {
        
        if (speed_x < 0) pos_x += -n;
        else pos_x += n;
        if (speed_y < 0) pos_y += -n;
        else pos_y += n;
        
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
    
    public void resetSpeed() {
        
        speed_x = defaultSpeed;
        speed_y = defaultSpeed;
        
    }
    
    public void resetPos() {
        
        Random rand = new Random();
                
        pos_x = 445.0;
        pos_y = rand.nextInt(400) + 25;
        
    }
    
    public double getPosX() {
        
        return pos_x + (size / 2);
        
    }
    
    public double getPosY() {
        
        return pos_y + (size / 2);
        
    }
    
    public double getSize() {
        
        return size;
        
    }

    public double getSpeedX() {
        
        return speed_x;
        
    }   

    public double getSpeedY() {
        
        return speed_y;
        
    }
    
    public boolean doesIntersect(Rectangle2D.Double r) {
                
        if (b.intersects(r)) {
            
            System.out.println("Does intersect");
            return true;
            
        }
        
        System.out.println("Doesnt intesect");
        return false;
        
    } 

    public Ellipse2D.Double getBall() {
        
        return b;
        
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
    
    // checks to see if the ball will collide with it
    // wont need to collide with itself, so will always return false
        
    public boolean detectCollision(Ball b) {
        
        System.out.println(NAME + ": Cannot detect collision, object cannot collide with itself.");
        return false;
        
    }
    
}
