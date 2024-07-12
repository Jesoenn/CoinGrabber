package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import display.CoinAnimations;
import display.Player;

import javax.imageio.ImageIO;

import static java.awt.Font.BOLD;

public class DisplayCoins {
    private Dimension resolution;
    private Font comic=MyFont.comic;
    private Image coin,background;
    private final String mainPath="/buttons/coinCounter/";
    public DisplayCoins(Dimension resolution){
        this.resolution=resolution;
        downloadImages();
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(background,resolution.width-180, 30,150,50, null);
        g2d.drawImage(coin,resolution.width-170, 40,30,30, null);
        g2d.setColor(Color.WHITE);
        g2d.setFont(comic);
        int howManyNumbers=String.valueOf(Player.collectedCoins).length(); //1-1 10-2 100-3 cyfry
        g2d.drawString(String.valueOf(Player.collectedCoins),resolution.width-howManyNumbers*21-40,65);
    }
    private void downloadImages(){
        try {
            coin=ImageIO.read(getClass().getResource("/coin/1.png"));
            background=ImageIO.read(getClass().getResource(mainPath+"Dummy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateSizes(Dimension resolution){
        this.resolution=resolution;
    }
}
