package com.gameshooterproject.basic;

import com.gameshooterproject.holders.GeneralHolder;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.GameMapField;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;

import java.util.LinkedList;

public class Camera {

    private LinkedList<GameObject> allObjectsList;
    private GeneralHolder generalHolder;
    private Player player;

    public Camera(GeneralHolder generalHolder) {
        this.generalHolder = generalHolder;
        this.player = generalHolder.getWalkersHolder().getPlayer();

        getAllObjects();
    }

    private void getAllObjects() {
        allObjectsList = new LinkedList<>();
        allObjectsList.addAll(generalHolder.getAll());
        allObjectsList.remove(player);
    }

    public void update(){
        getAllObjects();
        updatePlayerCameraLock();
        moveObjectsOrPlayer();
    }

    private void moveObjectsOrPlayer() {
        if (player.isPlayerCenterHorizontally()){
            moveEveryObjectExceptPlayerHorizontally();
        }else{
            movePlayerByX();
        }

        if(player.isPlayerCenterVertically()){
            moveEveryObjectExceptPlayerVertically();
        }else{
            movePlayerByY();
        }
    }

    private void updatePlayerCameraLock() {
        GameMapField map = (GameMapField)getObject(ID.MapField);

        if(screenBorderConnectsWithMapTopOrBottom(map)){
            player.setPlayerCenterVertically(false);
        } else {
            player.setPlayerCenterVertically(true);
        }

        if(screenBorderConnectsWithMapLeftOrRight(map)){
            player.setPlayerCenterHorizontally(false);
        } else {
            player.setPlayerCenterHorizontally(true);
        }
    }

    private void movePlayerByX() {
        player.moveByXOffset(player.getOffsetX());
    }

    private void movePlayerByY() {
        player.moveByYOffset(player.getOffsetY());
    }

    private void moveEveryObjectExceptPlayerVertically() {
        for(int i=0; i < allObjectsList.size(); i++){
            GameObject object = allObjectsList.get(i);
            int offsetY = (-1) * player.getOffsetY();
            object.moveByYOffset(offsetY);
        }
    }

    private void moveEveryObjectExceptPlayerHorizontally() {
        for(int i=0; i < allObjectsList.size(); i++){
            GameObject object = allObjectsList.get(i);
            int offsetX = (-1) * player.getOffsetX();
            object.moveByXOffset(offsetX);
        }
    }

    private GameObject getObject(ID id){
        for (int i = 0; i < allObjectsList.size(); i++){
            GameObject tempObject = allObjectsList.get(i);
            if (tempObject.getId() == id){
                return tempObject;
            }
        }
        return null;
    }

    private boolean screenBorderConnectsWithMapTopOrBottom(GameMapField gameMapField) {
        int screenHalfHeight = (Window.HEIGHT / 2);
        int playerDistanceToMapTop = (player.getY()) - (player.getHeight() / 2) - gameMapField.getY();
        int playerDistanceToMapBottom = (gameMapField.getY() + gameMapField.getHeight()) - (player.getY()) - (player.getHeight() / 2);

        if(playerDistanceToMapTop <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapBottom <= screenHalfHeight){
            return true;
        }
        return false;
    }

    private boolean screenBorderConnectsWithMapLeftOrRight(GameMapField gameMapField) {
        int screenHalfWidth = (Window.WIDTH / 2);
        int playerDistanceToMapLeft = (player.getX()) - (player.getWidth() / 2) - gameMapField.getX();
        int playerDistanceToMapRight = (gameMapField.getX() + gameMapField.getWidth()) - (player.getX()) - (player.getWidth() / 2);

        if(playerDistanceToMapLeft <= screenHalfWidth){
            return true;
        }
        if(playerDistanceToMapRight <= screenHalfWidth){
            return true;
        }
        return false;
    }
}