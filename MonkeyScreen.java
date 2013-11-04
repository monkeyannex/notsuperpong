import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

public class MonkeyScreen extends GameObject{
    
    private Rectangle2D.Double background;
    
    private String monkeyannex;
    
    private Font font;
    private FontMetrics metrics;
    
    private double timer;
    private double length;
    private double UPDATE_TIME;
    
    private Engine engine;
    
    public MonkeyScreen(int objectID, double l, Engine e) {
        
        OID = objectID;
        TYPE = "SPLASHSCREEN";
        NAME = "Monkey Annex Start Screen";
        
        engine = e;
        
        monkeyannex = "MONKEY ANNEX";
        
        background = new Rectangle2D.Double(0,0,0,0);
        
        length = l;
        timer = 0.0;
        
        VISIBLE = true;
        
        // Debug
        System.out.println("Created " + NAME);
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        UPDATE_TIME = (double)engine.getUT() / 1000000000;
        
        if(timer > length) {
            
            // Debug
            System.out.println(NAME + " REMOVED");
            
            engine.removeObject(engine.getObjectIndex(OID));
            
        }
        else {
            
            g.setFont(new Font("default", Font.BOLD, 30));
        
            font = g.getFont();
            metrics = g.getFontMetrics(font);
        
            int ma_width = metrics.stringWidth(monkeyannex);
            int ma_height = metrics.getHeight();
        
            background.setRect(0,0,c.getWidth(),c.getHeight());
        
            Color c1 = new Color(172, 255, 117, 255);
            Color c2 = new Color(88, 189, 21, 255);
        
            GradientPaint bg_gradient = new GradientPaint(c.getWidth()/2,0,c1,c.getWidth()/2,c.getHeight(),c2);
        
            g.setPaint(bg_gradient);
            g.fill(background);
        
            int x = (f.getWidth() / 2) - (ma_width / 2);
            int y = (f.getHeight() / 2) - (ma_height / 2);
        
            g.setColor(Color.black);
            g.drawString(monkeyannex, x , y);
        
        }
        
        // set the splash screen to fade in and out
        //if() {
            
            
            
        //}
        
        timer += UPDATE_TIME;
        
        // Debug
        //System.out.println(OID + ": " + timer);
        
    }

}
