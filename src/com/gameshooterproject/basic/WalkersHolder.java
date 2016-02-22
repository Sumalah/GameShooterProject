package com.gameshooterproject.basic;

import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;
import java.util.LinkedList;

public class WalkersHolder {
    Player player;
    private LinkedList<Walker> walkerObjectsList;

    public WalkersHolder(Player player) {
        walkerObjectsList = new LinkedList<>();
        this.player = player;
        addNewWalkerObject(player);
    }

    public void update(){
        for(int i = 0; i < walkerObjectsList.size(); i++){
            walkerObjectsList.get(i).update();
        }
    }

    public void draw(Graphics g){
        player.draw(g);
        drawZombies(g);
    }

    private void drawZombies(Graphics g) {
        for(int i = 0; i < walkerObjectsList.size(); i++){
            GameObject tempObject = walkerObjectsList.get(i);
            if(tempObject.getId() == ID.BasicZombie)
                tempObject.draw(g);
        }
    }

    public void addNewWalkerObject(Walker object){
        walkerObjectsList.add(object);
    }

    public LinkedList<Walker> getWalkerObjectsList(){
        return walkerObjectsList;
    }

    public Player getPlayer(){
        return player;
    }
}
