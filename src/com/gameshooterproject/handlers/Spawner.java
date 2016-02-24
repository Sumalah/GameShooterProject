package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.BasicZombie;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Walker;

import java.util.Random;

public class Spawner {

    WalkersHolder walkersHolder;
    Player player;

    public Spawner(WalkersHolder walkersHolder) {
        this.walkersHolder = walkersHolder;
        player = walkersHolder.getPlayer();
    }

    public void update(){
        removeDeadZombies();
        if(countWalkers() < 7){
            spawnNewRandomBasicZombie();
        }
    }

    private void removeDeadZombies() {
        for(int i = 0; i < walkersHolder.getGameObjectLinkedList().size(); i++){
            Walker zombie = (Walker) walkersHolder.getGameObjectLinkedList().get(i);
            if (zombie.getId() == ID.BasicZombie){
                BasicZombie basicZombie = (BasicZombie) zombie;

                if(!basicZombie.isAlive()){
                    walkersHolder.getGameObjectLinkedList().remove(basicZombie);
                    System.out.println("Foo");
                }
            }
        }
    }

    private void spawnNewRandomBasicZombie() {
        Random random = new Random();
        int x = random.nextInt(Window.WIDTH);
        int y = random.nextInt(Window.HEIGHT);

        walkersHolder.addNewObject(new BasicZombie(x, y, 70, 70, ID.BasicZombie, player, 31));
    }

    private int countWalkers(){
        int count = 0;

        for(int i = 0; i < walkersHolder.getGameObjectLinkedList().size(); i++){
            if(walkersHolder.getGameObjectLinkedList().get(i).getId() != ID.Player){
                count++;
            }
        }

        return count;
    }
}
