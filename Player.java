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
    
    public double speed_top;
    public double speed_current;
    public double speed_increment;
    
    // set the size of the paddle in terms of percent of the screen size
    private double hScale = 22.5;
    // set the paddle to 1/n of the paddle height
    private double wScale = 5.0;
    // the y value is complicated, see the calc method for details
    
    public int playerID;
    
    public int score;
    
    private Rectangle2D.Double paddle;
    
    //create the hitbox lines
    private Line2D.Double hitboxLEFT = new Line2D.Double();
    private Line2D.Double hitboxRIGHT = new Line2D.Double();
    private Line2D.Double hitboxTOP = new Line2D.Double();
    private Line2D.Double hitboxBOTTOM = new Line2D.Double();
    
    // Default player constructor
    public Player(int ID,int pID, Canvas canvas) {
        
        c = canvas;
        
        TYPE = "PADDLE";
        
        playerID = pID;
        
        OID = ID;
        NAME = "Player " + playerID;
        
        speed_top = 10.0;
        speed_current = 0.0;
        speed_increment = 0.5;
        
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
        
        int separation = (f.getWidth() / 100) * 10;
        
        if(playerID == 1) x = (f.getWidth() / 2) - (txt_width / 2) - separation;
        else if(playerID == 2) x = (f.getWidth() / 2) - (txt_width / 2) + separation;
        int y = c.getHeight() - (txt_height * 2);
        
        g.setColor(Color.white);
        g.drawString(text1, x , y);
        
        if (playerID == 1) g.setColor(Color.orange);
        else if (playerID == 2) g.setColor(Color.green);
        
        //Rectangle2D.Double paddle = new Rectangle2D.Double(pos_x, pos_y, paddleWidth, paddleHeight);
        paddle.setFrame(pos_x, pos_y, paddleWidth, paddleHeight);
        
        g.fill(paddle);
               
    }
    
    public void incrementSpeedCurrent() {
        
        if(speed_current < 0) {
            
            speed_current += speed_increment;
            if(speed_current > 0) speed_current = 0;
            
        }
        if(speed_current > 0) {
            
            speed_current -= speed_increment;
            if(speed_current < 0) speed_current = 0;
            
        }
        
    }
    
    // Move the paddle a set distance
    public void movePaddle(double dist) {
        
        // checks to see if the next paddle move will take it off the screen
        // if it does, the paddle will just draw at the edge, no further.
        
        if (pos_y + dist <= 0) pos_y = 0;
        else if (pos_y + dist >= c.getHeight() - paddleHeight) pos_y = c.getHeight() -paddleHeight;
        else pos_y += dist;
        
    }
    
    // Move the paddle automatically based off its speed
    public void movePaddle(boolean keyPressed) {
        
        // If the paddle shouldnt be moving, dont move it
        if(speed_current == 0) return;
        
        // If the paddles speed is !0 then move it
        if(speed_current < 0) {
            
            pos_y += speed_current;
            if(!keyPressed) incrementSpeedCurrent();
            
        }
        if(speed_current > 0) {
            
            pos_y += speed_current;
            if(!keyPressed) incrementSpeedCurrent();
            
        }
        
        // Move the paddle, or dont if it will go off screen
        if (pos_y < 0) pos_y = 0;
        else if (pos_y > c.getHeight() - paddleHeight) pos_y = c.getHeight() -paddleHeight;
        
        // Update other variables that rely on position
        pos_x1 = pos_x + paddleWidth;
        pos_y1 = pos_y + paddleHeight;
        
    }
    
    // Set the speed to max
    // Is usually used when play hits the key to move the paddle
    // true is down
    public void setSpeedMax(boolean direction) {
        
        if(!direction)speed_current = speed_top;
        if(direction)speed_current = speed_top;
        
    }
    
    public void setSpeedCurrent(double speed) {
        
        if(speed < 0) speed_current = -speed_top;
        if(speed > 0) speed_current = speed_top;
        if(speed == 0) speed_current = 0;
        
    }
    
    public boolean detectCollision(Ball b) {
        
        //Hitbox collision detection
        hitboxLEFT.setLine(pos_x, pos_y, pos_x, pos_y+paddleHeight);
        hitboxRIGHT.setLine(pos_x+paddleWidth, pos_y, pos_x+paddleWidth, pos_y+paddleHeight);
        hitboxTOP.setLine(pos_x, pos_y, pos_x+paddleWidth, pos_y);
        hitboxBOTTOM.setLine(pos_x, pos_y+paddleHeight, pos_x+paddleWidth, pos_y+paddleHeight);
        //Get the line between the ball and where the ball would go in a tick to collide with
        Line2D.Double ballCollisionLine = b.getBallCollisionLine();
        
        //Only detect if the ball collides with the left hand side if its going right
        if (ballCollisionLine.intersectsLine(hitboxLEFT) && b.getSpeedX() > 0){
            System.out.println("Hit the left hand side of the paddle");
            //X Distance to Paddle from the top left position of the ball
            double xDistToPad = pos_x - b.getPosX();
            //X Distance remaining after hitting the paddle due to its speed
            double xDistAfterPad = (c.getWidth() / 100.0)*b.getSpeedX() - xDistToPad;
            //Update the position of the ball to be next to the paddle
            b.updatePos(xDistToPad);
            //change the balls X direction
            b.invertSpeedX();
            //Move the ball the remaining distance           
            b.updatePos(xDistAfterPad);
            b.increaseSpeed();
            return true;
        }
        //Only detect if the ball collides with the right hand side if its going left
        else if (ballCollisionLine.intersectsLine(hitboxRIGHT) && b.getSpeedX() < 0) {
            System.out.println("Hit the right hand side of the paddle");
            //X Distance to Paddle from the top left of the ball to the RHS of the paddle
            double xDistToPad = (pos_x + paddleWidth) - b.getRealPosX();
            //X Distance remaining after hitting the paddle due to its speed
            double xDistAfterPad = (c.getWidth() / 100.0)*b.getSpeedX() - xDistToPad;
            //Update the position of the ball to be next to the paddle
            b.updatePos(xDistToPad);
            //change the balls X direction
            b.invertSpeedX();
            //Move the ball the remaining distance
            b.updatePos(xDistAfterPad);
            b.increaseSpeed();
            return true;
        }
        else if (ballCollisionLine.intersectsLine(hitboxTOP)) {
            System.out.println("Hit the top of the paddle");
            //Y Distance to Paddle from the top left position of the ball
            double yDistToPad = pos_y - b.getPosY();
            //X Distance remaining after hitting the paddle due to its speed
            double yDistAfterPad = (c.getHeight() / 100.0)*b.getSpeedY() - yDistToPad;
            //Update the position of the ball to be next to the paddle
            b.updatePos(yDistToPad);
            //change the balls Y direction
            b.invertSpeedY();
            //Move the ball the remaining distance           
            b.updatePos(yDistAfterPad);
            b.increaseSpeed();
            return true;
        }
        else if (ballCollisionLine.intersectsLine(hitboxBOTTOM)) {
            System.out.println("Hit the bottom of the paddle");
            //Y Distance to Paddle from the top left of the ball to the RHS of the paddle
            double yDistToPad = (pos_y + paddleHeight) - b.getRealPosY();
            //Y Distance remaining after hitting the paddle due to its speed
            double yDistAfterPad = (c.getHeight() / 100.0)*b.getSpeedY() - yDistToPad;
            //Update the position of the ball to be next to the paddle
            b.updatePos(yDistToPad);
            //change the balls Y direction
            b.invertSpeedY();
            //Move the ball the remaining distance           
            b.updatePos(yDistAfterPad);
            b.increaseSpeed();
            return true;
        }
        //There was no collision
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
        
        // reset the paddle speed
        speed_current = 0.0;
        
    }
    
}
