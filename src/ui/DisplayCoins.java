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
    private Image coin,background,circle;
    private final String mainPath="/resources/buttons/coinCounter/";
    public DisplayCoins(Dimension resolution){
        this.resolution=resolution;
        downloadImages();
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(background,resolution.width-400, 100, null);
        g2d.setColor(Color.WHITE);
        g2d.setFont(comic);
        g2d.drawString(String.valueOf(Player.collectedCoins),resolution.width-40,50);
    }
    private void downloadImages(){
        try {
            coin=ImageIO.read(getClass().getResource("/resources/coin/1.png"));
            background=ImageIO.read(getClass().getResource(mainPath+"Dummy.png"));
            circle=ImageIO.read(getClass().getResource(mainPath+"circle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateSizes(Dimension resolution){
        this.resolution=resolution;
    }
}
