/*
 * Game Engine
 *      - Creates a seperate thread so the engine can run while the graphics are displayed and input taken. 
 */
import java.util.Random;
import javax.swing.JComponent;

public class GameEngine implements Runnable {

        //  Tracks the number of engine loops for debug purposes
        private static int engineLoopCount;
        
        //  The amount of the between Engine Loops in milliseconds
        private static int engineSleep = 25;
        
        private boolean paused;
        
        //  Creates and empty JComponent that will house the MainFrame JComponent.  This allows the engine to repaint the screen every engine cycle
        private JComponent c;
        
        private Ball b;
        
        private Player p1;
        //private Player p2;
        private AI p2;

        // Default constructor
        public GameEngine() {
            
            System.out.println("Engine Created.");
            
            paused = false;
            
        }

        // The code to run when the engine starts
        public void run() {
            
            // Debug info
            System.out.println("Engine Started.");	
		
            engineLoopCount = 0;
            
            // Contains the code to run on each loop
            while(true) {
                
                // so only run the main engine while not paused
                if (!paused) {
			
                    // Show engine loop number for debug purposes
                    // System.out.println("Engine loop count is " + engineLoopCount);
                    //engineLoopCount++;
                
                    if (b != null) {
                
                        collisionDetect();
                    
                        b.updatePos();
                        
                        //AI
                        if (p2 != null) {
                            p2.runAI(b.getPosX(), b.getPosY(), b.getSpeedX());
                        }
                                           
                    }
                    else System.out.println("Ball does not exist yet.");
                
                    // repaint the screen, but only if the screen is ready to be repainted.
                    if (c != null) {
                    
                        c.repaint();
                    
                    }
                    else System.out.println("Cannot repaint screen.");
                
                }
                
                // attempt to sleep the thread
                try {
                    Thread.sleep(engineSleep);
                } 
                catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
            
        }
        
        private void collisionDetect() {
            
            int pos_x = b.getPosX();
            int pos_y = b.getPosY();
            
            if ( pos_x < -10 ) {
                
                p2.incrementScore();
                resetGame();              
                //b.invertSpeedX();
                
            }
            
            if ( pos_x > 900 ) {
                
                p1.incrementScore();
                resetGame();
                //b.invertSpeedX();
                
            }
            
            // detect collision with the top and bottom of the screen
            if ( pos_y <= 0 ) b.invertSpeedY();
            if ( pos_y >= 440 ) b.invertSpeedY();
            
            // detect collision with player 1 paddle
            if ( pos_x >= p1.getPosX() -10 && pos_x <= p1.getPosX() + p1.getPadWidth()) {
                
                if (pos_y >= p1.getPosY() && pos_y <= p1.getPosY() + p1.getPadHeight() ) {
                    
                    b.invertSpeedX();
                    b.increaseSpeed();
                
                }
                
            }
            
            // detect collision with player 2 paddle
            if ( pos_x >= p2.getPosX() -10 && pos_x <= p2.getPosX() + p2.getPadWidth()) {
                
                if (pos_y >= p2.getPosY() && pos_y <= p2.getPosY() + p2.getPadHeight() ) {
                    
                    b.invertSpeedX();
                    b.increaseSpeed();
                
                }
                
            }
            
        }
        
        public void togglePause() {
            
            paused = !paused;
            
        }
        
        // Accepts the MainFrame JComponent object, which allows the engine to repaint the screen
        public void setComponent(JComponent component) {
            
            c = component;
            
        }
        
        public void setBall(Ball ball) {
            
            b = ball;
            
        }
        
        public void setPlayer(Player p) {
            
            p1 = p;
            
        }

        public void setAIPlayer(AI p) {
            
            p2 = p;
            
        }
        
        public void resetGame(){
            
            //Define random x/y variables
            Random rand = new Random();
                        
            b.resetPos();
            b.setSpeed(5);
            
            if(rand.nextInt(2) == 1){
                b.invertSpeedX();
            }
            
            if(rand.nextInt(2) == 1){
                b.invertSpeedY();
            }
            
            p1.setPosY(175);
            p2.setPosY(175);
            
        }

}
