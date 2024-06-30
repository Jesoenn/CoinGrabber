package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {
    private int x,y,prevX,prevY,screenWidth,screenHeight,playerHeight=50;
    //JUMPING SETTINGS
    private int jumpFrames=0; //0,5s w gore i potem w dol
    private int yVelocity;
    private boolean jumped=false;
    //ANIMATION SETTINGS
    private ArrayList<Image> runAnimation;
    private ArrayList<Image> idleAnimation;
    private ArrayList<Image> jumpAnimation;
    private String currentAnimationPlaying="";
    private String facing="right";
    private int tick=0;
    private int currentAnimation=1;
    private Image displayImage;

    public Player(int screenWidth,int screenHeight){
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        x=screenWidth/2;
        y=screenHeight-playerHeight;
        prevX=x;
        prevY=y;
        downloadImages("run",8);
        downloadImages("idle",5);
        downloadImages("jump",8);
    }
    public void update(int speed){
        x+=speed;
        if(x>screenWidth-39 || x<0)
            x-=speed;
        if(jumped)
            jump();
        if(speed>0)
            facing="right";
        else if(speed<0)
            facing="left";
        animations();
        prevX=x;
    }
    private void animations(){
        if(jumped && currentAnimationPlaying!="jump"){
            currentAnimationPlaying="jump";
            tick=0;
            currentAnimation=1;
        }
        else if(prevX==x && currentAnimationPlaying!="idle" && y==screenHeight-playerHeight){
            currentAnimationPlaying="idle";
            tick=0;
            currentAnimation=1;
        }
        else if(prevX!=x && currentAnimationPlaying!="run" && y==screenHeight-playerHeight){
            currentAnimationPlaying="run";
            tick=0;
            currentAnimation=1;
        }

        if(tick%10==0){
            if(currentAnimationPlaying=="idle")
                displayImage=idleAnimation.get(currentAnimation-1);
            else if(currentAnimationPlaying=="run")
                displayImage=runAnimation.get(currentAnimation-1);
            else if(currentAnimationPlaying=="jump")
                displayImage=jumpAnimation.get(currentAnimation-1);
            currentAnimation++;
        }
        if(currentAnimation==5 && currentAnimationPlaying=="idle" || currentAnimation==8){
            tick=0;
            currentAnimation=1;
        }
        tick++;
    }
    public void draw(Graphics2D g2d){
        AffineTransform originalTransform = g2d.getTransform(); //zapis transformacji
        if(facing=="left"){
            g2d.scale(-1,1);
            g2d.drawImage(displayImage,-x-39,y,39,50,null);
            g2d.setTransform(originalTransform); //cofniecie transformacji
        }
        else{
            g2d.drawImage(displayImage,x,y,39,50,null);
        }
    }

    public void startJump(){
        jumped=true;
        yVelocity=10;
        jumpFrames=0;
    }
    private void jump(){
        jumpFrames++;
        double traveled;
        if(jumpFrames<=30){
            traveled=yVelocity*jumpFrames-(jumpFrames*jumpFrames)/6;
            y=screenHeight-playerHeight-(int)traveled;
        }
        else if(jumpFrames<=60){
            traveled=(jumpFrames-30)*(jumpFrames-30)/6;
            y=screenHeight-150-playerHeight+(int)traveled;
        }
        else{
            jumped=false;
        }
    }

    public boolean didJump(){
        return jumped;
    }
    private void downloadImages(String name, int howMany){
        ArrayList<Image> animationPack=new ArrayList<>();
        String mainPath="/images/"+name+"Animations/";
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
}
