package ui;

import java.awt.*;

public class UserInterfaceManager {
    private PlayMusic soundTrack;
    private MyFont newFont=new MyFont();
    private DisplayCoins displayCoins;
    private Dimension resolution;
    public UserInterfaceManager(Dimension resolution){
        this.resolution=resolution;
        soundTrack=new PlayMusic(); //Soundtrack
        displayCoins=new DisplayCoins(resolution);
    }
    public void draw(Graphics2D g2d){
        displayCoins.draw(g2d);
    }
    public void updateSizes(Dimension resolution){
        this.resolution=resolution;
        displayCoins.updateSizes(resolution);
    }
}
