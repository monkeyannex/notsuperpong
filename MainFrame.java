/*
 * MainFrame class draws the main panel and accepts keyboard input.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener {
    
    private static String windowTitle = "Not Super Pong";

    private static int width = 900;
    private static int height = 450;
    
    // creates the objects that will handle double buffering
    // this will stop flicker when objects on the screen move
    private static Image dbi;
    private static Graphics dbg;
    
    public static Player p1;
    //public static Player p2;
    public static AI p2;
    
    public static Ball ball;
    
    // create the empty game engine object that is passed to the constructor
    public static GameEngine e;

    // default constructor that accepts the engine object
    public MainFrame(GameEngine engine) {
        
        p1 = new Player();
        //p2 = new Player();
        p2 = new AI();
        
        ball = new Ball();
        
        e = engine;
        
        // setup the Main Frame attributes
        setTitle(windowTitle);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // as the game is first starting, start the main menu
        //MenuPanel menu = new MenuPanel();
        
        // debug info
        System.out.println("Main game frame created.");
        
        // start the main component that everything with be drawn onto
        Component c = new Component();
        
        // pass the component to the engine to allow repainting every engine cycle
        e.setComponent(c);
        
        e.setBall(ball);
        
        e.setPlayer(p1);
        //e.setPlayer(p2);
        e.setAIPlayer(p2);
        
        // not really sure what this does, but i think we need it to actually start the Content Pane in the JFrame
        setContentPane(c);
        
        // starts the ability to accept kestrokes
        this.addKeyListener(this);
    
    }
    
    // actually does the work in starting the drawing component
    static class Component extends JComponent {
        
        public void paint(Graphics g) {
            
            // initialises the double buffering
            if (dbi == null) {
                
                dbi = createImage (width, height);
                dbg = dbi.getGraphics();
                
            }
            
            // draws the background black
            dbg.setColor(Color.black);
            dbg.fillRect(0,0,width, height);
            
            // draw Player 1 paddle
            dbg.setColor(Color.blue);
            dbg.fillRect(p1.getPosX(), p1.getPosY(), p1.getPadWidth(), p1.getPadHeight());
            
            // draw Player 1 score
            dbg.setColor(Color.white);
            dbg.drawString("Player 1: " + p1.getScore(), 100, 20);
            
            // draw Player 2 paddle
            dbg.setColor(Color.green);
            dbg.fillRect(p2.getPosX(), p2.getPosY(), p2.getPadWidth(), p2.getPadHeight());
            
            // draw Player 2 score
            dbg.setColor(Color.white);
            dbg.drawString("Player 2: " + p2.getScore(), 800, 20);
            
            // draw the ball
            dbg.setColor(Color.white);
            dbg.fillOval(ball.getPosX(), ball.getPosY(), ball.getSize(), ball.getSize());
                        
            //draws the initial image to the screen
            g.drawImage(dbi, 0, 0, this);
            
        }
        
    }
    
    // allows code to run on a keypress
    public void keyPressed(KeyEvent evt) {
        
        int key = evt.getKeyCode();
        
        System.out.println("KEYPRESS: " + key);
        
        switch (key) {
            
            // 'w'
            case 87:
            
                p1.movePaddle(-10);
                break;
            
            // 's'    
            case 83:
            
                p1.movePaddle(10);
                break;
            
            // 'o'    
            case 79:
            
                p2.movePaddle(-10);
                break;
            
            // 'l'    
            case 76:
            
                p2.movePaddle(10);
                break;
            
            // escape
            case 27:
                
                e.togglePause();
                break;
        }
        
    }
    
    // allows code to run when a pressed key is released
    public void keyReleased(KeyEvent e) {
        
        
        
    }
    
    // allows code to run when a key is typed
    public void keyTyped(KeyEvent e) {
        
        
        
    }

}
