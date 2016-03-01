package com.gameshooterproject.holders;

public class BulletsHolder extends BasicHolder{

    public BulletsHolder(){
        super();
    }

    @Override
    public void update() {
        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            gameObjectLinkedList.get(i).update();
        }
    }
}
