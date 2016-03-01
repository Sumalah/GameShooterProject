package com.gameshooterproject.handlers;

import com.gameshooterproject.holders.GeneralHolder;
import com.gameshooterproject.objects.core.GameObject;

import java.util.LinkedList;

public class Cleaner {

    GeneralHolder generalHolder;

    public Cleaner(GeneralHolder generalHolder) {
        this.generalHolder = generalHolder;
    }

    public void clean(LinkedList<GameObject> list){
        for(int i = 0; i < list.size(); i++){

        }
    }
}
