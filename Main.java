import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    public Main() {
        
       setTitle("Not Super Pong");
       setSize(900, 450);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);   
       
		MyRunnable myRunnable = new MyRunnable();
		Thread myThread = new Thread(myRunnable);
		myThread.start();
		        
    }
    

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    
    }
}

class MyRunnable implements Runnable {
	public void run(){
		while(true){
			GameEngine game = new GameEngine();
		}
	}
}
