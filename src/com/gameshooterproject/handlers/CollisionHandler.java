package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.BulletsHolder;
import com.gameshooterproject.basic.GameMapHolder;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Bullet;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class CollisionHandler {
    private GameMap gameMap;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;
    private BulletsHolder bulletsHolder;

    public CollisionHandler(GameMapHolder gameMapHolder, WalkersHolder walkersHolder, BulletsHolder bulletsHolder) {
        this.gameMapHolder = gameMapHolder;
        this.walkersHolder = walkersHolder;
        this.bulletsHolder = bulletsHolder;
        this.gameMap = gameMapHolder.getGameMap();
    }

    public void update() {
        handleCollisions();
        handleBulletHits();
    }

    private void handleBulletHits() {
        LinkedList<GameObject> walkerLinkedList = walkersHolder.getGameObjectLinkedList();
        LinkedList<GameObject> bullets = bulletsHolder.getGameObjectLinkedList();

        for(int i = 0; i < walkerLinkedList.size(); i++){
            Walker tempWalker = (Walker)walkerLinkedList.get(i);

            for(int j = 0; j < bullets.size(); j++){
                Bullet tempBullet = (Bullet)bullets.get(j);

                if(isCollision(tempBullet, tempWalker)){
                    if(tempWalker.getId() == ID.BasicZombie) {
                        tempWalker.takeDamage(tempBullet.damage);
                        bullets.remove(tempBullet);
                    }
                }
            }
        }
    }

    private void handleCollisions() {
        keepWalkersInGameMap();
        keepWalkersAwayFromObstacles();
    }

    private void keepWalkersAwayFromObstacles() { //BTW what if obstacle will have direction var?
        LinkedList<GameObject> walkerLinkedList = walkersHolder.getGameObjectLinkedList();
        LinkedList<GameObject> mapObjectsList = gameMapHolder.getGameObjectLinkedList();

        for(int i = 0; i < walkerLinkedList.size(); i++){
            Walker tempWalker = (Walker)walkerLinkedList.get(i);

            for(int j = 0; j < mapObjectsList.size(); j++){
                GameObject tempMapObject = mapObjectsList.get(j);

                if(tempMapObject.getId() == ID.MapObstacles){
                    if(isCollision(tempMapObject, tempWalker)){
                        backObjectFromObstacle(tempWalker, tempMapObject);
                    }
                }
            }
        }
    }

    private void backObjectFromObstacle(Walker tempWalker, GameObject tempMapObject) {

        int mapObjectX = tempMapObject.getX();
        int mapObjectY = tempMapObject.getY();
        int mapObjectWidth = tempMapObject.getWidth();
        int mapObjectHeight = tempMapObject.getHeight();

        Shape oval = new Ellipse2D.Double(tempWalker.getX(), tempWalker.getY(), tempWalker.getWidth(), tempWalker.getHeight());
        Shape leftBound = new Rectangle2D.Double(mapObjectX, mapObjectY, 1, mapObjectHeight);
        Shape rightBound = new Rectangle2D.Double(mapObjectX+mapObjectWidth, mapObjectY, 1, mapObjectHeight);
        Shape topBound = new Rectangle2D.Double(mapObjectX, mapObjectY, mapObjectWidth, 1);
        Shape bottomBound = new Rectangle2D.Double(mapObjectX, mapObjectY + mapObjectHeight, mapObjectWidth, 1);

        if(oval.intersects(leftBound.getBounds())){
            tempWalker.setOffsetX(-1);
        }
        if(oval.intersects(rightBound.getBounds())){
            tempWalker.setOffsetX(1);
        }
        if(oval.intersects(topBound.getBounds())){
            tempWalker.setOffsetY(-1);
        }
        if(oval.intersects(bottomBound.getBounds())){
            tempWalker.setOffsetY(1);
        }

    }

    private boolean isCollision(GameObject tempMapObject, Walker tempWalker) {
        Shape oval = new Ellipse2D.Double(tempWalker.getX(), tempWalker.getY(), tempWalker.getWidth(), tempWalker.getHeight());
        Shape rect = new Rectangle2D.Double(tempMapObject.getX(), tempMapObject.getY(), tempMapObject.getWidth(), tempMapObject.getHeight());

        if(oval.intersects(rect.getBounds())){
            return true;
        }

        return false;
    }

    private void keepWalkersInGameMap() {
        LinkedList<GameObject> walkerLinkedList = walkersHolder.getGameObjectLinkedList();

        for(int i = 0; i < walkerLinkedList.size(); i++){
            Walker tempWalker = (Walker)walkerLinkedList.get(i);

            if(isWalkerCrossingTopLine(tempWalker)){
                tempWalker.setOffsetY(1);
            }
            if(isWalkerCrossingBottomLine(tempWalker)){
                tempWalker.setOffsetY(-1);
            }
            if(isWalkerCrossingLeftLine(tempWalker)){
                tempWalker.setOffsetX(1);
            }
            if(isWalkerCrossingRightLine(tempWalker)){
                tempWalker.setOffsetX(-1);
            }
        }
    }

    private boolean isWalkerCrossingTopLine(Walker walker) {
        int walkerY = walker.getY();
        int walkerHeight = walker.getHeight();

        if(walkerY - walkerHeight < gameMap.getY()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingBottomLine(Walker walker) {
        int walkerY = walker.getY();
        int walkerHeight = walker.getHeight();

        if(walkerY +  walkerHeight > gameMap.getY() + gameMap.getHeight()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingLeftLine(Walker walker) {
        int walkerX = walker.getX();
        int walkerWidth = walker.getWidth();

        if(walkerX - walkerWidth < gameMap.getX()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingRightLine(Walker walker) {
        int walkerX = walker.getX();
        int walkerWidth = walker.getWidth();

        if(walkerX +  walkerWidth > gameMap.getX() + gameMap.getWidth()){
            return true;
        }
        return false;
    }
}
