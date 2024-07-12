package display;

import javax.imageio.ImageIO;
import java.awt.*;

public class Background {
    private Image foreground,clouds;
    private int x1,x2; //coordinates for 2 cloud pictures
    private int screenWidth,screenHeight;
    public Background(int screenWidth,int screenHeight){
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        x1=0;
        x2=-screenWidth;
        downloadImages();
    }
    private void downloadImages(){
        String mainPath="/background/";
        try{
            foreground=ImageIO.read(getClass().getResource(mainPath+"foreground.png"));
            clouds=ImageIO.read(getClass().getResource(mainPath+"clouds.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //clouds movement
    public void updateClouds(){
        x1+=1;
        x2+=1;
        if(x1>=screenWidth)
            x1=-screenWidth;
        else if(x2>=screenWidth)
            x2=-screenWidth;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(clouds,x1,0,screenWidth,screenHeight,null);
        g2d.drawImage(clouds,x2,0,screenWidth,screenHeight,null);
        g2d.drawImage(foreground,0,0,screenWidth,screenHeight,null);
    }
    //Update clouds location on screen
    public void updateSizes(Dimension resolution){
        screenWidth=resolution.width;
        screenHeight=resolution.height;
        if(x1>x2) //x1 bardziej w prawo
            x2=x1-screenWidth;
        else if(x2>x1)
            x1=x2-screenWidth;
    }
}
