package main;

public class Main {
    public static void main(String[] args){
        FrameCreator window=new FrameCreator();
        Engine engine=new Engine();
        window.add(engine);
        window.pack();
        engine.startGame();
    }
}
