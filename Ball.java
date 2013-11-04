import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

public class Ball extends GameObject {
    
    private double pos_x;
    private double pos_y;
    
    private double size;
    private double sizeScale = 1.25;

    // speed of the board is measured in a percent of the screen travelled every cycle
    private double defaultSpeed = 0.55;
    private double speedIncrement = 0.05;
    private double speed_x;
    private double speed_y;
    
    Ellipse2D.Double b;
    
    //Initialise the line that is used for collision with objects
    private Line2D.Double ballCollisionLine = new Line2D.Double();
        
    public Ball(int ID, Canvas c) {
        
        OID = ID;
        NAME = "Ball";
        TYPE = "BALL";
        
        pos_x = c.getWidth() / 2;
        pos_y = c.getHeight() / 2;
        
        size = (c.getWidth() / 100.0) * sizeScale;
        
        speed_x = defaultSpeed;
        speed_y = defaultSpeed;
        
        b = new Ellipse2D.Double(pos_x, pos_y, size, size);
        
        //create ball collision line
        createBallCollisionLine(c);
        
        System.out.println("Ball created.");
              
        VISIBLE = true;
        SOLID = true;
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        // set the size of the ball
        size = (c.getWidth() / 100.0) * sizeScale;
        
        //Ellipse2D.Double b = new Ellipse2D.Double(pos_x, pos_y, size, size);
        b.setFrame(pos_x, pos_y, size, size);
        
        g.setColor(Color.white);
        g.fill(b);
        
    }
    
    public void updatePos(Canvas c) {
        
        pos_x += (c.getWidth() / 100.0) * speed_x;
        pos_y += (c.getWidth() / 100.0) * speed_y;
        
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
    
    public void resetPos(Canvas c) {
        
        Random rand = new Random();
        
        // stop the bal from spawning in a section at the top and bottom of the screen
        // it will spawn randomly in a section in the middle
        int noSpawnZone = (c.getHeight() / 100) * 10;
        
        pos_x = c.getWidth() / 2.0;
        // set the spawn zone to some
        pos_y = rand.nextInt(c.getHeight() - (noSpawnZone * 2)) + noSpawnZone;
        
    }
    
    public void createBallCollisionLine(Canvas c) {
        //Create a line which is from the current position of the ball
        //to where the ball will be on the next tick
        //Then we can use the intersects method to detect whether it would
        //pass through an object, and hence it would collide rather than pass through.
        
        double correction = size/2;
        double x1 = pos_x + correction;
        double y1 = pos_y + correction;
                        
        double x2 = x1 + (c.getWidth() / 100.0)*speed_x;
        double y2 = y1 + (c.getWidth() / 100.0)*speed_y;
        if (speed_x < 0) {
           x2 -= correction;  
           x1 -= correction;  
        }
        else{
           x2 += correction;
           x1 += correction;
        }

        if (speed_y < 0) {
           y2 -= correction;
           y1 -= correction;    
        }
        else{
           y2 += correction;
           y1 += correction;
        }
        ballCollisionLine.setLine(x1, y1, x2, y2);
    }
    
    public Line2D.Double getBallCollisionLine(){
        return ballCollisionLine;
    }
    
    public double getPosX() {
        
        return pos_x + (size / 2);
        
    }
    
    public double getPosY() {
        
        return pos_y + (size / 2);
        
    }

    public double getRealPosX() {
        
        return pos_x;
        
    }
    
    public double getRealPosY() {
        
        return pos_y;
        
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
    
    public Ellipse2D.Double getBall() {
        
        return b;
        
    }

    public void increaseSpeed() {
        
        if (speed_x > 0){
            speed_x += speedIncrement;
        }
        else{
            speed_x -= speedIncrement;
        }
    
        if (speed_y > 0){
            speed_y += speedIncrement;
        }
        else{
            speed_y -= speedIncrement;
        }    
    
        
    }
    
    // checks to see if the ball will collide with it
    // wont need to collide with itself, so will always return false
        
    public boolean detectCollision(Ball b) {
        
        // Debug
        //System.out.println(NAME + ": Cannot detect collision, object cannot collide with itself.");
        return false;
        
    }
    
}
