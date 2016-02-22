package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.mapelements.MapObstacle;

import java.awt.*;
import java.util.LinkedList;

public class GameMapHolder {
    GameMap gameMap;
    private LinkedList<GameObject> mapObjectsList;

    public GameMapHolder(GameMap gameMap) {
        mapObjectsList = new LinkedList<>();
        this.gameMap = gameMap;
        addNewMapObject(gameMap);
    }

    public void update(){
        for(int i = 0; i < mapObjectsList.size(); i++){
            mapObjectsList.get(i).update();
        }
    }

    public void addNewMapObject(GameObject object){
        mapObjectsList.add(object);
    }

    public LinkedList<GameObject> getMapObjectsList(){
        return mapObjectsList;
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    public void draw(Graphics g){
        gameMap.draw(g);
        drawMapObstacles(g);
    }

    private void drawMapObstacles(Graphics g) {
        for(int i = 0; i < mapObjectsList.size(); i++){
            GameObject tempObject = mapObjectsList.get(i);
            if(tempObject.getId() == ID.MapObstacles)
                tempObject.draw(g);
        }
    }
}
