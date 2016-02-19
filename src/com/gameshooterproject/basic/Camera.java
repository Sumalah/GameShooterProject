package com.gameshooterproject.basic;

import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.GameMap;
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
        updatePlayerScreenLock(player);

        if(player.isPlayerHorizontalCentered()){
            moveEveryObjectExceptPlayerHorizontaly(player);
        }else{
            movePlayerByX(player);
        }

        if(player.isPlayerVerticalCentered()){
            moveEveryObjectExceptPlayerVertical(player);
        }else{
            movePlayerByY(player);
        }
    }

    private void updatePlayerScreenLock(Player player) {
        GameMap map = (GameMap)getObject(ID.Map);
        if(screenBorderConnectsWithMapTopOrBottom(player, map)){
            player.setPlayerVerticalCentered(false);
        } else {
            player.setPlayerVerticalCentered(true);
        }

        if(screenBorderConnectsWithMapLeftOrRight(player, map)){
            player.setPlayerHorizontalCentered(false);
        } else {
            player.setPlayerHorizontalCentered(true);
        }
    }

    private void movePlayerByX(Player player) {
        player.moveByXOffset(player.getOffsetX());
    }

    private void movePlayerByY(Player player) {
        player.moveByYOffset(player.getOffsetY());
    }

    private void moveEveryObjectExceptPlayerVertical(Player player) {
        for(int i=0; i < objectList.size(); i++){
            GameObject object = objectList.get(i);

            if(object.getId() != ID.Player){
                int offsetY = (-1) * player.getOffsetY();
                object.moveByYOffset(offsetY);
            }
        }
    }

    private void moveEveryObjectExceptPlayerHorizontaly(Player player) {
        for(int i=0; i < objectList.size(); i++){
            GameObject object = objectList.get(i);

            if(object.getId() != ID.Player){
                int offsetX = (-1) * player.getOffsetX();
                object.moveByXOffset(offsetX);
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

    private boolean screenBorderConnectsWithMapTopOrBottom(Player player, GameMap gameMap) {
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

    private boolean screenBorderConnectsWithMapLeftOrRight(Player player, GameMap gameMap) {
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
}
