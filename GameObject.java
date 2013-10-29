import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GameObject {
    
    public String TYPE = "Empty";
    public String NAME = "Unnamed";
    public int OID = 0;
    
    public boolean VISIBLE = false;
    public boolean SOLID = false;
    
    public boolean isSolid() {
        
        if(SOLID) return true;
        return false;
        
    }
    
    public boolean detectCollision(Ball b) {
        
        System.out.println(NAME + ": Cannot detect collision, object is empty.");
        return false;
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        System.out.println("Cannot draw object, it is empty.");
        
    }
    
    public String getType() {
        
        return TYPE;
        
    }
    
    public String getName() {
        
        return NAME;
        
    }
    
    public int getOID() {
        
        return OID;
        
    }
    
    public boolean isVisable() {
        
        if(VISIBLE) return true;
        return false;
        
    }
    
    public void setVisable(boolean state) {
        
        VISIBLE = state;
        
    }
    
    public void setSolid(boolean state) {
        
        SOLID = state;
        
    }

}
