package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BasicZombie extends Walker{
    Player player;

    public BasicZombie(int x, int y, int width, int height, ID id, Player player) {
        super(x, y, width, height, id);

        this.player = player;
        setVel(3);
    }

    @Override
    public void update() {
        followPlayer();
        moveByYOffset(getOffsetY());
        moveByXOffset(getOffsetX());
        updateWalkerOffset();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.setColor(Color.CYAN);
        g2d.rotate(Math.toRadians(-1 * direction), x + (width / 2), y + (height / 2));
        g2d.fillOval(x, y, width, height);
        g2d.fillRect(x + (width / 2) - 3, y+height - 5, 5, 20);
        g2d.setTransform(old);
    }

    private void followPlayer(){
        int playerX = player.getX() - (player.getWidth()/2);
        int playerY = player.getY() - (player.getHeight()/2);

        float xDistance = getX() - playerX;
        float yDistance = getY() - playerY;
        double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
        direction = (270 - (int)angleToTurn);
        if(Math.abs(xDistance) > 5 && Math.abs(yDistance) > 5){
            vel = 3;
        }else{
            vel = 0;
        }

    }
}
