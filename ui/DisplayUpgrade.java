package ui;

import core.MouseReader;
import display.MakePurchase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DisplayUpgrade {
    private JLabel button;
    private ImageIcon darkUpgrade,lightUpgrade;
    private Image coin;
    private MouseReader mouseReader;
    private boolean buttonPressed=false;
    private Dimension resolution;
    private MakePurchase purchase;
    public DisplayUpgrade(Dimension resolution){
        button=new JLabel();
        mouseReader=new MouseReader();
        downloadImages();
        button.setIcon(darkUpgrade);
        button.addMouseListener(mouseReader);
        this.resolution=resolution;
        button.setBounds(resolution.width-94,resolution.height-94,84,84);
        purchase=new MakePurchase();
    }
    public void update(Dimension resolution, Graphics2D g2d){
        updateResolution(resolution);
        updateButton();
        drawImages(g2d);
    }
    //Check if button is pressed
    private void updateButton(){
        if(!buttonPressed && mouseReader.pressed){
            button.setIcon(lightUpgrade);
            buttonPressed=true;
        }
        if(buttonPressed && !mouseReader.pressed){
            button.setIcon(darkUpgrade);
            purchase.tryToBuy();
            buttonPressed=false;
        }
    }
    private void drawImages(Graphics2D g2d){
        g2d.setFont(MyFont.comic.deriveFont(Font.PLAIN,20));
        g2d.drawImage(coin,resolution.width-81,resolution.height-124,25,25,null);
        g2d.drawString("15", resolution.width-51, resolution.height-104);
    }
    private void downloadImages(){
        try {
            darkUpgrade=new ImageIcon(getClass().getResource("/buttons/shop/ArrowUp/Default.png"));
            lightUpgrade=new ImageIcon(getClass().getResource("/buttons/shop/ArrowUp/Hover.png"));
            coin= ImageIO.read(getClass().getResource("/coin/1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JLabel getButton(){
        return button;
    }
    public void updateResolution(Dimension resolution){
        if(!this.resolution.equals(resolution)){
            this.resolution=resolution;
            button.setBounds(resolution.width-94,resolution.height-94,84,84);
        }
    }
}
