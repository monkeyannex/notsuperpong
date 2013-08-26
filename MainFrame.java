import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener {
    
    private static String windowTitle = "Not Super Pong";

    private static int width = 900;
    private static int height = 450;
    
    private static Image dbi;
    private static Graphics dbg;
    
    public static Player p1;
    public static Player p2;
    
    public static GameEngine e;

    public MainFrame(GameEngine engine) {
        
        p1 = new Player();
        p2 = new Player();
        
        e = engine;
        
        setTitle(windowTitle);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Container mainPane = this.getContentPane();
        //mainPane.setBackground(Color.black);
        
        MenuPanel menu = new MenuPanel();
        
        System.out.println("Main game frame created.");
        
        Component c = new Component();
        
        // pass the component to the engine for repainting
        e.setComponent(c);
        
        setContentPane(c);
        
        this.addKeyListener(this);
        //this.requestFocus();
    
    }
    
    static class Component extends JComponent {
        
        public void paint(Graphics g) {
            
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
            
            // draw Player 2 paddle
            dbg.setColor(Color.green);
            dbg.fillRect(p2.getPosX(), p2.getPosY(), p2.getPadWidth(), p2.getPadHeight());
            
            g.drawImage(dbi, 0, 0, this);
            
        }
        
    }
    
    public void keyPressed(KeyEvent e) {
        
        char key = e.getKeyChar();
        
        System.out.println("KEYPRESS: " + key);
        
        switch (key) {
            
            case 'w':
            
                p1.movePaddle(-10);
                break;
                
            case 's':
            
                p1.movePaddle(10);
                break;
                
            case 'o':
            
                p2.movePaddle(-10);
                break;
                
            case 'l':
            
                p2.movePaddle(10);
                break;
            
        }
        
    }
    
    public void keyReleased(KeyEvent e) {
        
        
        
    }
    
    public void keyTyped(KeyEvent e) {
        
        
        
    }

}
