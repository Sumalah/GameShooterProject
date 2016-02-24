package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.BulletsHolder;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Bullet;
import com.gameshooterproject.objects.core.Weapon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyHandler extends KeyAdapter{
    Player player;
    BulletsHolder bulletsHolder;

    boolean keyUp, keyDown, keyLeft, keyRight, keySpacebar;

    public PlayerKeyHandler(Player player, BulletsHolder bulletsHolder) {
        this.player = player;
        this. bulletsHolder = bulletsHolder;

        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;
        keySpacebar = false;
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
        if(key == KeyEvent.VK_SPACE){
            keySpacebar = true;
            shot();
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
        if(key == KeyEvent.VK_SPACE){
            keySpacebar = false;
        }

        if(!keyUp && !keyDown){
            player.stop();
        }
    }

    private void shot(){
        Weapon playerWeapon = player.getWeapon();
        if (playerWeapon.readyToShot()) {
            Bullet bullet = playerWeapon.shootBullet(player.getDirection(), player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2);
            bulletsHolder.addNewObject(bullet);
        }
    }
}
