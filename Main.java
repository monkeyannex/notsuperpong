public class Main {
    
    //private Player p1;
    //private Player p2;

    public static void main(String[] args) {
    		
        // Create the GameEngine object and start it
		GameEngine engine = new GameEngine();
        Thread eThread = new Thread(engine);
        eThread.start();
        
        // Start the main Frame
        MainFrame mframe = new MainFrame(engine);
        mframe.setVisible(true);
        
        // Start the menu frame
        //MenuPanel menu = new MenuPanel();
        //menu.setVisible(true);
    
    }

}
