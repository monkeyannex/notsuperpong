// Canvas class.
// Starts a separate thread that will handle all the drawing of the game

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Canvas extends JComponent{

    private Graphics2D g;

    // allows the frame to be passed to other objects for 
    private static MainFrame f;
    // allows the canvas to get info from the engine
    private Engine e;
    
    private ArrayList o;
        
    private Rectangle2D.Double bg;
    
    private boolean debug;
        
    public Canvas(MainFrame frame, Engine engine) {
            
        f = frame;
        e = engine;
        o = e.getObjectList();
            
        bg = new Rectangle2D.Double(0,0,f.getWidth(),f.getHeight());
        
        // Pass the canvas to the engine so it can repaint every loop
        e.setCanvas(this);
        
        debug = true;
        
        // Debug
        System.out.println("Started Canvas.");
            
    }
        
    // does the actual painting
    public void paint(Graphics gg) {
            
        // initialises the double buffering
        g = (Graphics2D) gg;
            
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
        // sets the drawing colour to black, and draws a rectangle as the background
        // resizes the background to always take up the full window
        // always draws as big as the window
        g.setColor(Color.black);
        bg.setFrame(0,0,f.getWidth(),f.getHeight());
        g.fill(bg);
        
        // Main Drawing
        for (int x = 0; x < o.size(); x++) {
        
            GameObject object = (GameObject)o.get(x);
        
            if (object.VISIBLE) {
                    
                object.draw(g, f, this);
                
            }
        
        }
        
        // If debug is enabled, draw the Debug Layer
        if(debug) debug();
    
    }
    
    // Draws debug info
    // Anything drawn here will be drawn over the top of everything else
    private void debug() {
        
        g.setFont(new Font("default", Font.PLAIN, 10));
        
        int Y_SPACING = 15;
        int POS_X = 10;
        int POS_Y = 20;
        
        g.setColor(Color.white);
        
        g.drawString("DEBUG:",POS_X , POS_Y);
        g.drawString("------",POS_X , (POS_Y += Y_SPACING));
        
        
        // Draws window size details
        g.drawString("Window Size: " + f.getWidth() + ", " + f.getHeight(), POS_X, (POS_Y += Y_SPACING));
        
        //Draw UPTIME
        g.drawString("UPTIME: " + e.getUpTimeSimple() + " sec", POS_X, (POS_Y += Y_SPACING));
        
        //Draw FPS
        g.drawString("FPS: " + e.getFPS(), POS_X, (POS_Y += Y_SPACING));
        
        //Draw PHASE
        g.drawString("PHASE: " + e.getPhase() + "    TIME: " + e.getPhaseTimerSimple(), POS_X, (POS_Y += Y_SPACING));
            
    }
    
    public void toggleDebug() {
        
        debug = !debug;
        
    }

}
