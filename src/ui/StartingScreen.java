package ui;

import core.Engine;
import core.MouseReader;

import javax.swing.*;
import java.awt.*;

public class StartingScreen {
    private JLabel button;
    private Dimension resolution;
    private MouseReader mouseReader=new MouseReader();
    private boolean readyToPlay=false;
    public StartingScreen(Dimension resolution){
        button=new JLabel();
        button.setBounds(0,0,resolution.width,resolution.height);
        button.addMouseListener(mouseReader);
        this.resolution=resolution;
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0,.7f));
        g2d.fillRect(0,0, resolution.width, resolution.height);
        //jak tak w pausescreen srodek ekranu
        String text;
        int textWidth;
        g2d.setColor(Color.WHITE);
        g2d.setFont(MyFont.comic.deriveFont(Font.PLAIN,50));
        text="START GAME";
        textWidth=g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text, resolution.width/2-textWidth/2, resolution.height/2-25);
        g2d.setFont(MyFont.comic.deriveFont(Font.ITALIC,15));
        text="Click anywhere on the screen to play...";
        textWidth=g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text,resolution.width/2-textWidth/2,resolution.height/2);
        if(mouseReader.pressed){
            Engine.gameState= Engine.State.PLAYING;
            button.setBounds(-200,-200,1,1);
        }
    }
    public void updateResolution(Dimension resolution){
        this.resolution=resolution;
        button.setBounds(0,0,resolution.width,resolution.height);
    }
    public JLabel getButton(){
        return button;
    }
}
