package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {
    private int x,y,prevX,prevY,screenWidth,screenHeight,playerHeight=50;
    //KEY ELEMENTS
    private ArrayList<Coin> coins;
    private Collision collision;
    private int collectedCoins=0;
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
        this.coins=coins;
        prevX=x;
        prevY=y;
        animations=new PlayerAnimations(screenWidth,screenHeight,x,y,playerHeight);
        hitbox=new Rectangle(x,y,39,50);
        collision=new Collision(this,coins);
    }
    public void update(int speed){
        x+=speed;
        if(x>screenWidth-39 || x<0)
            x-=speed;
        if(jumped)
            jump();
        if(speed>0)
            animations.changeDirection("right");
        else if(speed<0)
            animations.changeDirection("left");
        animations.update(x,y,prevX,prevY,jumped);
        hitbox=new Rectangle(x,y,39,50);
        prevX=x;
        collision.check();
    }
    public void draw(Graphics2D g2d){
        g2d.draw(hitbox);
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
    public void updateCoins(ArrayList<Coin> coins){
        this.coins=coins;
        collision.updateCoins(coins);
    }
    public boolean didJump(){
        return jumped;
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
}
