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
    public double paddleWidth;
    public double paddleHeight;
    
    public int playerID;
    
    public int score;
    
    private boolean solid;
    
    // Default player constructor
    public Player(int ID) {
        
        playerID = ID;
        
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
        
        score = 0;
        
        solid = true;
        
        // Debug info.
        System.out.println("Created Player " + playerID + ".");
        
    }
    
    public void drawPaddle(Graphics2D g) {
        
        if (playerID == 1) g.setColor(Color.blue);
        else if (playerID == 2) g.setColor(Color.green);
        
        Rectangle2D.Double paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        
        g.fill(paddle);
        
    }
    
    public void movePaddle(int dist) {
        
        // checks to see if the next paddle move will take it off the screen
        // if it does, the paddle will just draw at the edge, no further.
        
        if (pos_y + dist <= 0) pos_y = 0;
        else if (pos_y + dist >= 450 - paddleHeight) pos_y = 450 -paddleHeight;
        else pos_y += dist;
        
    }
    
    public void detectCollision(Ball b) {
        
        if ( b.getPosX() >= pos_x - b.getSize() && b.getPosX() <= pos_x + paddleWidth ) {
                
            //if (b.getSpeedX() >= paddleWidth) {
                
                //for (int x = 0; x < b.getSpeedX(); x++) {
                
                    //b.updatePos(1);
                
                    if ( b.getPosY() >= pos_y && b.getPosY() <= pos_y + paddleHeight ) {
                    
                        b.invertSpeedX();
                        b.increaseSpeed();
                
                    }
                
                //}
                
            //}
                
        }
        
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
    
    public void setPosX(double newX) {
        
        pos_x = newX;
        
    }
    
    public void setPosY(double newY) {
        
        pos_y = newY;
        
    }
    
    // checks to see if the ball will collide with it
    public boolean isSolid() {
        
        return solid;
        
    }
    
}
