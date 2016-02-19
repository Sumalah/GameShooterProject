package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.GameMapHolder;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Walker;

import java.util.LinkedList;

public class CollisionHandler {
    private Player player;
    private GameMap gameMap;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;

    public CollisionHandler(GameMapHolder gameMapHolder, WalkersHolder walkersHolder) {
        this.gameMapHolder = gameMapHolder;
        this.walkersHolder = walkersHolder;
        this.player = walkersHolder.getPlayer();
        this.gameMap = gameMapHolder.getGameMap();
    }

    public void update() {
        handleCollisions();
    }

    private void handleCollisions() {
        keepWalkersInGameMap();
    }

    private void keepWalkersInGameMap() {
        LinkedList<Walker> walkerLinkedList = walkersHolder.getWalkerObjectsList();

        for(int i = 0; i < walkerLinkedList.size(); i++){
            Walker tempWalker = walkerLinkedList.get(i);

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
