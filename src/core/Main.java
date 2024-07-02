package core;
import ui.MyFont;

public class Main {
    public static void main(String[] args){
        FrameCreator window=new FrameCreator();
        Engine engine=new Engine();
        window.add(engine);
        window.pack();
        engine.startGame();
        System.out.println(window.getContentPane().getSize()); //tymczasowe
    }
}
