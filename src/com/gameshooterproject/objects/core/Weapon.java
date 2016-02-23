package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

import java.awt.*;
import java.util.LinkedList;

public class Weapon{
    private String name;
    private ID id;
    private int fireRate;
    private int damage;
    public LinkedList<Bullet> bullets;

    public Weapon(String name, int fireRate, ID id, int damage) {
        this.name = name;
        this.fireRate = fireRate;
        this.id = id;
        this.damage = damage;
        this.bullets = new LinkedList<>();
    }

    public void shootBullet(int direction, int x, int y){
        bullets.add(new Bullet(x, y, 5, 5, ID.Bullet, this.damage, direction));
    }

    public void drawBullets(Graphics g) {
        for(int i = 0; i < bullets.size(); i++){
            Bullet bullet = bullets.get(i);

            bullet.draw(g);
        }
    }

    public void updateBullets() {
        for(int i = 0; i < bullets.size(); i++){
            Bullet bullet = bullets.get(i);

            bullet.update();
        }
    }
}
