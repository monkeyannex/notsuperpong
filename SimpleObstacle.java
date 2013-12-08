import java.awt.*;
import java.awt.geom.*;

public class SimpleObstacle extends GameObject {
    
    public Canvas c;
    public Engine e;
    
    private static int TYPE_ID = 1;
    
    public double pos_x;
    public double pos_y;
    public double pos_x1;
    public double pos_y1;
    public double width = 60.0;
    public double height = 100.0;
    
    private Rectangle2D.Double box;
    
    //create the hitbox lines
    private Line2D.Double hitboxLEFT = new Line2D.Double();
    private Line2D.Double hitboxRIGHT = new Line2D.Double();
    private Line2D.Double hitboxTOP = new Line2D.Double();
    private Line2D.Double hitboxBOTTOM = new Line2D.Double();
    
    private double TIMER;

    public SimpleObstacle(int ID, int x, int y, Canvas canvas, Engine engine) {
        
        c = canvas;
        e = engine;
        
        OID = ID;
        TYPE = "OBSTACLE";
        NAME = "Simple Obstacle " + TYPE_ID;
        
        TYPE_ID ++;
        
        pos_x = x;
        pos_y = y;
        
        pos_x1 = pos_x + width;
        pos_y1 = pos_y + height;
        
        box = new Rectangle2D.Double(pos_x, pos_y, width, height);
        
        VISIBLE = true;
        SOLID = true;
        
        TIMER = 0.0;
        
        System.out.println("New " + TYPE + "(" + NAME + ") object created");
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
		
		double UPDATE_TIME = (double)e.getUT() / 1000000000;
        TIMER += UPDATE_TIME;
        
        if(TIMER >= 10.0) {
			
			e.removeObject(e.getObjectIndex(OID));
			return;
			
		}
        
        g.setColor(Color.red);
        
        g.fill(box);
        
    }
    
    public boolean detectCollision(Ball b) {
        
        //Hitbox collision detection
        hitboxLEFT.setLine(pos_x, pos_y, pos_x, pos_y+height);
        hitboxRIGHT.setLine(pos_x+width, pos_y, pos_x+width, pos_y+height);
        hitboxTOP.setLine(pos_x, pos_y, pos_x+width, pos_y);
        hitboxBOTTOM.setLine(pos_x, pos_y+height, pos_x+width, pos_y+height);
        //Get the line between the ball and where the ball would go in a tick to collide with
        Line2D.Double ballCollisionLine = b.getBallCollisionLine();
        
        //Only detect if the ball collides with the left hand side if its going right
        if (ballCollisionLine.intersectsLine(hitboxLEFT) && b.getSpeedX() > 0){
            b.invertSpeedX();
            b.increaseSpeed();
            return true;
        }
        //Only detect if the ball collides with the right hand side if its going left
        else if (ballCollisionLine.intersectsLine(hitboxRIGHT) && b.getSpeedX() < 0) {
            b.invertSpeedX();
            b.increaseSpeed();
            return true;
        }
        else if (ballCollisionLine.intersectsLine(hitboxTOP)) {
            b.invertSpeedY();
            b.increaseSpeed();
            return true;
        }
        else if (ballCollisionLine.intersectsLine(hitboxBOTTOM)) {
            b.invertSpeedY();
            b.increaseSpeed();
            return true;
        }
        //There was no collision
        return false;

    }


}
