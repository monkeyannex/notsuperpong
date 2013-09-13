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
    
    Rectangle2D.Double paddle;    
    
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
        
        paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        
        solid = true;
        
        // Debug info.
        System.out.println("Created Player " + playerID + ".");
        
    }
    
    public void drawPaddle(Graphics2D g) {
        
        if (playerID == 1) g.setColor(Color.blue);
        else if (playerID == 2) g.setColor(Color.green);
        
        //Rectangle2D.Double paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        paddle.setFrame(pos_x, pos_y, paddleWidth, paddleHeight);
        
        g.fill(paddle);
        
    }
    
    public void movePaddle(int dist) {
        
        // checks to see if the next paddle move will take it off the screen
        // if it does, the paddle will just draw at the edge, no further.
        
        if (pos_y + dist <= 0) pos_y = 0;
        else if (pos_y + dist >= 450 - paddleHeight) pos_y = 450 -paddleHeight;
        else pos_y += dist;
        
    }
    
    public boolean detectCollision(Ball b, Line2D.Double l) {
           
        if (l.intersects(paddle)) {
           
            //b.invertSpeedX();
            //b.increaseSpeed();
            //b.updatePos();
            
            //Move the ball to touch the paddle
            //Then move it the remaining amount in the distance tick
            
            //If the ball is to the left of the paddle
            if (b.getPosX() < pos_x) {
                
                //Get x distance to the paddle - taking off the paddle size so it doesnt go inside
                //double xToPad = pos_x - b.getPosX() - b.getSize()/2;
                double xToPad = pos_x - b.getPosX() - b.getSize();
                //Get remaing distance to travel after collision
                double xFromPad = b.getSpeedX() - xToPad - b.getSize(); 
                
                //If its above and left of the paddle
                if(b.getPosY() < pos_y) {
                    //Move the ball next to the paddle
                    b.updatePos(xToPad);
                    b.invertSpeedY();
                }
                //If its below and left of the paddle
                else if(b.getPosY() > pos_y + paddleHeight) {
                    //Move the ball next to the paddle
                    b.updatePos(xToPad);
                    b.invertSpeedY();
                }
                //Its to the left but next to the paddle
                else {
                    //Move the ball next to the paddle
                    
                    //NOTE THIS SEEMS TO BE WORKING.... NEED TO DO THE TOP/BOTTOM and THE LEFT HAND PADDLE
                    System.out.println("ball speed: " + b.getSpeedX());
                    System.out.println("xToPad: " + xToPad);
                    b.updatePos(xToPad);
                    b.invertSpeedX();
                    //Move the remainder of the distance in the tick
                    b.updatePos(xFromPad);
                    System.out.println("its to the left of the paddle");
                }
            }
            //If the ball is to the right of the paddle
            else if(b.getPosX() > pos_x + paddleWidth) {
                
                //Get x distance to the paddle - taking off the paddle size so it doesnt go inside
                double xToPad = b.getPosX() - (pos_x + paddleWidth);
                //Get remaing distance to travel after collision
                double xFromPad = b.getSpeedX() - xToPad;                
                
                
                //If its above and right of the paddle
                if(b.getPosY() < pos_y) {
                    b.invertSpeedY();
                }
                //If its below and right of the paddle
                else if(b.getPosY() > pos_y + paddleHeight) {
                    b.invertSpeedY();
                }
                //Its to the right but next to the paddle
                else {
                    //Move the ball next to the paddle
                    System.out.println("ball speed: " + b.getSpeedX());
                    System.out.println("xToPad: " + xToPad);
                    b.updatePos(xToPad);
                    b.invertSpeedX();
                    //Move the remainder of the distance in the tick
                    b.updatePos(xFromPad);
                    System.out.println("its to the left of the paddle");
                }    
            }
            
            //Increase the speed of the ball
            b.increaseSpeed();
            return true;
            
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
    
    // checks to see if the ball will collide with it
    public boolean isSolid() {
        
        return solid;
        
    }
    
}
