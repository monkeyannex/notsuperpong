/*
 * Game Engine
 *      - Creates a seperate thread so the engine can run while the graphics are displayed and input taken. 
 */
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.*;

public class GameEngine implements Runnable {

        //  Tracks the number of engine loops for debug purposes
        private static int engineLoopCount;
        
        //  The amount of the between Engine Loops in milliseconds
        private static int engineSleep = 25;
        
        private boolean paused;
        
        //  Creates and empty JComponent that will house the MainFrame JComponent.  This allows the engine to repaint the screen every engine cycle
        private JComponent c;
        
        private Ball b;
        
        //private Player p1;
        private AI p1;
        //private Player p2;
        private AI p2;
        
        private ArrayList gameObjects;

        // Default constructor
        public GameEngine() {
            
            gameObjects = new ArrayList();
            
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
                    
                        //b.updatePos();
                        
                        //AI
                        if (p1 != null) {
                            p1.runAI(b.getPosX(), b.getPosY(), b.getSpeedX());
                        }
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
            
            double pos_x = b.getPosX();
            double pos_y = b.getPosY();
            
            if ( pos_x < -10.0 ) {
                
                p2.incrementScore();
                resetGame();              
                //b.invertSpeedX();
                
            }
            
            if ( pos_x > 900.0 ) {
                
                p1.incrementScore();
                resetGame();
                //b.invertSpeedX();
                
            }
            
            // detect collision with the top and bottom of the screen
            if ( pos_y <= 0.0 ) b.invertSpeedY();
            if ( pos_y >= 440.0 ) b.invertSpeedY();
            
            
            //p1.detectCollision(b);
            //p2.detectCollision(b);
            
            boolean hasCollided = false;
            
            // start the collision detection
            for (int x = 0; x < gameObjects.size(); x++) {
                
                double x1 = b.getPosX() + b.getSize()/2;
                double y1 = b.getPosY() + b.getSize()/2;
                
                double correction = b.getSize()/2;
                double x2 = x1 + b.getSpeedX();
                double y2 = y1 + b.getSpeedY();
                if (b.getSpeedX() < 0) {
                    x2 -= correction;    
                }
                else{
                    x2 += correction;
                }

                if (b.getSpeedY() < 0) {
                    y2 -= correction;    
                }
                else{
                    y2 += correction;
                }
                
                //double x2 = x1 + b.getSpeedX();
                //double y2 = y1 + b.getSpeedY();
                
                Line2D.Double ballLine = new Line2D.Double();
                ballLine.setLine(x1, y1, x2, y2);
                
                GameObject obj = (GameObject)gameObjects.get(x);
                
                if (obj.isSolid()) {
                    
                    //if collision with ball/object would occur
                    //move the ball inside the object
                    //the new position is determined by the type of object it hits
                    if (obj.detectCollision(b, ballLine)) {
                        hasCollided = true;
                        break;
                        
                    }
                }
            }
            //move the ball if it doesnt collide
            if (!hasCollided) {
                b.updatePos();    
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
        
        public void setPlayer1(AI p) {
            
            p1 = p;
            
        }

        public void setPlayer2(AI p) {
            
            p2 = p;
            
        }
        
        /*public void setPlayer1(Player p) {
            
            p1 = p;
            
        }

        public void setPlayer2(Player p) {
            
            p2 = p;
            
        }*/
        
        public void addGameObject(Object o) {
            
            gameObjects.add(o);
            
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
