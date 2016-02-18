package com.gameshooterproject.basic;

import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;

import java.util.LinkedList;

public class Camera {

    public LinkedList<GameObject> objectList;

    public Camera() {
        objectList = new LinkedList<GameObject>();
    }

    public void update(){
        Player player = (Player) getPlayerObject();
        int playerDirection = player.getDirection();
        int playerVel = player.getVel();

        if(player.isPlayerCentered()){
            for(int i = 0; i < objectList.size(); i++){
                GameObject tempObject = objectList.get(i);
                if(tempObject.getId() != ID.Player){
//                    int objectActualY = tempObject.getY();
                    
//                    tempObject.setX((int)((tempObject.getX())+(player.getVel() * Math.sin(Math.toRadians(player.getDirection())))));
                }
            }
        }

        if(screenBorderConnectsWithMapBorders()){
            player.setPlayerCentered(false);
        }else{
            player.setPlayerCentered(true);
        }
    }

    private boolean screenBorderConnectsWithMapBorders() {
        GameObject player = getPlayerObject();
        GameObject gameMap = getObject(ID.Map);
        int screenHalfWidth = (Window.WIDTH / 2);
        int screenHalfHeight = (Window.HEIGHT / 2);

        //TODO

        int playerDistanceToMapTop = (player.getY()) - (player.getHeight() / 2) - gameMap.getY();
        int playerDistanceToMapBottom = (gameMap.getY() + gameMap.getHeight()) - (player.getY()) - (player.getHeight() / 2);
        int playerDistanceToMapLeft = (player.getX()) - (player.getWidth() / 2) - gameMap.getX();
        int playerDistanceToMapRight = (gameMap.getX() + gameMap.getWidth()) - (player.getX()) - (player.getWidth() / 2);

        if(playerDistanceToMapTop <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapBottom <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapLeft <= screenHalfWidth){
            return true;
        }
        if(playerDistanceToMapRight <= screenHalfWidth){
            return true;
        }

        return false;
    }

    public void addNewObject(GameObject object){
        objectList.add(object);
    }

    public GameObject getPlayerObject(){
        return getObject(ID.Player);
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
