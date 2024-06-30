package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyReader implements KeyListener {
    public boolean left=false,right=false,space=false;

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode=keyEvent.getKeyCode();
        if(keyCode==KeyEvent.VK_A){
            left=true;
        }
        if(keyCode==KeyEvent.VK_D){
            right=true;
        }
        if(keyCode==KeyEvent.VK_SPACE){
            space=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode=keyEvent.getKeyCode();
        if(keyCode==KeyEvent.VK_A){
            left=false;
        }
        if(keyCode==KeyEvent.VK_D){
            right=false;
        }
        if(keyCode==KeyEvent.VK_SPACE){
            space=false;
        }
    }
}
