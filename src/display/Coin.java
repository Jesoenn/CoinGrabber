package display;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Coin {
    private int x,y=450,screenWidth,screenHeight,coinSize=30; //y=450 TYMCXZASOWE potem na -coinsize
    private CoinAnimations coinAnimations;
    private int currentAnimationNumber;
    private Ellipse2D hitbox;
    public Coin(int screenWidth,int screenHeight, int x){
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        //y=-coinSize;
        this.x=x;
        coinAnimations=new CoinAnimations(coinSize,x);
        hitbox=new Ellipse2D.Double(x+coinAnimations.getMovedX(1),y,coinAnimations.getWidth(1),coinSize);
    }
    public void update(){
        currentAnimationNumber=coinAnimations.getCurrentAnimation();
        //y-=1;
        coinAnimations.update();
        hitbox=new Ellipse2D.Double(x+coinAnimations.getMovedX(currentAnimationNumber),y,coinAnimations.getWidth(currentAnimationNumber),coinSize);
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        g2d.draw(hitbox);
        //g2d.drawRect(x+coinAnimations.getMovedX(currentAnimationNumber),y,coinAnimations.getWidth(currentAnimationNumber),coinSize);
        coinAnimations.draw(g2d);
    }
    public Ellipse2D getHitbox(){
        return hitbox;
    }
}
