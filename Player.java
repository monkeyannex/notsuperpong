/*
 * Player Class controls everything to do with the player
 * - Keeps track of the position and movement of the paddle
*/

import java.awt.*;
import java.awt.geom.*;

public class Player {
    
    // Tracks the player number.  Used in place of a player name at this point. 
    //public static int playerNumber = 1;
    
    // Tracks paddle attributes
    //public int pos_x;
    //public int pos_y;
    //public int paddleWidth;
    //public int paddleHeight;
    
    public double pos_x;
    public double pos_y;
    public double paddleWidth;
    public double paddleHeight;
    
    public int playerID;
    
    public int score;
    
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
        
        // Debug info.
        System.out.println("Created Player " + playerID + ".");
        
        //playerNumber++;
        
    }
    
    public void drawPaddle(Graphics2D g) {
        
        if (playerID == 1) g.setColor(Color.blue);
        else if (playerID == 2) g.setColor(Color.green);
        
        Rectangle2D.Double paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        
        g.fill(paddle);
        
    }
    
    public void movePaddle(int dist) {
        
        pos_y += dist;
        
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
    
}
