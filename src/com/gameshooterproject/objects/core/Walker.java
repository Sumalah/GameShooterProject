package com.gameshooterproject.objects.core;

import com.gameshooterproject.basic.ID;

public abstract class Walker extends GameObject {
    protected int vel;
    protected int direction;

    public Walker(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        vel = 0;
        direction = 180;
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

    public void stop(){
//        Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            int count = 0;
//
//            @Override
//            public void run() {
//                vel -= 1;
//                count++;
//                if (count >= 5) {
//                    this.cancel();
//                }
//            }
//        }, 300, 300);
        vel = 0;
    }

    public void speedUpForward(){
        vel = 4;
    }

    public void speedUpBackward(){
        vel = -4;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }
}
