package ui;

import com.sun.jdi.IntegerType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusic implements LineListener{
    //1 ustalam losowo kolejnosc soundstrackow w arraylist moze, i zapetlam
    private String[] fileNames={"BENGIER.wav","DuaLipaLoveAgain.wav","EminemHoudini.wav"};
    private String mainPath="/sounds/";
    //private ArrayList<File> files;
    private ArrayList<URL> urls;
    private Clip clip;
    private int i=0;
    private int pause=0;
    public PlayMusic(){
        randomize();
        play();
    }
    public void play(){
        try {
            if(clip!=null)
                clip.close();
            AudioInputStream audio=AudioSystem.getAudioInputStream(urls.get(i));
            clip=AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audio);
            //if song is unpaused
            if(pause==0)
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
        if (pause==0 && event.getType() == LineEvent.Type.STOP) {
            clip.close();
            i++;
            if (i == urls.size())
                i = 0;
            play();
        }
    }
    //randomize songs queue
    private void randomize(){
        //files= new ArrayList<>();
        urls=new ArrayList<>();
        ArrayList<Integer> numbers=new ArrayList<>();
        Random random=new Random();
        int number;
//        while(files.size()<3){
//            number=random.nextInt(3);
//            if(!numbers.contains(number))
//                files.add(new File(mainPath+fileNames[number]));
//        }
        while(urls.size()<3){
            number=random.nextInt(3);
            if(!numbers.contains(number))
                urls.add(getClass().getResource(mainPath+fileNames[number]));
        }
        System.out.println("SONGS ADDED TO QUEUE");
    }
    //if button was pressed, change song to paused
    public void change(){
        if(clip.isRunning()){
            pause=1;
            clip.stop();
        }
        else if(!clip.isRunning()){
            clip.start();
            pause=0;
        }
    }
}
