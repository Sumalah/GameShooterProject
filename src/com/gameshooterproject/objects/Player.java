package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.graphics.BufferedImageLoader;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Walker {

    private boolean playerCenterVertically;
    private boolean playerCenterHorizontally;
    private Weapon weapon;
    private BufferedImage image = null;

    public Player(int x, int y, int width, int height, ID id, int health) {
        super(x, y, width, height, id, health);

        BufferedImageLoader loader = new BufferedImageLoader();
        image = loader.loadImage("res/circle-star-small-white.png");

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
        rotatePlayer();
        updateWalkerOffset();
        weapon.update();
    }

    private void rotatePlayer() {
        setDirection(getDirection() + turning);
    }

    public void addWeapon(Weapon weapon){
        setWeapon(weapon);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.toRadians(-1 * direction), x + (width/2), y + (height / 2));
//        g2d.fillOval(x, y, width, height);
        g2d.drawImage(image, x, y, null);
        g2d.fillRect(x+(width/2)-3, y+height - 5, 5, 20);
        g2d.setTransform(old);  // after that nothing will be rotated
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
