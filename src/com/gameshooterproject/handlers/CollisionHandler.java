package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.*;
import com.gameshooterproject.holders.*;
import com.gameshooterproject.objects.GameMapField;
import com.gameshooterproject.objects.Bullet;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.Weapon;
import com.gameshooterproject.objects.core.GameObject;
import com.gameshooterproject.objects.core.Walker;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class CollisionHandler {
    private final int TWO_RECTANGLES = 0;
    private final int RECTANGLE_AND_OVAL = 1;

    private GameMapField gameMapField;
    private Spawner spawner;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;
    private BulletsHolder bulletsHolder;
    private CrateHolder crateHolder;

    private LinkedList<GameObject> walkerLinkedList;
    private LinkedList<GameObject> bulletsList;
    private LinkedList<GameObject> mapObjectsList;
    private LinkedList<GameObject> crateObjectList;

    public CollisionHandler(GeneralHolder generalHolder, Spawner spawner) {
        this.spawner = spawner;

        this.gameMapHolder = generalHolder.getGameMapHolder();
        this.walkersHolder = generalHolder.getWalkersHolder();
        this.bulletsHolder = generalHolder.getBulletsHolder();
        this.crateHolder = generalHolder.getCrateHolder();

        this.gameMapField = gameMapHolder.getGameMapField();

        walkerLinkedList = walkersHolder.getGameObjectLinkedList();
        bulletsList = bulletsHolder.getGameObjectLinkedList();
        mapObjectsList = gameMapHolder.getGameObjectLinkedList();
        crateObjectList = crateHolder.getGameObjectLinkedList();
    }

    public void update() {
        handleWalkers();
        handleBullets();

        ifPlayerOnWeaponGetIt();
    }

    private void handleWalkers() {
        for(int i = 0; i < walkerLinkedList.size(); i++) {
            Walker walker = (Walker) walkerLinkedList.get(i);

            keepWalkerInGameMap(walker);
            keepWalkerAwayFromObstacles(walker);
            checkIfWalkerHitByBullet(walker);
        }
    }

    private void handleBullets() {
        for(int i = 0; i < bulletsList.size(); i++) {
            Bullet bullet = (Bullet) bulletsList.get(i);

            removeBulletIfOutOfGameMap(bullet);
            removeBulletsWhichHitsObstacles(bullet);
            destroyCratesHitByBullet(bullet);
        }
    }

    private void removeBulletIfOutOfGameMap(Bullet bullet) {
        if((bullet.getX() < gameMapField.getX() || bullet.getX() > gameMapField.getX() + gameMapField.getWidth())
                || (bullet.getY() < gameMapField.getY() || bullet.getY() > gameMapField.getY() + gameMapField.getHeight())){
            bulletsHolder.removeObject(bullet);
        }
    }

    private void removeBulletsWhichHitsObstacles(Bullet bullet) {
        for(int i = 0; i < mapObjectsList.size(); i++){
            GameObject mapObject = mapObjectsList.get(i);

            if(mapObject.getId() != ID.MapField){
                if(isCollision(bullet, mapObject, TWO_RECTANGLES)){
                    bulletsHolder.removeObject(bullet);
                }
            }
        }
    }

    private void destroyCratesHitByBullet(Bullet bullet) {
        for(int i = 0; i < crateObjectList.size(); i++){
            GameObject crateObject = crateObjectList.get(i);

            if(crateObject.getId() == ID.Crate) {
                if (isCollision(bullet, crateObject, TWO_RECTANGLES)) {
                    crateHolder.removeObject(crateObject);
                    bulletsHolder.removeObject(bullet);
                    spawner.setSpawnWeapon(true);
                }
            }
        }
    }

    private void ifPlayerOnWeaponGetIt() {
        for(int i = 0; i < crateObjectList.size(); i++){
            GameObject weaponObject = crateObjectList.get(i);
            Player player = walkersHolder.getPlayer();

            if(weaponObject.getId() == ID.Weapon){
                if(isCollision(weaponObject, player, RECTANGLE_AND_OVAL)){
                    player.addWeapon((Weapon)weaponObject);
                    crateObjectList.remove(weaponObject);
                }
            }
        }
    }

    private void checkIfWalkerHitByBullet(Walker walker) {
        for(int j = 0; j < bulletsList.size(); j++){
            Bullet tempBullet = (Bullet) bulletsList.get(j);

            if(isCollision(tempBullet, walker, RECTANGLE_AND_OVAL)){
                if(walker.getId() == ID.BasicZombie) {
                    walker.takeDamage(tempBullet.damage);
                    bulletsList.remove(tempBullet);
                }
            }
        }
    }

    private void keepWalkerAwayFromObstacles(Walker walker) { //BTW what if obstacle will have direction var?
        for(int j = 0; j < mapObjectsList.size(); j++){
            GameObject tempMapObject = mapObjectsList.get(j);

            if(tempMapObject.getId() == ID.MapObstacles){
                if(isCollision(tempMapObject, walker, RECTANGLE_AND_OVAL)){
                    backObjectFromObstacle(walker, tempMapObject);
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

    private boolean isCollision(GameObject firstObject, GameObject secondObject, int option) {
        Shape shape2;
        Shape shape1 = new Rectangle2D.Double(firstObject.getX(), firstObject.getY(), firstObject.getWidth(), firstObject.getHeight());

        if(option == RECTANGLE_AND_OVAL){
            shape2 = new Ellipse2D.Double(secondObject.getX(), secondObject.getY(), secondObject.getWidth(), secondObject.getHeight());
        }else{
            shape2 = new Rectangle2D.Double(secondObject.getX(), secondObject.getY(), secondObject.getWidth(), secondObject.getHeight());
        }


        if(shape2.intersects(shape1.getBounds())){
            return true;
        }

        return false;
    }

    private void keepWalkerInGameMap(Walker walker) {
        int walkerY = walker.getY();
        int walkerX = walker.getX();
        int walkerHeight = walker.getHeight();
        int walkerWidth = walker.getWidth();

            if(isWalkerCrossingTopLine(walkerY, walkerHeight)){
                walker.setOffsetY(1);
            }
            if(isWalkerCrossingBottomLine(walkerY, walkerHeight)){
                walker.setOffsetY(-1);
            }
            if(isWalkerCrossingLeftLine(walkerX, walkerWidth)){
                walker.setOffsetX(1);
            }
            if(isWalkerCrossingRightLine(walkerX, walkerWidth)){
                walker.setOffsetX(-1);
            }
    }

    private boolean isWalkerCrossingTopLine(int y, int height) {
        if(y - height < gameMapField.getY()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingBottomLine(int y, int height) {
        if(y +  height > gameMapField.getY() + gameMapField.getHeight()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingLeftLine(int x, int width) {
        if(x - width < gameMapField.getX()){
            return true;
        }
        return false;
    }

    private boolean isWalkerCrossingRightLine(int x, int width) {
        if(x +  width > gameMapField.getX() + gameMapField.getWidth()){
            return true;
        }
        return false;
    }
}
