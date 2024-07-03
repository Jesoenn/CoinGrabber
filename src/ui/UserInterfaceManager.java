package ui;

import core.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserInterfaceManager {
    private PlayMusic soundTrack;
    private MyFont newFont=new MyFont();
    private DisplayCoins displayCoins;
    private Pause pause;
    private MuteMusic mute;
    private Dimension resolution;
    public UserInterfaceManager(Dimension resolution){
        this.resolution=resolution;
        soundTrack=new PlayMusic(); //Soundtrack
        displayCoins=new DisplayCoins(resolution);
        pause=new Pause();
        mute=new MuteMusic();
    }
    public void draw(Graphics2D g2d){
        displayCoins.draw(g2d);
        mute.updateImage();
        if(mute.changeState==1){
            soundTrack.change();
            mute.changeState=0;
        }
        if(pause.update()==true){
            //mute.gamePaused();
            Engine.gameState= Engine.State.PAUSED;
            pause.start(g2d,resolution.width,resolution.height);
        }
        else
            Engine.gameState= Engine.State.PLAYING;
    }
    public void updateSizes(Dimension resolution){
        this.resolution=resolution;
        displayCoins.updateSizes(resolution);
    }
    public ArrayList<JLabel> getAllButtons(){
        ArrayList<JLabel> buttons=new ArrayList<>();
        buttons.add(mute.getButton());
        buttons.add(pause.getButton());
        buttons.add(pause.getUnpauseButton());
        return buttons;
    }
}
