package display;

import ui.CollectedSound;

import java.util.ArrayList;
import java.util.Iterator;

public class Collision {
    private ArrayList<Coin> coins;
    private Player player;
    private CollectedSound playSound; //play sound when Collision happens
    public Collision(Player player, ArrayList<Coin> coins) {
        this.player=player;
        this.coins=coins;
        playSound=new CollectedSound();
    }
    public int check(){
        int collisions=0;
        Iterator<Coin> iterator=coins.iterator();
        while(iterator.hasNext()){
            Coin coin=iterator.next();
            if(player.getHitbox().getBounds2D().intersects(coin.getHitbox().getBounds2D())){
                playSound.play();
                iterator.remove();
                collisions++;
            }
            //Jezeli moneta wyjdzie poza mape
            if(coin.getY()>coin.getScreenHeight() || coin.getX()>coin.getScreenWidth())
                iterator.remove();
        }
        return collisions;
    }
    public void updateCoins(ArrayList<Coin> coins){
        this.coins=coins;
    }
}
