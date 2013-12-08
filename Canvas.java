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
    
    // Setup the game board details
    private Rectangle2D.Double bg_gradient;
    
    private double cl_width;
    private Rectangle2D.Double center_line;
    
    private Color c1 = new Color(0, 0, 0, 255);
    private Color c2 = new Color(50, 50, 50, 255);
        
    private GradientPaint gradient;
    
    private String game_title = "!SUPER-PONG";
    
    private boolean debug;
        
    public Canvas(MainFrame frame, Engine engine) {
            
        f = frame;
        e = engine;
        o = e.getObjectList();
            
        bg = new Rectangle2D.Double(0,0,f.getWidth(),f.getHeight());
        bg_gradient = new Rectangle2D.Double(0,0,f.getWidth(),f.getHeight());
                
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
        
        // Draw the game board if in a game phase
        if(e.getPhaseNumber() > 10) {
            
            // Draw the background
            gradient = new GradientPaint(f.getWidth()/2,0,c1,f.getWidth()/2,f.getHeight() * 2,c2);
            g.setPaint(gradient);
            bg_gradient.setFrame(0,0,f.getWidth(),f.getHeight());
            g.fill(bg_gradient);
            
            // Draw the center line
            g.setColor(Color.gray);
            cl_width = (f.getWidth() / 100) * 2.5;
            center_line = new Rectangle2D.Double((f.getWidth() / 2) - (cl_width / 2),0 ,cl_width ,f.getHeight());
            g.fill(center_line);
            
            // Draw the game title
            g.setFont(new Font("default", Font.BOLD, 30));
        
            Font font = g.getFont();
            FontMetrics metrics = g.getFontMetrics(font);
        
            int txt_width = metrics.stringWidth(game_title);
            int txt_height = metrics.getHeight();
        
            int x = (f.getWidth() / 2) - (txt_width / 2);
            int y = 40;
        
            g.setColor(Color.white);
            g.drawString(game_title, x , y);
        
        }
        
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
