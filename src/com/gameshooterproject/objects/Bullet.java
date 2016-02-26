package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject {
    public int damage;
    private int direction;
    private int offsetX;
    private int offsetY;
    private int vel = 25;

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
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.setColor(Color.RED);
        g2d.rotate(Math.toRadians(-1 * direction), x + (width / 2), y + (height / 2));
        g.fillRect(x, y, width, height);
        g2d.setTransform(old);  // after that nothing will be rotated
    }

    protected void updateWalkerOffset() {
        offsetX = (int)(vel * Math.sin(Math.toRadians(direction)));
        offsetY = (int)(vel * Math.cos(Math.toRadians(direction)));
    }
}
