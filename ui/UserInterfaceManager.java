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
    private DisplayUpgrade upgrade;
    private MuteMusic mute;
    private Dimension resolution;
    private StartingScreen startingScreen;
    public UserInterfaceManager(Dimension resolution){
        this.resolution=resolution;
        soundTrack=new PlayMusic(); //Soundtrack
        displayCoins=new DisplayCoins(resolution);
        startingScreen=new StartingScreen(resolution);
        pause=new Pause();
        mute=new MuteMusic();
        upgrade=new DisplayUpgrade(resolution);
    }
    public void draw(Graphics2D g2d){
        displayCoins.draw(g2d);
        mute.updateImage();
        upgrade.update(resolution,g2d);
        if(mute.changeState==1){
            soundTrack.change();
            mute.changeState=0;
        }
        if(Engine.gameState==Engine.State.LOADING){
            startingScreen.draw(g2d);
        }
        else if(pause.update()){
            //mute.gamePaused();
            Engine.gameState= Engine.State.PAUSED;
            pause.start(g2d,resolution.width,resolution.height);
        }
        else if(!Engine.gameState.equals(Engine.State.LOADING))
            Engine.gameState= Engine.State.PLAYING;
    }
    public void updateSizes(Dimension resolution){
        this.resolution=resolution;
        displayCoins.updateSizes(resolution);
        startingScreen.updateResolution(resolution);
    }
    public ArrayList<JLabel> getAllButtons(){
        ArrayList<JLabel> buttons=new ArrayList<>();
        buttons.add(mute.getButton());
        buttons.add(upgrade.getButton());
        buttons.add(pause.getButton());
        buttons.add(startingScreen.getButton());
        buttons.add(pause.getUnpauseButton()); //najnizej zeby mozna bylo inne klikac
        return buttons;
    }
}
