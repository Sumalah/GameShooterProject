package com.gameshooterproject.basic;


import java.awt.*;

public class GeneralHolder {

    private BulletsHolder bulletsHolder;
    private CrateHolder crateHolder;
    private GameMapHolder gameMapHolder;
    private WalkersHolder walkersHolder;

    public GeneralHolder(BulletsHolder bulletsHolder, CrateHolder crateHolder, GameMapHolder gameMapHolder, WalkersHolder walkersHolder) {
        this.bulletsHolder = bulletsHolder;
        this.crateHolder = crateHolder;
        this.gameMapHolder = gameMapHolder;
        this.walkersHolder = walkersHolder;
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
}
