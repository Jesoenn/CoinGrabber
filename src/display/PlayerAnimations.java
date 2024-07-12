package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class PlayerAnimations {
    private int screenHeight,x,y,playerHeight,playerWidth;
    private ArrayList<Image> runAnimation;
    private ArrayList<Image> idleAnimation;
    private ArrayList<Image> jumpAnimation;
    private String currentAnimationPlaying="";
    private String facing="right";
    private int tick=0;
    private int currentAnimation=1;
    private Image displayImage;
    public PlayerAnimations(int screenHeight, int x, int y,int playerHeight,int playerWidth){
        this.playerHeight=playerHeight;
        this.playerWidth=playerWidth;
        this.x=x;
        this.y=y;
        this.screenHeight=screenHeight;
        downloadImages("run",8);
        downloadImages("idle",5);
        downloadImages("jump",8);
    }
    protected void update(int x,int y,int prevX,boolean jumped){
        this.x=x;
        this.y=y;
        //start jump animation
        if(jumped && currentAnimationPlaying!="jump"){
            currentAnimationPlaying="jump";
            tick=0;
            currentAnimation=1;
        }
        //start idle animation
        else if(prevX==x && currentAnimationPlaying!="idle" && y==screenHeight-playerHeight){
            currentAnimationPlaying="idle";
            tick=0;
            currentAnimation=1;
        }
        //start running animation
        else if(prevX!=x && currentAnimationPlaying!="run" && y==screenHeight-playerHeight){
            currentAnimationPlaying="run";
            tick=0;
            currentAnimation=1;
        }
        //every 10 ticks play next animation frame
        if(tick%10==0){
            if(currentAnimationPlaying.equals("idle"))
                displayImage=idleAnimation.get(currentAnimation-1);
            else if(currentAnimationPlaying.equals("run"))
                displayImage=runAnimation.get(currentAnimation-1);
            else if(currentAnimationPlaying.equals("jump"))
                displayImage=jumpAnimation.get(currentAnimation-1);
            currentAnimation++;
        }
        if(currentAnimation==5 && currentAnimationPlaying.equals("idle") || currentAnimation==8){
            tick=0;
            currentAnimation=1;
        }
        tick++;
    }
    public void draw(Graphics2D g2d){
        AffineTransform originalTransform = g2d.getTransform(); //zapis transformacji
        if(facing.equals("left")){
            g2d.scale(-1,1);
            g2d.drawImage(displayImage,-x-playerWidth,y,playerWidth,playerHeight,null);
            g2d.setTransform(originalTransform); //cofniecie transformacji
        }
        else{
            g2d.drawImage(displayImage,x,y,playerWidth,playerHeight,null);
        }
    }
    private void downloadImages(String name, int howMany){
        ArrayList<Image> animationPack=new ArrayList<>();
        String mainPath="/"+name+"Animations/";
        String path;
        for(int i=0; i<howMany; i++){
            path=mainPath+(i+1)+".png";
            try{
                animationPack.add(ImageIO.read(getClass().getResource(path)));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(name=="run")
            runAnimation=animationPack;
        else if(name=="idle")
            idleAnimation=animationPack;
        else if(name=="jump")
            jumpAnimation=animationPack;
    }
    public void updateSizes(Dimension resolution,int y){
        screenHeight=resolution.height;
        this.y=y;
    }
    public void changeDirection(String direction){
        facing=direction;
    }
}
