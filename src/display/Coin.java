package display;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Coin {
    private int x;
    private static int screenWidth,screenHeight;
    public static final int coinSize=40;
    private int y=-coinSize;
    private CoinAnimations coinAnimations;
    private int currentAnimationNumber;
    private Ellipse2D hitbox;
    public Coin(int screenWidth,int screenHeight, int x){
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        this.x=x;
        coinAnimations=new CoinAnimations(coinSize,x);
        hitbox=new Ellipse2D.Double(x+coinAnimations.getMovedX(1),y,coinAnimations.getWidth(1),coinSize);
    }
    public void update(){
        currentAnimationNumber=coinAnimations.getCurrentAnimation();
        y+=1;
        coinAnimations.update(y);
        hitbox=new Ellipse2D.Double(x+coinAnimations.getMovedX(currentAnimationNumber),y,coinAnimations.getWidth(currentAnimationNumber),coinSize);
    }
    public void draw(Graphics2D g2d){
        //g2d.draw(hitbox);
        //g2d.drawRect(x+coinAnimations.getMovedX(currentAnimationNumber),y,coinAnimations.getWidth(currentAnimationNumber),coinSize);
        coinAnimations.draw(g2d);
    }
    public void updateSizes(Dimension resolution){
        screenWidth=resolution.width;
        screenHeight=resolution.height;
    }
    public Ellipse2D getHitbox(){
        return hitbox;
    }
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
    public int getScreenHeight(){
        return screenHeight;
    }
    public int getScreenWidth(){
        return screenWidth;
    }
}
