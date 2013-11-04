/*
 * Start Class, that gets everything started
 */

public class Start {

    public static void main(String[] args) {
        
        // Start the Game Engine
        // Debug
        System.out.println("Initialising Sound Engine.");
        Engine engine = new Engine();
        Thread eThread = new Thread(engine);
        eThread.start();
        // Debug
        System.out.println("Sound Engine Started.");
        
        // Start the Sound Engine
        //Debug
        //System.out.println("Initialising Sound Engine.");
        //SoundEngine se = new SoundEngine();
        //Thread seThread = new Thread(se);
        //seThread.start();
        // Debug
        //System.out.println("Sound Engine Started.");
        
        // Pass the Sound Engine to the Game Engine
        //engine.setSEngine(se);
        
        // Start the main Frame
        MainFrame frame = new MainFrame(engine);
        //frame.setVisible(true);
        
    }

}
