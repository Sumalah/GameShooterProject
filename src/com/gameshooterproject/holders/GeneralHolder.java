package com.gameshooterproject.holders;


import com.gameshooterproject.objects.GameMapField;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class GeneralHolder {

    private BulletsHolder bulletsHolder;
    private CrateHolder crateHolder;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;

    public GeneralHolder(Player player, GameMapField gameMapField) {
        bulletsHolder = new BulletsHolder();
        crateHolder = new CrateHolder();
        walkersHolder = new WalkersHolder(player);
        gameMapHolder = new GameMapHolder(gameMapField);
    }

    public void update(){
        gameMapHolder.update();
        walkersHolder.update();
        bulletsHolder.update();
        crateHolder.update();
    }

    public void draw(Graphics g){
        gameMapHolder.draw(g);
        walkersHolder.draw(g);
        bulletsHolder.draw(g);
        crateHolder.draw(g);
    }

    public BulletsHolder getBulletsHolder() {
        return bulletsHolder;
    }

    public CrateHolder getCrateHolder() {
        return crateHolder;
    }

    public GameMapHolder getGameMapHolder() {
        return gameMapHolder;
    }

    public WalkersHolder getWalkersHolder() {
        return walkersHolder;
    }

    public LinkedList<GameObject> getAll(){
        LinkedList<GameObject> list = new LinkedList<>();

        list.addAll(walkersHolder.getGameObjectLinkedList());
        list.addAll(gameMapHolder.getGameObjectLinkedList());
        list.addAll(bulletsHolder.getGameObjectLinkedList());
        list.addAll(crateHolder.getGameObjectLinkedList());

        return list;
    }
}
