package display;

import core.Engine;

public class MakePurchase {
    public void tryToBuy(){
        if(Player.collectedCoins>=15){
            Player.collectedCoins-=15;
            Engine.speedModifier+=5;
            if(CoinGenerator.minimumChanceToSpawn>=55)
                CoinGenerator.minimumChanceToSpawn-=5;
        }
    }
}
