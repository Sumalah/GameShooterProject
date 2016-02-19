package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;

public class Player extends Walker {
    private boolean playerCentered;
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
        setPlayerCentered(true);
    }

    public void setPlayerCentered(Boolean value){
        playerCentered = value;
    }

    public boolean isPlayerCentered(){
        return playerCentered;
    }

    @Override
    public void update() {
        if(screenBorderConnectsWithMapBorders()){
            setPlayerCentered(false);
        } else {
            setPlayerCentered(true);
        }

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

    private boolean screenBorderConnectsWithMapBorders() {
        int screenHalfWidth = (Window.WIDTH / 2);
        int screenHalfHeight = (Window.HEIGHT / 2);

        int playerDistanceToMapTop = (getY()) - (getHeight() / 2) - gameMap.getY();
        int playerDistanceToMapBottom = (gameMap.getY() + gameMap.getHeight()) - (getY()) - (getHeight() / 2);
        int playerDistanceToMapLeft = (getX()) - (getWidth() / 2) - gameMap.getX();
        int playerDistanceToMapRight = (gameMap.getX() + gameMap.getWidth()) - (getX()) - (getWidth() / 2);

        if(playerDistanceToMapTop <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapBottom <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapLeft <= screenHalfWidth){
            return true;
        }
        if(playerDistanceToMapRight <= screenHalfWidth){
            return true;
        }

        return false;
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
