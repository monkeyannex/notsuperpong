public class Ball {
    
    private int pos_x;
    private int pos_y;
    
    private int size;
    
    private int speed;
    
    public Ball() {
        
        pos_x = 445;
        pos_y = 220;
        
        size = 10;
        
        speed = 2;
        
        System.out.println("Ball created.");
        
    }
    
    public void updatePos() {
        
        pos_x += speed;
        
        pos_y += speed;
        
    }
    
    public int getPosX() {
        
        return pos_x;
        
    }
    
    public int getPosY() {
        
        return pos_y;
        
    }
    
    public int getSize() {
        
        return size;
        
    }
    
}
