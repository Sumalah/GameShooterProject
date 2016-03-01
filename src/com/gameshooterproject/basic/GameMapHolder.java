package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMapField;
import com.gameshooterproject.objects.mapelements.MapObstacle;

public class GameMapHolder extends BasicHolder{
    GameMapField gameMapField;

    public GameMapHolder(GameMapField gameMapField) {
        super();
        this.gameMapField = gameMapField;
        addNewObject(gameMapField);
        makeGameLevel();
    }

    private void makeGameLevel() {
        addNewObject(new MapObstacle(100, 100, 200, 50, ID.MapObstacles));
        addNewObject(new MapObstacle(100, 300, 200, 50, ID.MapObstacles));
        addNewObject(new MapObstacle(600, 600, 100, 500, ID.MapObstacles));
    }

    public GameMapField getGameMapField(){
        return gameMapField;
    }

    @Override
    public void update() {
        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            gameObjectLinkedList.get(i).update();
        }
    }
}
