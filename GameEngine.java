import javax.swing.JComponent;

public class GameEngine implements Runnable {

        private static int engineLoopCount;
        private static int engineSleep = 100;
        
        private JComponent c;

        public GameEngine() {
            
            System.out.println("Engine Created.");
            
        }

        public void run() {
            
            System.out.println("Engine Started.");	
		
            engineLoopCount = 0;
            // Game Engine loop
            while(true){
			
                // Show engine loop number for debug purposes
                // System.out.println("Engine loop count is " + engineLoopCount);
                //engineLoopCount++;
                
                // repaint the screen, but only if the screen is ready to be repainted.
                if (c != null) {
                    
                    c.repaint();
                    
                }
                
                try {
                    Thread.sleep(engineSleep);
                } 
                catch (InterruptedException ie) {
                    System.out.println(ie);
                }						
            }
            
        }
        
        public void setComponent(JComponent component) {
            
            c = component;
            
        }

}
