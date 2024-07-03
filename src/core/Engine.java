package core;

import display.Background;
import display.Coin;
import display.CoinGenerator;
import display.Player;
import ui.UserInterfaceManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Engine extends JPanel implements Runnable{
    public enum State{
        LOADING,
        PLAYING,
        PAUSED,
    }
    private Dimension screenSize;
    private int screenWidth=900,screenHeight=600; //TYMCZASOWE rozmiary ekranu
    private Player player; //gracz
    private ArrayList<Coin> coins; //monety na ekranie
    private UserInterfaceManager uiManager;
    private Background background; //tlo
    private KeyReader keyReader; //pobieranie naciskania klawiszy
    private Thread runGame; //watek na cala gre
    private CoinGenerator generator; //generowanie monet
    public static State gameState;
    public static int speedModifier=0;
    public Engine(){
        screenSize=new Dimension(screenWidth,screenHeight);
        setPreferredSize(screenSize);
        setDoubleBuffered(true);
        keyReader=new KeyReader();
        addKeyListener(keyReader);
        setFocusable(true);
        //EKRAN STARTOWY
        gameState=State.LOADING;
        //BUTTONS
        setLayout(null);
        uiManager=new UserInterfaceManager(screenSize);
        for(JLabel button: uiManager.getAllButtons())
            add(button);
    }

    protected void startGame(){
        requestFocusInWindow(); //zeby keyreader od razu dzialal
        //OBJECTS
        generator=new CoinGenerator(screenWidth,screenHeight); //Generowanie monet
        coins=new ArrayList<>();//Monety
        player=new Player(screenWidth,screenHeight,coins);     //Gracz
        background=new Background(screenWidth,screenHeight);   //Tlo
        //GAME LOOP
        //gameState=State.PLAYING;
        runGame=new Thread(this);
        runGame.start();
    }
    @Override
    public void run(){
        //FRAME SETTINGS
        double frameTime=16.66667; //60FPS - 1/60*1000ms
        double nextFrame=System.currentTimeMillis()+frameTime; //kiedy nastepna klatka
        double timeLeft; //czas pozostaly do nastepnej klatki
        //CORE
        while(runGame!=null){
            try{
                timeLeft=nextFrame-System.currentTimeMillis();
                if(timeLeft>0)
                    Thread.sleep((long)timeLeft);
                nextFrame=System.currentTimeMillis()+frameTime;
            }catch(Exception e){
                e.printStackTrace();
            }
            updateScreenSizes();
            if(gameState==State.PLAYING)
                update();
            repaint();
        }
    }
    public void update(){
        //System.out.println(coins.size());
        if(generator.generated())
            coins.add(generator.getCoin()); //Dodanie wygenerowanej monety
        int speed=0; //potrzebne jezeli gracz skacze
        if(keyReader.right)
            speed=5+speedModifier;
        if(keyReader.left)
            speed=-(5+speedModifier);
        if(keyReader.space && !player.didJump())
            player.startJump();
        player.update(speed); //player movement
        background.updateClouds(); //clouds movement
        for(Coin coin: coins) //coins movement
            coin.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        if(background!=null)
            background.draw(g2d);
        if(coins!=null){
            for(Coin coin: coins)
                coin.draw(g2d);
        }
        if(player!=null)
            player.draw(g2d);
        //WHOLE UI WITH UPDATES
        if(uiManager!=null)
            uiManager.draw(g2d);
    }
    private void updateScreenSizes(){
        if(!screenSize.equals(getSize())){
            System.out.println("INNY ROZMIAR");
            screenSize=getSize();
            if(background!=null)
                background.updateSizes(screenSize);
            if(player!=null)
                player.updateSizes(screenSize);
            if(coins.size()>0)
                coins.get(0).updateSizes(screenSize);
            if(generator!=null)
                generator.updateSizes(screenSize);
            if(uiManager!=null)
                uiManager.updateSizes(screenSize);
        }
    }
}
