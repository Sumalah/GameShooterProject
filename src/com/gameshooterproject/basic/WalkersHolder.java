package com.gameshooterproject.basic;

import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.core.Walker;

import java.util.LinkedList;

public class WalkersHolder {
    Player player;
    private LinkedList<Walker> mapObjectsList;

    public WalkersHolder(Player player) {
        mapObjectsList = new LinkedList<>();
        this.player = player;
        addNewWalkerObject(player);
    }

    public void update(){
        for(int i = 0; i < mapObjectsList.size(); i++){
            mapObjectsList.get(i).update();
        }
    }

    public void addNewWalkerObject(Walker object){
        mapObjectsList.add(object);
    }

    public LinkedList<Walker> getWalkerObjectsList(){
        return mapObjectsList;
    }

    public Player getPlayer(){
        return player;
    }
}
