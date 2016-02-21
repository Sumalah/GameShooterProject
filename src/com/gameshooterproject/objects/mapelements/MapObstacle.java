package com.gameshooterproject.objects.mapelements;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;

public class MapObstacle extends GameObject{

    public MapObstacle(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }
}
