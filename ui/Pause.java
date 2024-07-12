package ui;

import core.MouseReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Pause {
    private ImageIcon pauseLight,pauseDark;
    private boolean buttonPressed=false;
    private MouseReader mouseReader=new MouseReader();
    private MouseReader unpauseReader=new MouseReader();
    private JLabel button,unpauseButton;
    private final String mainPath="/buttons/pause/";
    private boolean gamePaused;
    public Pause(){
        downloadImages();
        mouseReader=new MouseReader();
        button=new JLabel();
        downloadImages();
        button.setIcon(pauseDark);
        button.setBounds(10,10,50,50);
        button.addMouseListener(mouseReader);
        unpauseButton=new JLabel();
        unpauseButton.setBounds(-100,-100,1,1);
        unpauseButton.addMouseListener(unpauseReader);
        gamePaused=false;
    }
    private void downloadImages(){
        pauseDark= new ImageIcon(getClass().getResource(mainPath+"Default.png"));
        pauseLight= new ImageIcon(getClass().getResource(mainPath+"Hover.png"));
    }
    public boolean update(){
        updateImage();
        if(gamePaused)
            return true;
        return false;
    }
    private void updateImage(){
        //Initial press - change icon to light
        if(!buttonPressed && mouseReader.pressed){
            button.setIcon(pauseLight);
            buttonPressed=true;
        }
        //mouse released -> Pause game
        if(buttonPressed && !mouseReader.pressed){
            button.setIcon(pauseDark);
            buttonPressed=false;
            gamePaused=!gamePaused;
        }
    }
    //Display pausing screen
    public void start(Graphics2D g2d,int width,int height){
        unpauseButton.setBounds(0,0,width,height);
        String text;
        int textWidth;
        g2d.setColor(new Color(0,0,0,.5f));
        g2d.fillRect(0,0,width,height);
        g2d.setColor(Color.WHITE);
        g2d.setFont(MyFont.comic.deriveFont(Font.PLAIN,50));
        text="GAME PAUSED";
        textWidth=g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text,width/2-textWidth/2,height/2-25);
        g2d.setFont(MyFont.comic.deriveFont(Font.ITALIC,15));
        text="Click anywhere on the screen to resume...";
        textWidth=g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text,width/2-textWidth/2,height/2);
        //condition to unpause the game
        if(unpauseReader.pressed){
            gamePaused=false;
            unpauseButton.setBounds(-100,-100,1,1);
        }
    }
    public void pressButton(){
        buttonPressed=true;
    }
    public JLabel getButton(){
        return button;
    }
    public JLabel getUnpauseButton(){
        return unpauseButton;
    }
}
