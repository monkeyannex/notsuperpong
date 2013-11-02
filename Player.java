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
    public double paddleWidth = 10;
    public double paddleHeight = 10;
    
    // set the size of the paddle in terms of percent of the screen size
    private double hScale = 22.5;
    // set the paddle to 1/n of the paddle height
    private double wScale = 5.0;
    // the y value is complicated, see the calc method for details
    
    public int playerID;
    
    public int score;
    
    private Rectangle2D.Double paddle;    
    
    // Default player constructor
    public Player(int ID,int pID, Canvas canvas) {
        
        c = canvas;
        
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
        
        calcPos(c);
        
        // draw some info like player name and score
        String text1 = NAME + ": " + score;
        g.setFont(new Font("default", Font.BOLD, 16));
        
        Font font = g.getFont();
        FontMetrics metrics = g.getFontMetrics(font);
        
        int txt_width = metrics.stringWidth(text1);
        int txt_height = metrics.getHeight();
        
        int x = 0;
        
        int separation = 80;
        
        if(playerID == 1) x = (f.getWidth() / 2) - (txt_width / 2) - separation;
        else if(playerID == 2) x = (f.getWidth() / 2) - (txt_width / 2) + separation;
        int y = c.getHeight() - (txt_height * 2);
        
        g.setColor(Color.gray);
        g.drawString(text1, x , y);
        
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
        else if (pos_y + dist >= c.getHeight() - paddleHeight) pos_y = c.getHeight() -paddleHeight;
        else pos_y += dist;
        
    }
    
    public boolean detectCollision(Ball b) {
        
        double bx = b.getPosX();
        double by = b.getPosY();
        
        pos_x1 = pos_x + paddleWidth;
        pos_y1 = pos_y + paddleHeight;
        
        if(by >= pos_y && by <= pos_y1) {
            
            if(bx >= pos_x && bx <= pos_x1) {
                
                b.invertSpeedX();
                b.increaseSpeed();
                
                return true;
                
            }
            
        }
        
        return false;

    }
    
    private void calcPos(Canvas c) {
        
        // calculate the correct dimensions of the paddle based on the size of the screen
        paddleHeight = (c.getHeight() / 100.0) * hScale;
        paddleWidth = paddleHeight / wScale;
        
        // set its correct position of 1 paddle width from the edge of the screen
        // the 2 * width is because it needs to account for its own width as well as the gap to the edge of the screen
        if(playerID == 1) pos_x = paddleWidth;
        else if(playerID == 2) pos_x = c.getWidth() - (2 * paddleWidth);
        
        // calculate the y position
        // the position of the paddle is a percentage of the total screen height
        
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
    
    public void resetPaddle(Canvas c) {
        
        // set the paddle back to its normal position
        if(playerID == 1) pos_x = paddleWidth;
        else if(playerID == 2) pos_x = c.getWidth() - (2.0 * paddleWidth);
        
        // set the paddle to the halfway point of the screen
        pos_y = (c.getHeight() / 2.0) - (paddleHeight / 2.0);
        
    }
    
}
