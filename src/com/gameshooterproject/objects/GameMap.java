package com.gameshooterproject.objects;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;

public class GameMap extends GameObject {

    public GameMap(int x, int y, int width, int height, ID id){
        super(x, y, width, height, id);

        calculateAndInitMapDimensions();
        centerMap();
    }

    private void calculateAndInitMapDimensions() {
        double mapScale = 1.5;
        width = (int)(Window.WIDTH * mapScale);
        height = (int)(Window.HEIGHT * mapScale);
    }

    private void centerMap() {
        x = (Window.WIDTH / 2) - (width / 2);
        y = (Window.HEIGHT / 2) - (height / 2);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);
        g.setColor(Color.lightGray);
        for(int i = y; i < height; i += height / 10){
            g.drawLine(x, i, width, i);
        }
        for(int i = x; i < width; i += width / 10){
            g.drawLine(i, y, i, height);
        }
    }
}
