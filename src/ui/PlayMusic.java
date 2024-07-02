package ui;

import com.sun.jdi.IntegerType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusic implements LineListener{
    //1 ustalam losowo kolejnosc soundstrackow w arraylist moze, i zapetlam
    private String[] fileNames={"BENGIER.wav","DuaLipaLoveAgain.wav","EminemHoudini.wav"};
    private String mainPath="src/resources/sounds/";
    private ArrayList<File> files;
    private Clip clip;
    private int i=0;
    public PlayMusic(){
        randomize();
        play();
    }
    public void play(){
        try {
            if(clip!=null)
                clip.close();
            AudioInputStream audio=AudioSystem.getAudioInputStream(files.get(i));
            clip=AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audio);
            clip.start();
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(LineEvent event){
        if (event.getType() == LineEvent.Type.STOP) {
            clip.close();
            i++;
            if (i == files.size())
                i = 0;
            play();
        }
    }
    private void randomize(){
        files= new ArrayList<>();
        ArrayList<Integer> numbers=new ArrayList<>();
        Random random=new Random();
        int number;
        while(files.size()<3){
            number=random.nextInt(3);
            if(!numbers.contains(number))
                files.add(new File(mainPath+fileNames[number]));
        }
        System.out.println("SONGS ADDED");
    }
}
