package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

import java.util.Timer;
import java.util.TimerTask;

public class Weapon{
    private String name;
    private ID id;
    private int maxCooldown;
    private int actualCooldown;
    private int damage;
    Timer timer;
    CountCooldown countCooldown;

    public Weapon(String name, int maxCooldown, ID id, int damage) {
        this.name = name;
        this.maxCooldown = maxCooldown;
        this.id = id;
        this.damage = damage;
        this.timer = new Timer();

        actualCooldown = 0;
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

        return new Bullet(x, y, 5, 5, ID.Bullet, this.damage, direction);
    }

    public boolean readyToShot() {
        if(countCooldown != null)
            actualCooldown = countCooldown.getCooldown();
        if(actualCooldown <= 0){
            return true;
        }
        return false;
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
