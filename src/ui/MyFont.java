package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.Font.BOLD;

public class MyFont {
    public static Font comic;
    public static Color lightBlue=new Color(55,223,229);
    public MyFont(){
        try {
            InputStream inputStream=getClass().getResourceAsStream("/fonts/Bungee-Regular.otf");
            comic = Font.createFont(Font.TRUETYPE_FONT,inputStream);
            comic=comic.deriveFont(BOLD,30f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//              CHANGED FROM FILE TO INPUTSTREAM
//        File file=new File("src/resources/fonts/Bungee-Regular.otf");
//        try {
//            comic = Font.createFont(Font.TRUETYPE_FONT,file);
//            comic=comic.deriveFont(BOLD,30f);
//        } catch (FontFormatException | IOException e) {
//            throw new RuntimeException(e);
//        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(comic);
    }
}
