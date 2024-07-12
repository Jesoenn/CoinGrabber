package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {
    private int x,y,prevX,screenWidth,screenHeight,playerHeight=100,playerWidth=78;
    //KEY ELEMENTS
    private Collision collision;
    public static int collectedCoins=0;
    //JUMPING SETTINGS
    private int jumpFrames=0; //0,5s w gore i potem w dol
    private int yVelocity;
    private boolean jumped=false;
    //ANIMATION SETTINGS
    private PlayerAnimations animations;
    private Rectangle hitbox;

    public Player(int screenWidth,int screenHeight, ArrayList<Coin> coins){
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        x=screenWidth/2;
        y=screenHeight-playerHeight;
        prevX=x;
        animations=new PlayerAnimations(screenHeight,x,y,playerHeight,playerWidth);
        hitbox=new Rectangle(x,y,playerWidth,playerHeight);
        collision=new Collision(this,coins);
    }
    public void update(int speed){
        x+=speed;
        //Used to push back sprite onto visible screen when resizing
        if(x>screenWidth-playerWidth)
            x=screenWidth-playerWidth;
        else if(x<0)
            x=0;
        if(jumped)
            jump();
        if(speed>0)
            animations.changeDirection("right");
        else if(speed<0)
            animations.changeDirection("left");
        animations.update(x,y,prevX,jumped);
        hitbox=new Rectangle(x,y,playerWidth,playerHeight);
        prevX=x;
        collectedCoins+=collision.check();
    }
    public void draw(Graphics2D g2d){
        animations.draw(g2d);
    }

    public void startJump(){
        jumped=true;
        yVelocity=10;
        jumpFrames=0;
    }
    private void jump(){
        jumpFrames++;
        double traveled;
        //positive acceleration (moving up)
        if(jumpFrames<=30){
            traveled=yVelocity*jumpFrames-(jumpFrames*jumpFrames)/6;
            y=screenHeight-playerHeight-(int)traveled;
        }
        //negative acceleration (falling down)
        else if(jumpFrames<=60){
            traveled=(jumpFrames-30)*(jumpFrames-30)/6;
            y=screenHeight-150-playerHeight+(int)traveled;
        }
        else{
            jumped=false;
        }
    }
    public void updateSizes(Dimension resolution){
        screenWidth=resolution.width;
        int newScreenHeight=resolution.height;
        y+=newScreenHeight-screenHeight; //aktualizacja wysokosci na mapie
        screenHeight=resolution.height;
        animations.updateSizes(resolution,y);
    }
    public boolean didJump(){
        return jumped;
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
}
