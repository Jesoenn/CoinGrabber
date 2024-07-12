package ui;

import core.MouseReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MuteMusic {
    private ImageIcon muteLightIcon,muteDarkIcon,unmuteLightIcon,unmuteDarkIcon;
    private JLabel button;
    private final String mainPath="/buttons/";
    private boolean buttonPressed=false;
    private String musicState="playing",prevMusicState="playing";
    public int changeState=0; //operator to change from playing to stopped
    private MouseReader mouseListener;
    public MuteMusic(){
        mouseListener=new MouseReader();
        button=new JLabel();
        downloadImages();
        button.setIcon(muteDarkIcon);
        button.setBounds(70,10,50,50);
        button.addMouseListener(mouseListener);
    }
    private void downloadImages(){
        muteDarkIcon=new ImageIcon(getClass().getResource(mainPath+"mute/Default.png"));
        muteLightIcon=new ImageIcon(getClass().getResource(mainPath+"mute/Hover.png"));
        unmuteDarkIcon=new ImageIcon(getClass().getResource(mainPath+"mute/Default2.png"));
        unmuteLightIcon=new ImageIcon(getClass().getResource(mainPath+"mute/Hover2.png"));
    }
    public JLabel getButton(){
        return button;
    }
    public String updateImage(){
        //pierwsze wcisniecie
        if(!buttonPressed && mouseListener.pressed && musicState.equals("playing")){
            button.setIcon(muteLightIcon);
            buttonPressed=true;
        }
        //trzymanie guzika
        else if(buttonPressed && !mouseListener.pressed && musicState.equals("playing")){
            button.setIcon(unmuteDarkIcon);
            System.out.println("ZATRZYMANE");
            musicState="stopped";
            buttonPressed=false;
        }
        //puszczenie
        else if(!buttonPressed && mouseListener.pressed && musicState.equals("stopped")){
            buttonPressed=true;
            button.setIcon(unmuteLightIcon);
        }
        //drugie nacisniecie
        else if(buttonPressed && !mouseListener.pressed && musicState.equals("stopped")){
            buttonPressed=false;
            button.setIcon(muteDarkIcon);
            musicState="playing";
            System.out.println("WZNOWIONE");
        }
        //trzymanie drugie
        if(!prevMusicState.equals(musicState)){
            prevMusicState=musicState;
            changeState=1;
        }
        return musicState;
    }
}
