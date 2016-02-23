package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMap;

public class GameMapHolder extends BasicHolder{
    GameMap gameMap;

    public GameMapHolder(GameMap gamemap) {
        super();
        this.gameMap = gamemap;
        addNewObject(gamemap);
    }

    public GameMap getGameMap(){
        return gameMap;
    }

}
