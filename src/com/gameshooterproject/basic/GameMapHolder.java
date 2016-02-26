package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.mapelements.MapObstacle;

public class GameMapHolder extends BasicHolder{
    GameMap gameMap;

    public GameMapHolder(GameMap gamemap) {
        super();
        this.gameMap = gamemap;
        addNewObject(gamemap);
        makeGameLevel();
    }

    private void makeGameLevel() {
        addNewObject(new MapObstacle(100, 100, 200, 50, ID.MapObstacles));
        addNewObject(new MapObstacle(100, 300, 200, 50, ID.MapObstacles));
        addNewObject(new MapObstacle(600, 600, 100, 500, ID.MapObstacles));
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    @Override
    public void update() {
        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            gameObjectLinkedList.get(i).update();
        }
    }
}
