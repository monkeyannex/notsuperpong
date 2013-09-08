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
    
    //public static Player p1;
    public static AI p1;
    //public static Player p2;
    public static AI p2;
    
    public static Ball ball;
    
    // create the empty game engine object that is passed to the constructor
    public static GameEngine e;

    // default constructor that accepts the engine object
    public MainFrame(GameEngine engine) {
        
        //p1 = new Player(1);
        p1 = new AI(1);
        //p2 = new Player(2);
        p2 = new AI(2);
        
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
        
        e.setPlayer1(p1);
        //e.setPlayer(p2);
        e.setPlayer2(p2);
        
        // not really sure what this does, but i think we need it to actually start the Content Pane in the JFrame
        setContentPane(c);
        
        // starts the ability to accept kestrokes
        this.addKeyListener(this);
    
    }
    
    // actually does the work in starting the drawing component
    static class Component extends JComponent {
        
        public void paint(Graphics gg) {
            
            // initialises the double buffering
            Graphics2D g = (Graphics2D) gg;
            
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            /*
             * Background Layer
             */
              
            // draws the background black
            g.setColor(Color.black);
            g.fillRect(0,0,width, height);
            
            // draw a debug grid over the background
            debugGrid(g);
            
            // draw Player 1 paddle
            p1.drawPaddle(g);
            
            // draw Player 1 score
            g.setColor(Color.white);
            g.drawString("Player 1: " + p1.getScore(), 100, 20);
            
            // draw Player 2 paddle
            p2.drawPaddle(g);
            
            // draw Player 2 score
            g.setColor(Color.white);
            g.drawString("Player 2: " + p2.getScore(), 800, 20);
            
            // draw the ball
            ball.drawBall(g);
            
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
    
    // draws a grid over the background
    // used for debug purposes
    private static void debugGrid(Graphics2D g) {
        
        int vertLinePos = 449;
        int horiLinePos = 224;
        int lineWidth = 2;
        int lineSpacing = 50;
        
        // grid colour
        g.setColor(Color.red);
        
        // center lines
        g.fillRect(vertLinePos,0,lineWidth, height);
        g.fillRect(0, horiLinePos, width, lineWidth);
        
        vertLinePos = 449 - lineSpacing;
        horiLinePos = 224 - lineSpacing;
        
        g.setColor(Color.gray);
        // draw lines to the left of the center point
        while ( vertLinePos > 0 ) {
            
            g.fillRect(vertLinePos,0,lineWidth, height);
            vertLinePos -= lineSpacing;
            
        }
        
        vertLinePos = 449 + lineSpacing;
        
        // draw lines to the right of the center point
        while ( vertLinePos < width ) {
            
            g.fillRect(vertLinePos,0,lineWidth, height);
            vertLinePos += lineSpacing;
            
        }
        
        // draw lines above the center point
        while ( horiLinePos > 0 ) {
            
            g.fillRect(0, horiLinePos, width, lineWidth);
            horiLinePos -= lineSpacing;
            
        }
        
        horiLinePos = 224 + lineSpacing;
        
        // draw lines below the center point
        while ( horiLinePos < width ) {
            
            g.fillRect(0, horiLinePos, width, lineWidth);
            horiLinePos += lineSpacing;
            
        }
        
        
    }

}
