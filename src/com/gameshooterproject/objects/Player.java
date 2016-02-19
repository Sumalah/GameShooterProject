package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;

public class Player extends Walker {

    private boolean playerCenterVertically;
    private boolean playerCenterHorizontally;

    public Player(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        centerPlayer();
    }

    public void centerPlayer() {
        setCoordinatesToWindowCenter();
        setPlayerCenterHorizontally(true);
        setPlayerCenterVertically(true);
    }

    private void setCoordinatesToWindowCenter() {
        x = (Window.WIDTH / 2) - (width / 2) - 2;
        y = (Window.HEIGHT / 2) - (height / 2) - 2;
    }

    @Override
    public void update() {
        updateWalkerOffset();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.toRadians(-1 * direction), x + (width/2), y + (height / 2));
        g2d.fillOval(x, y, width, height);
    }
//================GETTERS - SETTERS================//

    public void setPlayerCenterVertically(Boolean value){
        playerCenterVertically = value;
    }

    public boolean isPlayerCenterVertically(){
        return playerCenterVertically;
    }

    public void setPlayerCenterHorizontally(Boolean value){
        playerCenterHorizontally = value;
    }

    public boolean isPlayerCenterHorizontally(){
        return playerCenterHorizontally;
    }
}
