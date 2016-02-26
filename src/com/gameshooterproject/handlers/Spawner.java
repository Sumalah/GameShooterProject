package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.CrateHolder;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.BasicZombie;
import com.gameshooterproject.objects.Crate;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Walker;

import java.util.Random;

public class Spawner {

    WalkersHolder walkersHolder;
    CrateHolder  crateHolder;
    Player player;
    Random random;
    boolean spawnZombie;
    boolean spawnCrate;

    public Spawner(WalkersHolder walkersHolder, CrateHolder crateHolder) {
        this.walkersHolder = walkersHolder;
        this.crateHolder = crateHolder;
        player = walkersHolder.getPlayer();
        random = new Random();

        spawnZombie = false;
        spawnCrate = true;
    }

    public void update(){
        removeDeadZombies();
        if(spawnZombie){
            spawnNewRandomBasicZombie();
        }
        if(spawnCrate){
            spawnNewRandomCrate();
        }
    }

    private void spawnNewRandomCrate() {
        int x = random.nextInt(Window.WIDTH);
        int y = random.nextInt(Window.HEIGHT);

        Crate crate = new Crate(x, y, 50, 50, ID.Crate);
        crateHolder.addNewObject(crate);
        spawnCrate = false;
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
