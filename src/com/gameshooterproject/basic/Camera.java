package com.gameshooterproject.basic;

import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;

import java.util.LinkedList;

public class Camera {

    public LinkedList<GameObject> objectList;

    public Camera() {
        objectList = new LinkedList<>();
    }

    public void update(){
        Player player = getPlayerObject();

        if(player.isPlayerCentered()){
            moveEveryObjectExceptPlayer(player);
        }else{
            movePlayer(player);
        }
    }

    private void movePlayer(Player player) {
        player.moveByOffset(player.getOffsetX(), player.getOffsetY());
    }

    private void moveObject(GameObject object){
        object.moveByOffset(1 ,1);
    }

    private void moveEveryObjectExceptPlayer(Player player) {
        for(int i=0; i < objectList.size(); i++){
            GameObject object = objectList.get(i);

            if(object.getId() != ID.Player){
                int offsetX = (-1) * player.getOffsetX();
                int offsetY = (-1) * player.getOffsetY();
                object.moveByOffset(offsetX, offsetY);
            }
        }
    }

    public void addNewObject(GameObject object){
        objectList.add(object);
    }

    public Player getPlayerObject(){
        return (Player) getObject(ID.Player);
    }

    private GameObject getObject(ID id){
        for (int i = 0; i < objectList.size(); i++){
            GameObject tempObject = objectList.get(i);
            if (tempObject.getId() == id){
                return tempObject;
            }
        }
        return null;
    }
}
