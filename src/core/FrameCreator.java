package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FrameCreator extends JFrame {
    public FrameCreator(){
        setResizable(true); // potrm na 1920 na 1080
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Coin Grabber");
        setVisible(true);
    }
}
