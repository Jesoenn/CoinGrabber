package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CollectedSound{
    //private File file;
    private Clip clip;
    URL url;
    public CollectedSound(){
        loadSound();
    }
    private void loadSound(){
        url=getClass().getResource("/sounds/coinCollected.wav");
        //file=new File("src/resources/sounds/coinCollected.wav");
    }
    public void play(){
        try {
            if(clip!=null && !clip.isRunning())
                clip.close();
            AudioInputStream audio= AudioSystem.getAudioInputStream(url); //changed from file to inputstream
            clip=AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
