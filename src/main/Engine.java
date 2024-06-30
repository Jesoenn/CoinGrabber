package main;

import display.Background;
import display.Player;

import javax.swing.*;
import java.awt.*;

public class Engine extends JPanel implements Runnable{
    private int screenWidth=700,screenHeight=500;
    private Player player;
    Background background;
    private KeyReader keyReader;
    Thread runGame;
    public Engine(){
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setDoubleBuffered(true);
        keyReader=new KeyReader();
        addKeyListener(keyReader);
        setFocusable(true);
    }

    protected void startGame(){
        requestFocusInWindow();
        //OBJECTS
        player=new Player(screenWidth,screenHeight);
        background=new Background(screenWidth,screenHeight);
        //GAME LOOP
        runGame=new Thread(this);
        runGame.start();
    }
    @Override
    public void run(){
        //FRAME SETTINGS
        double frameTime=16.66667; //60FPS - 1/60*1000ms
        double nextFrame=System.currentTimeMillis()+frameTime; //kiedy nastepna klatka
        double timeLeft; //czas pozostaly do nastepnej klatki
        while(runGame!=null){
            try{
                timeLeft=nextFrame-System.currentTimeMillis();
                if(timeLeft>0)
                    Thread.sleep((long)timeLeft);
                nextFrame=System.currentTimeMillis()+frameTime;
            }catch(Exception e){
                e.printStackTrace();
            }
            update();
            repaint();
        }
    }
    public void update(){
        int speed=0;
        if(keyReader.right)
            speed=5;
        if(keyReader.left)
            speed=-5;
        if(keyReader.space && !player.didJump())
            player.startJump();
        player.update(speed);
        background.updateClouds();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        if(background!=null)
            background.draw(g2d);
        if(player!=null)
            player.draw(g2d);
    }
}
