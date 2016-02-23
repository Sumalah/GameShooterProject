package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

public class Weapon{
    private String name;
    private ID id;
    private int cooldown;
    private int damage;

    public Weapon(String name, int cooldown, ID id, int damage) {
        this.name = name;
        this.cooldown = cooldown;
        this.id = id;
        this.damage = damage;
    }

    public Bullet shootBullet(int direction, int x, int y){
        return new Bullet(x, y, 5, 5, ID.Bullet, this.damage, direction);
    }
}
