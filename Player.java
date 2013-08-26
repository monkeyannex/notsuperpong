public class Player {
    
    private static int playerNumber = 1;
    
    private int pos_x;
    private int pos_y;
    
    private int paddleWidth;
    private int paddleHeight;
    
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
