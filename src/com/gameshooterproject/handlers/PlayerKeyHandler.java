package com.gameshooterproject.handlers;

import com.gameshooterproject.objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyHandler extends KeyAdapter{
    Player player;

    boolean keyUp, keyDown, keyLeft, keyRight, keySpacebar;

    public PlayerKeyHandler(Player player) {
        this.player = player;

        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
        keySpacebar = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            keyUp = true;
        }
        if(key == KeyEvent.VK_S) {
            keyDown = true;
        }
        if(key == KeyEvent.VK_A) {
            player.turnLeft();
            keyLeft = true;
        }
        if(key == KeyEvent.VK_D) {
            player.turnRight();
            keyRight = true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        if(key == KeyEvent.VK_SPACE){
            keySpacebar = true;
            player.getWeapon().setTriggerPressed(true);
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

        if(key == KeyEvent.VK_W) {
            keyUp = false;
        }
        if(key == KeyEvent.VK_S) {
            keyDown = false;
        }
        if(key == KeyEvent.VK_A) {
            player.stopTurning();
            keyLeft = false;
        }
        if(key == KeyEvent.VK_D) {
            player.stopTurning();
            keyRight = false;
        }
        if(key == KeyEvent.VK_SPACE){
            keySpacebar = false;
            player.getWeapon().setTriggerPressed(false);
        }

        if(!keyUp && !keyDown){
            player.stop();
        }
    }
}
