package com.gameshooterproject.holders;

import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;
import java.util.LinkedList;

public abstract class BasicHolder {

    protected LinkedList<GameObject> gameObjectLinkedList;

    public BasicHolder() {
        gameObjectLinkedList = new LinkedList<>();
    }

    public abstract void update();

    public void draw(Graphics g){
        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            gameObjectLinkedList.get(i).draw(g);
        }
    }

    public void addNewObject(GameObject object){
        gameObjectLinkedList.add(object);
    }

    public void removeObject(GameObject object){
        gameObjectLinkedList.remove(object);
    }

    public LinkedList<GameObject> getGameObjectLinkedList(){
        return gameObjectLinkedList;
    }
}
