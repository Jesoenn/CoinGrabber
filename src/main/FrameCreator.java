package main;

import javax.swing.*;

public class FrameCreator extends JFrame {
    public FrameCreator(){
        setResizable(true); // potrm na 1920 na 1080
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Coin Grabber");
        setVisible(true);
    }
}
