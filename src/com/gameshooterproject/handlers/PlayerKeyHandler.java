package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.Camera;
import com.gameshooterproject.objects.core.Walker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyHandler extends KeyAdapter{
    Camera camera;

    boolean keyUp, keyDown, keyLeft, keyRight;

    public PlayerKeyHandler(Camera camera) {
        this.camera = camera;

        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        Walker playerObject = (Walker) camera.getPlayerObject();

        if(key == KeyEvent.VK_UP) {
            playerObject.speedUpForward();
            keyUp = true;
        }
        if(key == KeyEvent.VK_DOWN) {
            playerObject.speedUpBackward();
            keyDown = true;
        }
        if(key == KeyEvent.VK_LEFT) {
            keyLeft = true;
        }
        if(key == KeyEvent.VK_RIGHT) {
            keyRight = true;
        }

        if(keyLeft){
            int actualPlayerDirection = playerObject.getDirection();
            playerObject.setDirection(actualPlayerDirection + 10);
        }
        if(keyRight){
            int actualPlayerDirection = playerObject.getDirection();
            playerObject.setDirection(actualPlayerDirection - 10);
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        Walker playerObject = (Walker) camera.getPlayerObject();

        if(key == KeyEvent.VK_UP) {
            keyUp = false;
        }
        if(key == KeyEvent.VK_DOWN) {
            keyDown = false;
        }
        if(key == KeyEvent.VK_LEFT) {
            keyLeft = false;
        }
        if(key == KeyEvent.VK_RIGHT) {
            keyRight = false;
        }

        if(!keyUp && !keyDown){
            playerObject.stop();
        }
    }
}
