package com.gameshooterproject.objects;

import com.gameshooterproject.holders.BulletsHolder;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Weapon extends GameObject{

    private String name;
    private int maxCooldown;
    private int actualCooldown;
    private int damage;
    private Player player;
    private BulletsHolder bulletsHolder;
    private Timer timer;
    private CountCooldown countCooldown;
    private boolean triggerPressed;
    private boolean isOnGround;

    public Weapon(int x, int y, int width, int height, ID id, String name, int maxCooldown, int damage, Player player, BulletsHolder bulletsHolder) {
        super(x, y, width, height, id);
        this.name = name;
        this.maxCooldown = maxCooldown;
        this.damage = damage;
        this.player = player;
        this.bulletsHolder = bulletsHolder;

        this.timer = new Timer();
        this.triggerPressed = false;
        this.isOnGround = false;

        actualCooldown = 0;
    }

    public void update(){
        if(isTriggerPressed()){
            if (readyToShot()) {
                Bullet bullet = shootBullet(player.getDirection(), player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2);
                bulletsHolder.addNewObject(bullet);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if(isOnGround()){
            g.setColor(Color.black);
            g.fillRect(x, y, width, height);
        }
    }

    private void cooldownCounter(){
        Timer timer = new Timer();
        countCooldown = new CountCooldown(10);
        timer.schedule(countCooldown, 0, maxCooldown * 5);
    }

    public Bullet shootBullet(int direction, int x, int y){
        timer.cancel();
        timer.purge();

        cooldownCounter();

        return new Bullet(x, y, 2, 10, ID.Bullet, this.damage, direction);
    }

    public boolean readyToShot() {
        if(countCooldown != null)
            actualCooldown = countCooldown.getCooldown();
        if(actualCooldown <= 0){
            return true;
        }
        return false;
    }

    public boolean isTriggerPressed() {
        return triggerPressed;
    }

    public void setTriggerPressed(boolean triggerPressed) {
        this.triggerPressed = triggerPressed;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class CountCooldown extends TimerTask {
    int cooldown;

    public CountCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void run() {
        if(cooldown <= 0) {
            this.cancel();
        }
        cooldown -= 1;
    }

    public int getCooldown(){
        return cooldown;
    }
}
