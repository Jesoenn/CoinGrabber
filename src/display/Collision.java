package display;

import java.util.ArrayList;
import java.util.Iterator;

public class Collision {
    private ArrayList<Coin> coins;
    private Player player;
    public Collision(Player player, ArrayList<Coin> coins) {
        this.player=player;
        this.coins=coins;
    }
    public void check(){
        Iterator<Coin> iterator=coins.iterator();
        while(iterator.hasNext()){
            Coin coin=iterator.next();
            if(player.getHitbox().getBounds2D().intersects(coin.getHitbox().getBounds2D())){
                iterator.remove();
            }
        }
    }
    public void updateCoins(ArrayList<Coin> coins){
        this.coins=coins;
    }
}
