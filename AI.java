public class AI extends Player{
    
    
    public void runAI(int xPos, int yPos){
        
       //movePaddle(1);
       setPosX(xPos);
       setPosY(yPos);        
    }
    
    
    public void setPosX(int newX) {
        
        pos_x = newX;
        
    }
    
    public void setPosY(int newY) {
        
        pos_y = newY;
        
    }
  
    
}
