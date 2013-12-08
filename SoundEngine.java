import java.io.*;
import java.io.InputStream;
import javax.swing.SwingWorker;


public class SoundEngine {
    
    // Controls the state of the Sound Engine
    //private boolean RUNNING;
    
    // Amount of time to sleep between Sound Engine cycles
    // In milliseconds
    //private int SLEEP_TIME;

    // The folder that all the game sounds are stored in
    private String FOLDER = "sound/";
    
    private AudioFile SELECT;
    private AudioFile BOOP;
    private AudioFile PADDLE_HIT;
    private AudioFile MENU_BLIP;

    public SoundEngine() {
        
        //RUNNING = true;
        //SLEEP_TIME = 50;
        
        // Initialise all the sounds needed to play
        SELECT = new AudioFile(FOLDER + "select.wav");
        BOOP = new AudioFile(FOLDER + "boop.wav");
        PADDLE_HIT = new AudioFile(FOLDER + "paddle_hit.wav");
        MENU_BLIP = new AudioFile(FOLDER + "menu_blip.wav");
    
    }
    
    public void play(final String toPlay) {
        
        SwingWorker worker = new SwingWorker<Void, Void>() {
        
            @Override
            protected Void doInBackground() throws Exception { 
        
                if(toPlay.equals("select")) SELECT.play(new ByteArrayInputStream(SELECT.getSamples()));
                else if(toPlay.equals("boop")) BOOP.play(new ByteArrayInputStream(BOOP.getSamples()));
                else if(toPlay.equals("paddle_hit")) PADDLE_HIT.play(new ByteArrayInputStream(PADDLE_HIT.getSamples()));
                else if(toPlay.equals("menu_blip")) MENU_BLIP.play(new ByteArrayInputStream(MENU_BLIP.getSamples()));
        
                return null;
        
            }
            
        };
        
        worker.execute();
        
    }

}
