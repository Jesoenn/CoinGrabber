package display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CoinGenerator {
    private Random generateNumber;
    private int screenWidth,screenHeight,x;
    private Coin coin;
    public CoinGenerator(int screenWidth, int screenHeight){
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        generateNumber=new Random();
    }
    public boolean generated(){
        int chances=generateNumber.nextInt(100)+1; //1-100;
        if(chances==100){
            coin=new Coin(screenWidth,screenHeight,generateNumber.nextInt(screenWidth-Coin.coinSize));
            return true;
        }
        return false;
    }
    public Coin getCoin(){
        return coin;
    }
    public void updateSizes(Dimension resolution){
        screenWidth=resolution.width;
        screenHeight=resolution.height;
    }
}
