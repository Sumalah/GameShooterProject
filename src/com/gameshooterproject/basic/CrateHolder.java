package com.gameshooterproject.basic;

public class CrateHolder extends BasicHolder {

    @Override
    public void update() {
        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            gameObjectLinkedList.get(i).update();
        }
    }
}
