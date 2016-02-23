package com.gameshooterproject.basic;

import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;

public class HUD extends GameObject{

    private Player player;
    private int playerHealth;

    public HUD(int x, int y, int width, int height, ID id, Player player) {
        super(x, y, width, height, id);
        this.player = player;
    }

    @Override
    public void update() {
        updateHealth();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.setColor(Color.pink);
        g.fillRect(x, y, playerHealth / 2, height);
    }

    private void updateHealth(){
        playerHealth = player.getHealth();
    }
}
