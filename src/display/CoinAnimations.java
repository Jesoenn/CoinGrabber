package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

public class CoinAnimations {
    private int coinSize,x,y;
    private static ArrayList<Image> coinAnimation;
    private static int[][] resolution=new int[2][10]; //x,przesuniecie, 10 kolumn
    private int tick=0;
    private int currentAnimation=1;
    private Image currentImage;
    public static boolean firstObject=true;
    public CoinAnimations(int coinSize, int x){
        this.x=x;
        this.coinSize=coinSize;
        if(firstObject){
            downloadImages();
            firstObject=false;
        }
        addResolution();
    }
    public void update(int y){
        this.y=y;
        if(tick%3==0){ //Every 3 frames animation gets changed
            currentAnimation++;
            if(currentAnimation==11){ //if all animations displayed, repeat cycle
                tick=0;
                currentAnimation=1;
            }
            currentImage=coinAnimation.get(currentAnimation-1);
        }
        tick++;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(currentImage,x+resolution[1][currentAnimation-1],y,resolution[0][currentAnimation-1],coinSize,null);
    }
    public void downloadImages(){
        coinAnimation=new ArrayList<>();
        String mainPath="/coin/";
        for(int i=0; i<10; i++){
            try{
                coinAnimation.add(ImageIO.read(getClass().getResource(mainPath+(i+1)+".png")));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //Displaying coin sizes. Array makes each animation looks proportional
    public void addResolution(){
        double scaler=coinSize/564.0;
        //          WIDTH                       MOVED TO X's RIGHT
        resolution[0][0]=(int)(scaler*564); resolution[1][0]=(int)(0*scaler);
        resolution[0][1]=(int)(scaler*559); resolution[1][1]=(int)(2*scaler);
        resolution[0][2]=(int)(scaler*504); resolution[1][2]=(int)(30*scaler);
        resolution[0][3]=(int)(scaler*428); resolution[1][3]=(int)(68*scaler);
        resolution[0][4]=(int)(scaler*262); resolution[1][4]=(int)(151*scaler);
        resolution[0][5]=(int)(scaler*108); resolution[1][5]=(int)(228*scaler);
        resolution[0][6]=(int)(scaler*262); resolution[1][6]=(int)(151*scaler);
        resolution[0][7]=(int)(scaler*428); resolution[1][7]=(int)(68*scaler);
        resolution[0][8]=(int)(scaler*503); resolution[1][8]=(int)(30*scaler);
        resolution[0][9]=(int)(scaler*559); resolution[1][9]=(int)(2*scaler);
    }
    public int getWidth(int animationNumber){
        return resolution[0][animationNumber-1];
    }
    public int getMovedX(int animationNumber){
        return resolution[1][animationNumber-1];
    }
    public int getCurrentAnimation(){
        return currentAnimation;
    }
}
