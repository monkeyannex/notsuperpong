import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

public class ScreenFade extends GameObject{
    
    private Rectangle2D.Double fade;
    
    private Engine e;

    // sets how long the fade should go for
    private double length;
    private double timer;
    
    private float alpha;
    
    // stores fade direction.  true == fade in, false == fade out
    private boolean fadein;

    public ScreenFade(int objectID, double l, Engine engine, boolean fi) {
    
        e = engine;
        
        TYPE = "EFFECT";
        NAME = "Screen Fade";
    
        OID = objectID;
        length = l;
        
        fadein = fi;
        
        if(fadein) alpha = 1;
        else alpha = 0;
        
        fade = new Rectangle2D.Double(0,0,0,0);
        
        timer = 0;
        
        VISIBLE = true;
        
        // Debug
        System.out.println("Created " + NAME);
    
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        double UPDATE_TIME = (double)e.getUT() / 1000000000;
        timer += UPDATE_TIME;
        
        // once th fade has completed remove it from the object list
        if(timer >= length) {
            
            // Debug
            System.out.println("FADE REMOVED");
            
            e.removeObject(e.getObjectIndex(OID));
            
        }
        else {
        
            fade.setRect(0,0,c.getWidth(),c.getHeight());
            
            double percent = UPDATE_TIME / length;
            
            if(fadein) alpha -= (float)percent;
            else alpha += (float)percent;
            
            if(alpha > 1) alpha = 1;
            if(alpha < 0) alpha = 0;
            
            Color blackFade = new Color(0,0,0,alpha);
        
            g.setColor(blackFade);
            g.fill(fade);
            
        }
        
    }
     
     

}
