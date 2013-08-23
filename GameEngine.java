import javax.swing.*;

public class GameEngine {
	
	private int engineLoopCount;
	private int engineSleep;
	
	public GameEngine(){
		engineSleep = 100;
		initialiseEngine();
		
	}
		
	private void initialiseEngine(){
		System.out.println("Game Engine Created");	
		
		
		
		engineLoopCount = 0;
		//Game Engine loop
		while(true){
			
			System.out.println("Engine count is " + engineLoopCount);
			engineLoopCount++;
			try {
				Thread.sleep(engineSleep);
			} 
			catch (InterruptedException ie) {
				System.out.println(ie);
			}						
		}		
	}
	
	
	
	public void setEngineSleep(int sleeptime){
		engineSleep = sleeptime;
	}
}
