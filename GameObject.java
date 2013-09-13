import java.awt.*;
import java.awt.geom.*;

public class GameObject {
    
    private static String TYPE = "Empty";
    private int ID = 0; 
    
    public boolean isSolid() {
        
        return false;
        
    }
    
    public boolean detectCollision(Ball b, Line2D.Double l) {
        
        System.out.println("Cannot detect collision, object is empty.");
        return false;
    }
    
    public String getType() {
        
        return TYPE;
        
    }
    
    public int getID() {
        
        return ID;
        
    }

}
