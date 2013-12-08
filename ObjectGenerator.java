import java.util.Random;

public class ObjectGenerator {
    
    // Set the values for how rare a type of item is
    // 0.0 = will never spawn
    // 100.0 = will always spawn
    private double RARITY_COMMON = 100.0;
    
    // Set up the timer for events
    private double TIMER;
    
    private static Random random;
    private static Engine engine;
    private static Canvas canvas;

    public ObjectGenerator(Engine e, Canvas c) {
    
        engine = e;
        canvas = c;
    
        random = new Random();
        TIMER = 0;
    
    }
    
    public void spawner() {
        
        // Keep the timer accurate
        double UPDATE_TIME = (double)engine.getUT() / 1000000000;
        TIMER += UPDATE_TIME;
        
        if(TIMER > 3.0) {
            
            // Generate a random spot to draw
            int pos_x = random.nextInt(1200) + 200;
            int pos_y = random.nextInt(700) + 100;
            
            engine.addObject(new SimpleObstacle(engine.nextOID(), pos_x, pos_y, canvas, engine));
            
            // Reset the cooldown for spawning an object
            TIMER = 0;
            
        }
        
        
    }

}
