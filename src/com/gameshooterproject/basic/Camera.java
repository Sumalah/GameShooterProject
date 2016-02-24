package com.gameshooterproject.basic;

import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.core.Walker;

import java.util.LinkedList;

public class Camera {

    private LinkedList<GameObject> allObjectsList;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;
    private Player player;

    public Camera(GameMapHolder gameMapHolder, WalkersHolder walkersHolder) {
        this.gameMapHolder = gameMapHolder;
        this.walkersHolder = walkersHolder;

        getAllObjects();
    }

    private void getAllObjects() {
        allObjectsList = new LinkedList<>();
        player = walkersHolder.getPlayer();
        LinkedList<GameObject> walkersList = walkersHolder.getGameObjectLinkedList();
        LinkedList<GameObject> mapObjectsList = gameMapHolder.getGameObjectLinkedList();

        for(int i = 0; i < walkersList.size(); i++){
            Walker tempWalker = (Walker)walkersList.get(i);
            if(tempWalker.getId() != ID.Player){
                allObjectsList.add(tempWalker);
            }
        }

        for(int i = 0; i < mapObjectsList.size(); i++){
            GameObject tempMapObject = mapObjectsList.get(i);
            allObjectsList.add(tempMapObject);
        }
    }

    public void update(){
        getAllObjects();
        updatePlayerCameraLock();
        moveObjectsOrPlayer();
    }

    private void moveObjectsOrPlayer() {
        if(player.isPlayerCenterHorizontally()){
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
        GameMap map = (GameMap)getObject(ID.Map);

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

    private boolean screenBorderConnectsWithMapTopOrBottom(GameMap gameMap) {
        int screenHalfHeight = (Window.HEIGHT / 2);
        int playerDistanceToMapTop = (player.getY()) - (player.getHeight() / 2) - gameMap.getY();
        int playerDistanceToMapBottom = (gameMap.getY() + gameMap.getHeight()) - (player.getY()) - (player.getHeight() / 2);

        if(playerDistanceToMapTop <= screenHalfHeight){
            return true;
        }
        if(playerDistanceToMapBottom <= screenHalfHeight){
            return true;
        }
        return false;
    }

    private boolean screenBorderConnectsWithMapLeftOrRight(GameMap gameMap) {
        int screenHalfWidth = (Window.WIDTH / 2);
        int playerDistanceToMapLeft = (player.getX()) - (player.getWidth() / 2) - gameMap.getX();
        int playerDistanceToMapRight = (gameMap.getX() + gameMap.getWidth()) - (player.getX()) - (player.getWidth() / 2);

        if(playerDistanceToMapLeft <= screenHalfWidth){
            return true;
        }
        if(playerDistanceToMapRight <= screenHalfWidth){
            return true;
        }
        return false;
    }

    public Player getPlayerObject(){
        return player;
    }
}