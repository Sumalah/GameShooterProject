package com.gameshooterproject.handlers;

import com.gameshooterproject.objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyHandler extends KeyAdapter{
    Player player;

    boolean keyUp, keyDown, keyLeft, keyRight;

    public PlayerKeyHandler(Player player) {
        this.player = player;

        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP) {
            keyUp = true;
        }
        if(key == KeyEvent.VK_DOWN) {
            keyDown = true;
        }
        if(key == KeyEvent.VK_LEFT) {
            player.turnLeft();
            keyLeft = true;
        }
        if(key == KeyEvent.VK_RIGHT) {
            player.turnRight();
            keyRight = true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }

        if(keyUp){
            player.speedUpForward();
        }

        if(keyDown){
            player.speedUpBackward();
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP) {
            keyUp = false;
        }
        if(key == KeyEvent.VK_DOWN) {
            keyDown = false;
        }
        if(key == KeyEvent.VK_LEFT) {
            player.stopTurning();
            keyLeft = false;
        }
        if(key == KeyEvent.VK_RIGHT) {
            player.stopTurning();
            keyRight = false;
        }

        if(!keyUp && !keyDown){
            player.stop();
        }

    }
}
