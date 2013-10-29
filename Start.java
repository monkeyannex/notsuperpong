/*
 * Start Class, that gets everything started
 */

public class Start {

    public static void main(String[] args) {
        
        // Start the game engine
        Engine engine = new Engine();
        Thread eThread = new Thread(engine);
        eThread.start();
        
        // Start the main Frame
        MainFrame frame = new MainFrame(engine);
        //frame.setVisible(true);
        
    }

}
