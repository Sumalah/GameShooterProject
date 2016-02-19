package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;

public class Player extends Walker {
    private boolean playerVerticalCentered;
    private boolean playerHorizontalCentered;
    private GameMap gameMap;
    private int offsetX, offsetY;

    public Player(int x, int y, int width, int height, ID id, GameMap gameMap) {
        super(x, y, width, height, id);
        this.gameMap = gameMap;

        centerPlayer();

        offsetX = this.x;
        offsetY = this.y;
    }

    public void centerPlayer() {
        x = (Window.WIDTH / 2) - (width / 2) - 2;
        y = (Window.HEIGHT / 2) - (height / 2) - 2;
        setPlayerHorizontalCentered(true);
        setPlayerVerticalCentered(true);
    }

    public void setPlayerVerticalCentered(Boolean value){
        playerVerticalCentered = value;
    }

    public boolean isPlayerVerticalCentered(){
        return playerVerticalCentered;
    }

    public void setPlayerHorizontalCentered(Boolean value){
        playerHorizontalCentered = value;
    }

    public boolean isPlayerHorizontalCentered(){
        return playerHorizontalCentered;
    }

    @Override
    public void update() {
        offsetX = (int)(vel * Math.sin(Math.toRadians(direction)));
        offsetY = (int)(vel * Math.cos(Math.toRadians(direction)));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.toRadians(-1 * direction), x + (width/2), y + (height / 2));
        g2d.fillOval(x, y, width, height);
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
