package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class CollectedSound{
    private File file;
    private Clip clip;
    public CollectedSound(){
        loadSound();
    }
    private void loadSound(){
        file=new File("src/resources/sounds/coinCollected.wav");
    }
    public void play(){
        try {
            if(clip!=null && !clip.isRunning())
                clip.close();
            AudioInputStream audio= AudioSystem.getAudioInputStream(file);
            clip=AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
