public class GameObject {
    
    private static String TYPE = "Empty";
    private int ID = 0; 
    
    public boolean isSolid() {
        
        return false;
        
    }
    
    public void detectCollision(Ball b) {
        
        System.out.println("Cannot detect collision, object is empty.");
        
    }
    
    public String getType() {
        
        return TYPE;
        
    }
    
    public int getID() {
        
        return ID;
        
    }

}
