/*
 * Player Class controls everything to do with the player
 * - Keeps track of the position and movement of the paddle
*/      

public class Player {
    
    // Tracks the player number.  Used in place of a player name at this point. 
    private static int playerNumber = 1;
    
    // Tracks paddle attributes
    private int pos_x;
    private int pos_y;
    private int paddleWidth;
    private int paddleHeight;
    
    // Default player constructor
    public Player() {
        
        if (playerNumber == 1) {
            
            pos_x = 20;
            pos_y = 20;
            
        }
        else if (playerNumber == 2) {
            
            pos_x = 860;
            pos_y = 20;
            
        }
        
        paddleWidth = 20;
        paddleHeight = 100;
        
        // Debug info.
        System.out.println("Created Player " + playerNumber + ".");
        
        playerNumber++;
        
    }
    
    public void movePaddle(int dist) {
        
        pos_y += dist;
        
    }
    
    public int getPosX() {
        
        return pos_x;
        
    }
    
    public int getPosY() {
        
        return pos_y;
        
    }
    
    public int getPadWidth() {
        
        return paddleWidth;
        
    }
    
    public int getPadHeight() {
        
        return paddleHeight;
        
    }
    
}
