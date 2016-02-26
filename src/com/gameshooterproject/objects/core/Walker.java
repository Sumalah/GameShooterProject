package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

public abstract class Walker extends GameObject { //they had to be circles
    protected int vel;
    protected int direction;
    protected int offsetX, offsetY;
    protected int turning;
    protected int health;

    public Walker(int x, int y, int width, int height, ID id, int health) {
        super(x, y, width, height, id);
        this.health = health;
        vel = 0;
        direction = 180;
        turning = 0;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if(direction > 359){
            this.direction = 0;
        }
        else if(direction < 0){
            this.direction = 359;
        }
        else{
            this.direction = direction;
        }
    }

    public boolean isAlive(){
        if(health > 0){
            return true;
        }
        return false;
    }

    public void stop(){
        vel = 0;
    }
    protected void updateWalkerOffset() {
        offsetX = (int)(vel * Math.sin(Math.toRadians(direction)));
        offsetY = (int)(vel * Math.cos(Math.toRadians(direction)));
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public void speedUpForward(){
        vel = 6;
    }

    public void speedUpBackward(){
        vel = -4;
    }

    public void turnLeft(){
        turning = 5;
    }

    public void turnRight(){
        turning = -5;
    }

    public void stopTurning(){
        turning = 0;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
