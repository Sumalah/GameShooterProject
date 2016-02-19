package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.core.GameObject;

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

}
