import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.*;

public class Engine implements Runnable {
    
    // PHASE variable controls what the engine will be doing
    // PHASE of 0 - Pause phase.
    // PHASE of 1 - Start phase.  The program is starting for the first time.
    // PHASE of 2 - Pause Menu phase.  Pauses the game, and shows the main menu.
    // PHASE of 10 - Menu Phase.  Is running some kind of menu.
    // PHASE of 100 - Game phase.  Is running a playing game.
    private int PHASE = 0;
    private int PREV_PHASE = 0;
    private boolean FIRST_RUN;
    private boolean RUNNING;
    
    // Phase 100 assets
    private Player p1;
    private Player p2;
    private AI ai1;
    private AI ai2;
    
    private Ball b;
    
    // Set unique ID identifier
    // Every object has a unique ID that can be used to search for a specific object
    private static int OID;
    
    private static int menuOID;
    
    // Sets the desired frame frate
    private long FPS_DESIRED = 60;
    // Holds the current frame rate
    private double FPS_CURRENT = 0;
    
    private ArrayList fps;
    
    // Sets the desired sleep time.
    private long DESIRED_SLEEP_TIME = 1000000000 / FPS_DESIRED;
    
    private long START_TIME;
    private long UPDATE_TIME;
    
    private double TIMER;
    
    private double UPTIME;
    
    // Holds the loop number  
    private long loopNumber = 0;
    
    // Holds all the objects in the game
    // Used for effecient drawing and processing of all objects based on the properties
    private ArrayList objects;
    
    private Canvas c;
    private ObjectGenerator generator;
    private SoundEngine se;
    
    //private MonkeyScreen splash1;
    
    // Constructor
    public Engine() {
        
        UPTIME = 0.0;
        
        FIRST_RUN = true;
        
        RUNNING = true;
        
        START_TIME = System.nanoTime();
        
        // Initialise the basic properties of the engine
        loopNumber = 1;
        TIMER = 0;
        OID = 10000;
        
        objects = new ArrayList();
        // Debug
        System.out.println("Initialised ArrayList(objects).");
        
        fps = new ArrayList();
        // Debug
        System.out.println("Initialised ArrayList(fps).");
        
        // Debug
        System.out.println("Starting Sound Engine...");
        se = new SoundEngine();
        // Debug
        System.out.println("Initialised Sound Engine.");
        
        // Debug
        System.out.println("Starting Object Generator...");
        generator = new ObjectGenerator(this, c);
        // Debug
        System.out.println("Initialised Object Generator.");
                
        // Begin the starting phase
        phaseChange(1);
        
    }
    
    public void run() {
        
        long LAST_LOOP = System.nanoTime();
                
        // The main game engine loop
        while(RUNNING) {
            
            // Calculate how long the last loop took to complete
            long LOOP_START = System.nanoTime();
            UPDATE_TIME = LOOP_START - LAST_LOOP;
            LAST_LOOP = LOOP_START;
            
            UPTIME += (double)UPDATE_TIME / 1000000000;
            
            // Calculate fps
            fpsCalc(UPDATE_TIME);
                        
            // Debug
            //System.out.println("P" + PHASE + " loop begun; #" + loopNumber);
            //System.out.println("  +Start: " + START_TIME);
            
            // Run PHASE 0(Pause phase)
            if(PHASE == 0) {
                
                // Debug
                //System.out.println("Engine Paused.");
                
            }
            
            // Run PHASE 2(Pause with the menu)
            //else if(PHASE == 2) phase2();
            
            // Run PHASE 1(Startup phase)
            else if(PHASE == 1) {
                
                phase1(LOOP_START, UPDATE_TIME);
            
            }
            
            // Run PHASE 10(Menu phase)
            else if(PHASE == 10) {
            
                phase10(LOOP_START, UPDATE_TIME);
                
                // Debug
                //System.out.println("  -Doing nothing at this stage either.");
            
            }
            
            // Run PHASE 100(Game phase)
            else if(PHASE == 100) {
            
                phase100();
                
                // Debug
                //System.out.println("  -Doing nothing at this stage either.");
            
            }
            
            // This makes sure that the max frame rate is enforced
            // It skips the sleep period if the loop has taken longer that the max desired time for a single loop
            long LOOP_TIME = System.nanoTime() - LOOP_START;
            
            // Debug
            //System.out.println("  +End: " + System.nanoTime());
            
            // Sleep the thread
            
            long SLEEP_TIME = 0;
            
            if(LOOP_TIME < DESIRED_SLEEP_TIME) {
                   
                try {
                    
                    // calculates the amount of time the engine needs to sleep for in milliseconds
                    SLEEP_TIME = (DESIRED_SLEEP_TIME - LOOP_TIME) / 1000000;
                    
                    Thread.sleep(SLEEP_TIME);
                } 
                catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            
            }
            
            // Repaint the canvas if the Canvas is not null
            if(c != null) c.repaint();
            else System.out.println("Cannot repaint screen; Canvas not started.");
            
            // Debug
            //System.out.println("Game Objects: " + objects.size());
            
            //RUNNING_TIME = System.nanoTime() - START_TIME;
            
            // Debug
            // Shows the Phase ended, with a loop time, then a sleep time.
            //System.out.println("P" + PHASE + " loop ended; " + (LOOP_TIME / 1000000) + "ms, " + SLEEP_TIME + "ms");
            loopNumber ++;
                         
        }
        
    }
    
    public void phaseChange(int state) {
        
        PHASE = state;
        
        // Debug
        System.out.println("Changing Phase.");
        System.out.println("  +PHASE: " + PHASE);
        
        // Allow the new phase to run initialisation code if required
        FIRST_RUN = true;
        
        return;
        
    }
    
    public void phase1(long LOOP_START, long UPDATE_TIME) {
        
        GameObject splash1 = null;
        //GameObject fadein1 = null;
        
        double splash1_length = 4.0;
        double splash1_fade_length = 0.25;
        
        if(FIRST_RUN && TIMER >= 1.0) {
            
            TIMER = 0.0;
            
            addObject(new MonkeyScreen(nextOID(), splash1_length, this));
            splash1 = (GameObject)objects.get(getObjectIndex("Monkey Annex Start Screen"));
            addObject(new ScreenFade(nextOID(), splash1_fade_length, this, true, 0));
            addObject(new ScreenFade(nextOID(), splash1_fade_length, this, false, splash1_length - splash1_fade_length));
            
            FIRST_RUN = false;
            
        }
        //
        else TIMER += (double)UPDATE_TIME / 1000000000;
        
        //splash1 = (GameObject)objects.get(getObjectIndex("Monkey Annex Start Screen"));
        
        // Debug
        // Show how long the startup phase has been going
        //System.out.println("P1-TIMER: " + TIMER + " secs");
        
        if(TIMER > (splash1_length + 1.0)) {
            
            phaseChange(10);
            
        }
        
        // Debug
        // It allows testing of the main loop mechanic to take
        /*Random random = new Random();
        
        try {
            Thread.sleep(random.nextInt(10));
        } 
        catch (InterruptedException ie) {
            System.out.println(ie);
        }
        
        //System.out.println("  -Doing nothing at this stage.");
        
        return;*/
        
    }
    
    
    
    public void phase10(long LOOP_START, long UPDATE_TIME) {
        
        GameObject mainmenu = new GameObject();
        
        if(FIRST_RUN) {
            
            TIMER = 0.0;
            
            Menu temp1 = new Menu(nextOID(), this);
            addObject(temp1);
            mainmenu = temp1;
            menuOID = mainmenu.getOID();
            
            FIRST_RUN = false;
            
            if(!mainmenu.VISIBLE) mainmenu.setVisable(true);
            
            addObject(new ScreenFade(nextOID(), 0.25, this, true, 0));
            
        }
        //
        else TIMER += (double)UPDATE_TIME / 1000000000;
        
        // Debug
        // Show how long the startup phase has been going
        //System.out.println("P10-TIMER: " + TIMER / 1000000000 + " secs");
        
        // Convert to seconds for ease of use
        //long TIMER_SECS = TIMER / 1000000000;
        
    }
    
    public void phase100() {
        
        if(FIRST_RUN) {
            
            TIMER = 0.0;
            
            if(p1 != null) objects.remove(getObjectIndex(p1.getOID()));
            if(p2 != null) objects.remove(getObjectIndex(p2.getOID()));
            if(ai1 != null) objects.remove(getObjectIndex(ai1.getOID()));
            if(ai2 != null) objects.remove(getObjectIndex(ai2.getOID()));
            if(b != null) objects.remove(getObjectIndex(b.getOID()));
            
            objects.add(new Player(nextOID(), 1, c));
            p1 = (Player)objects.get(getObjectIndex("Player 1"));
            objects.add(new AI(nextOID(), 2, c));
            ai2 = (AI)objects.get(getObjectIndex("Player 2"));
            objects.add(new Ball(nextOID(), c));
            b = (Ball)objects.get(getObjectIndex("Ball"));
            
            resetGame();
            
            FIRST_RUN = false;
            
        }
        //
        else TIMER += (double)UPDATE_TIME / 1000000000;
        
        if (b != null) {
			
			boolean moveKeyPressed = false;
			
			// 
			if(KEY_S || KEY_W) moveKeyPressed = true;
                
            // Update the players paddle position based on its speed
            if(p1 != null) p1.movePaddle(moveKeyPressed);
            if(p2 != null) p2.movePaddle(moveKeyPressed);
            
            // Update the AIs paddle based on how smart it is
            if (ai1 != null) ai1.runAI(b.getPosX(), b.getPosY(), b.getSpeedX());
            if (ai2 != null) ai2.runAI(b.getPosX(), b.getPosY(), b.getSpeedX());
            
            generator.spawner();
            
            collisionDetect();
                                           
        }
        else System.out.println("Ball does not exist yet.");
        
        return;
        
    }
    
    private void collisionDetect() {
            
            double pos_x = b.getPosX();
            double pos_y = b.getPosY();
            
            // detect collision with the top and bottom of the screen
            if ( pos_y <= 0 ) b.invertSpeedY();
            if ( pos_y >= c.getHeight() ) b.invertSpeedY();
            
            // detect whether a player has scored
            if ( pos_x <= - b.getSize() - 10.0 ) {
                
                if(p2 != null) p2.incrementScore();
                if(ai2 != null) ai2.incrementScore();
                resetGame();              
                //b.invertSpeedX();
                
            }
            
            if ( pos_x >= c.getWidth() + b.getSize() + 10.0 ) {
                
                if(p1 != null) p1.incrementScore();
                if(ai1 != null) ai1.incrementScore();
                resetGame();
                //b.invertSpeedX();
                
            }
            
            boolean hasCollided = false;
            
            //update the collision line of the ball
            b.createBallCollisionLine(c);
            
            // start the collision detection
            for (int x = 0; x < objects.size(); x++) {
                
                GameObject obj = (GameObject)objects.get(x);
                
                if (obj.isSolid()) {
                    
                    hasCollided = obj.detectCollision(b);
                    
                    if(hasCollided) {
                        
                        se.play("paddle_hit");
                        break;
                        
                    } 
                    
                }
            }
            //move the ball if it doesnt collide
            b.updatePos(c);
  
        }
    
    public void resetGame(){
            
            //Define random x/y variables
            Random rand = new Random();
                        
            b.resetPos(c);
            b.resetSpeed();
            
            if(rand.nextInt(2) == 1){
                b.invertSpeedX();
            }
            
            if(rand.nextInt(2) == 1){
                b.invertSpeedY();
            }
            
            p1.resetPaddle(c);
            ai2.resetPaddle(c);
            
        }
    
    // FPS calculator
    private void fpsCalc(long UPDATE_TIME) {
        
        long TIME = 0;
        
        fps.add(UPDATE_TIME);
        
        for(int x = 0; x < fps.size(); x++) {
            
            TIME += (long)fps.get(x);
            
        }
        
        long FRAME_AVG = TIME / fps.size();
        
        FPS_CURRENT = 1000000000 / FRAME_AVG;
        
        if(TIME >= 1000000000) fps.remove(0);
        
        // Debug
        //System.out.println("  +FPS: " + FPS_CURRENT);
        
    }
    
    // Return the current frame rate
    public double getFPS() {
        
            return FPS_CURRENT;
        
    }
    
    // Swtiches the engine state to paused.
    public void togglePause() {
        
        // If the game isnt paused, then pause it, while keeping track of the previous state so the current phase can be preserved.
        if(PHASE != 0) {
            
            PREV_PHASE = PHASE;
            PHASE = 0;
            
            if(PREV_PHASE == 100) {
                
                objects.add(new Menu(nextOID(), this));
                
            }
            
            // Debug
            System.out.println("Engine Paused.");
            
        }
        else {
            
            PHASE = PREV_PHASE;
            
            // Theoretically kill the menu, which should be the last object in the list
            removeObject(objects.size() - 1);
            
            // Debug
            System.out.println("Engine Un-Paused.");
            
        }
            
    }
    
    // Add an object to the list of game objects
    public void addObject(Object obj) {
        
        objects.add(obj);
        
        return;
        
    }
    
    // Increase the unique object ID number then return it.
    public int nextOID() {
        
        OID++;
        
        return OID;
        
    }
    
    public void setCanvas(Canvas canvas) {
        
        c = canvas;
        
    }
    
    public ArrayList getObjectList() {
        
        return objects;
        
    }
    
    // Returns the position in the Game Object list via its OID
    public int getObjectIndex(int OID) {
        
        for (int x = 0; x < objects.size(); x++) {
        
            GameObject object = (GameObject)objects.get(x);
        
            if (object.getOID() == OID) return x;
        
        }
        
        // if the object doesnt exist, return a huge number
        // not sure to get around this in a nicer fashion
        // will deal with it later
        return 999999999;
        
    }
    
    // Returns the position in the Game Object list via its NAME
    public int getObjectIndex(String n) {
        
        for (int x = 0; x < objects.size(); x++) {
        
            GameObject object = (GameObject)objects.get(x);
        
            if (object.getName().equals(n)) return x;
        
        }
        
        // if the object doesnt exist, return a huge number
        // not sure to get around this in a nicer fashion
        // will deal with it later
        return 999999999;
        
    }
    
    public String getPhase() {
        
        if(PHASE == 0) return "PAUSE";
        if(PHASE == 1) return "STARTUP";
        if(PHASE == 10) return "MENU";
        if(PHASE == 100) return "GAME";
        return "NULL";
        
    }
    
    public int getPhaseNumber() {
        
        return PHASE;
        
    }
    
    // Key stoke booleans
    private boolean KEY_W = false;	// Player 1 'up'
    private boolean KEY_S = false;	// Player 1 'down'
    
    public void keyPress(int key) {
        
        // Global controls
        switch (key) {
            
            // 'TILDE'    
            case 192:
                c.toggleDebug();                
                break;
            
        }
        
        // Controls specific to Phase 10
        if (PHASE == 0 || PHASE == 10) {
            
            Menu m = (Menu)objects.get(getObjectIndex("Main Menu"));
            
            switch (key) {
            
                // 'DOWN'
                case 38:
                    m.prevMenuOpt();
                    se.play("menu_blip");
                    break;
            
                // 'UP'    
                case 40:
                    m.nextMenuOpt();
                    se.play("menu_blip");
                    break;
 
                // 'ENTER'    
                case 10:
                    if(m.getSelected().equals("Exit")) {
                        
                        se.play("select");
                        System.exit(0);
                        
                    }
                    else if(m.getSelected().equals("New Game")) {
                        
                        removeObject(getObjectIndex(m.getOID()));
                        phaseChange(100);
                        se.play("select");
                        
                    }
                    else if(m.getSelected().equals("Continue")) {
                        
                        if(PHASE == 0) {
                            
                            togglePause();
                            se.play("select");
                            
                        }
                        
                    }
                    break;
                
            }

        }
        
        else if(PHASE == 100) {
            
            switch(key) {
                
                case 87:
                    if(p1.getType().equals("PADDLE")) {
						KEY_S = true;
						p1.setSpeedCurrent(-1);
					}
                    break;
                    
                case 83:
                    if(p1.getType().equals("PADDLE")) {
						KEY_W = true;
						p1.setSpeedCurrent(1);
					}
                    break;
                
            }
            
        }
        
    }
    
    public void keyRelease(int key) {
		
		if(PHASE == 100) {
            
            switch(key) {
                
                case 87:
                    if(p1.getType().equals("PADDLE")) KEY_S = false;//p1.setSpeedCurrent(-1);
                    break;
                    
                case 83:
                    if(p1.getType().equals("PADDLE")) KEY_W = false;//p1.setSpeedCurrent(1);
                    break;
                
            }
            
        }
		
	}
    
    public long getUT() {
        
        return UPDATE_TIME;
        
    }
    
    // Return the total running time in a simple way X.XX
    // NOT FOR ACCURATE CALCULATIONS
    public double getUpTimeSimple() {
        
        double UPTIME_SIMPLE = UPTIME;
        
        UPTIME_SIMPLE = Math.round(UPTIME_SIMPLE * 100);
        UPTIME_SIMPLE = UPTIME_SIMPLE / 100;
        
        return UPTIME_SIMPLE;
        
    }
    
    // Return the total running time
    public double getUpTime() {
        
        return UPTIME;
        
    }
    
    // Return the running time of a phase in a simple way X.XX
    // NOT FOR ACCURATE CALCULATIONS
    public double getPhaseTimerSimple() {
        
        double TIMER_SIMPLE = TIMER;
        
        TIMER_SIMPLE = Math.round(TIMER_SIMPLE * 100);
        TIMER_SIMPLE = TIMER_SIMPLE / 100;
        
        return TIMER_SIMPLE;
        
    }
    
    // Return the total running time
    public double getPhaseTimer() {
        
        return TIMER;
        
    }
    
    // removes an object based on its index
    public void removeObject(int index) {
        
        objects.remove(index);
        objects.trimToSize();
        
    }

}
