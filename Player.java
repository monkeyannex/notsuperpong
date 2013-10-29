/*
 * Player Class controls everything to do with the player
 * - Keeps track of the position and movement of the paddle
*/

import java.awt.*;
import java.awt.geom.*;

public class Player extends GameObject {
    
    // Tracks the player number.  Used in place of a player name at this point. 
    //public static int playerNumber = 1;
    
    // Tracks paddle attributes    
    public double pos_x;
    public double pos_y;
    public double pos_x1;
    public double pos_y1;
    public double paddleWidth;
    public double paddleHeight;
    
    public int playerID;
    
    public int score;
    
    private Rectangle2D.Double paddle;    
    
    // Default player constructor
    public Player(int ID,int pID) {
        
        TYPE = "PADDLE";
        
        playerID = pID;
        
        OID = ID;
        NAME = "Player " + playerID;
        
        if (playerID == 1) {
            
            pos_x = 20.0;
            pos_y = 20.0;
            
        }
        else if (playerID == 2) {
            
            pos_x = 860.0;
            pos_y = 20.0;
            
        }
        
        paddleWidth = 20.0;
        paddleHeight = 100.0;
        
        pos_x1 = pos_x + paddleWidth;
        pos_y1 = pos_y + paddleHeight;
        
        score = 0;
        
        paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        
        VISIBLE = true;
        SOLID = true;
        
        // Debug info.
        System.out.println("Created Player " + playerID + ".");
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        if (playerID == 1) g.setColor(Color.orange);
        else if (playerID == 2) g.setColor(Color.green);
        
        //Rectangle2D.Double paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        paddle.setFrame(pos_x, pos_y, paddleWidth, paddleHeight);
        
        g.fill(paddle);
        
    }
    
    public void movePaddle(double dist) {
        
        // checks to see if the next paddle move will take it off the screen
        // if it does, the paddle will just draw at the edge, no further.
        
        if (pos_y + dist <= 0) pos_y = 0;
        else if (pos_y + dist >= 450 - paddleHeight) pos_y = 450 -paddleHeight;
        else pos_y += dist;
        
    }
    
    public boolean detectCollision(Ball b) {
        
        double bx = b.getPosX();
        double by = b.getPosY();
        
        if(by >= pos_y && by <= pos_y1) {
            
            if(bx >= pos_x && bx <= pos_x1) {
                
                b.invertSpeedX();
                
                return true;
                
            }
            
        }
        
        return false;

    }
    
    public void incrementScore() {
        
        score++;
        
    }
    
    public int getScore() {
        
        return score;
        
    }
    
    public double getPosX() {
        
        return pos_x;
        
    }
    
    public double getPosY() {
        
        return pos_y;
        
    }
    
    public double getPadWidth() {
        
        return paddleWidth;
        
    }
    
    public double getPadHeight() {
        
        return paddleHeight;
        
    }
    
    public Rectangle2D getPaddle() {
        
        return paddle;
    }
    
    public void setPosX(double newX) {
        
        pos_x = newX;
        
    }
    
    public void setPosY(double newY) {
        
        pos_y = newY;
        
    }
    
}
