package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

import java.awt.*;

public class Bullet extends GameObject{
    public int damage;
    private int direction;
    private int offsetX;
    private int offsetY;
    private int vel = 30;

    public Bullet(int x, int y, int width, int height, ID id, int damage, int direction) {
        super(x, y, width, height, id);
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public void update() {
        updateWalkerOffset();
        moveByYOffset(offsetY);
        moveByXOffset(offsetX);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    protected void updateWalkerOffset() {
        offsetX = (int)(vel * Math.sin(Math.toRadians(direction)));
        offsetY = (int)(vel * Math.cos(Math.toRadians(direction)));
    }
}
