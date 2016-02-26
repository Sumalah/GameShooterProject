package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;

public class Crate extends GameObject {

    public Crate(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
    }
}
