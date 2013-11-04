/*
 * Start Class, that gets everything started
 */

public class Start {

    public static void main(String[] args) {
        
        // Start the Game Engine
        // Debug
        System.out.println("Starting Game Engine...");
        Engine engine = new Engine();
        Thread eThread = new Thread(engine);
        eThread.start();
        // Debug
        System.out.println("Initialised Game Engine.");
                
        // Start the main Frame
        MainFrame frame = new MainFrame(engine);
        //frame.setVisible(true);
        
    }

}
