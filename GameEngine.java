/*
 * Game Engine
 *      - Creates a seperate thread so the engine can run while the graphics are displayed and input taken. 
 */

import javax.swing.JComponent;

public class GameEngine implements Runnable {

        //  Tracks the number of engine loops for debug purposes
        private static int engineLoopCount;
        
        //  The amount of the between Engine Loops in milliseconds
        private static int engineSleep = 100;
        
        //  Creates and empty JComponent that will house the MainFrame JComponent.  This allows the engine to repaint the screen every engine cycle
        private JComponent c;

        // Default constructor
        public GameEngine() {
            
            System.out.println("Engine Created.");
            
        }

        // The code to run when the engine starts
        public void run() {
            
            // Debug info
            System.out.println("Engine Started.");	
		
            engineLoopCount = 0;
            
            // Contains the code to run on each loop
            while(true){
			
                // Show engine loop number for debug purposes
                // System.out.println("Engine loop count is " + engineLoopCount);
                //engineLoopCount++;
                
                // repaint the screen, but only if the screen is ready to be repainted.
                if (c != null) {
                    
                    c.repaint();
                    
                }
                else System.out.println("Cannot repaint screen.");
                
                // attempt to sleep the thread
                try {
                    Thread.sleep(engineSleep);
                } 
                catch (InterruptedException ie) {
                    System.out.println(ie);
                }						
            }
            
        }
        
        // Accepts the MainFrame JComponent object, which allows the engine to repaint the screen
        public void setComponent(JComponent component) {
            
            c = component;
            
        }

}
