// Creates the main window

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MainFrame extends JFrame implements KeyListener {
    
    private static String TITLE = "!SUPER-PONG";
    private static String VERSION = "0.2.1";

    private static int WIDTH = 900;
    private static int HEIGHT = 450;
    
    private boolean FULLSCREEN = false;
    
    private Engine e;
    
    // create the frame
    public MainFrame(Engine engine) {
        
        e = engine;
        
        setTitle(TITLE + " " + VERSION);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Debug
        System.out.println(TITLE + " " + VERSION);
        System.out.println("Res: " + WIDTH + ", " + HEIGHT);
        
        
        // start the main drawing canvas that everything with be drawn onto
        Canvas c = new Canvas(this, e);
                
        c.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // sets the actual drawing pane
        setContentPane(c);
        
        // starts the ability to accept kestrokes
        this.addKeyListener(this);
        
        pack();
        setVisible(true);
        validate();
        
        
        // Enable fullscreen if needed
        if(FULLSCREEN) {
            
            // NOt sure why or how this works, but it enables fullscreen mode
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
            
        }
        
    }
    
    // allows code to run on a keypress
    public void keyPressed(KeyEvent evt) {
        
        int key = evt.getKeyCode();
        
        if(key == 27) e.togglePause();
        else {
        
            // debug: shows the key pressed
            System.out.println("KEYPRESS: " + key);
        
            e.keyPress(key);
            
        }
        
    }
    
    // allows code to run when a pressed key is released
    public void keyReleased(KeyEvent evt) {
        
        int key = evt.getKeyCode();
        
        // debug: shows the key released
        System.out.println("KEYRELEASED: " + key);
        
        
    }
    
    // allows code to run when a key is typed
    public void keyTyped(KeyEvent evt) {
        
        int key = evt.getKeyCode();
        
        // debug: shows the key typed
        System.out.println("KEYTYPED: " + key);
        
    }

}
