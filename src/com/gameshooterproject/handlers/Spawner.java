package com.gameshooterproject.handlers;

import com.gameshooterproject.basic.BulletsHolder;
import com.gameshooterproject.basic.CrateHolder;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.main.Window;
import com.gameshooterproject.objects.BasicZombie;
import com.gameshooterproject.objects.Crate;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.Weapon;
import com.gameshooterproject.objects.core.Walker;

import java.util.Random;

public class Spawner {

    WalkersHolder walkersHolder;
    CrateHolder  crateHolder;
    BulletsHolder bulletsHolder;
    Player player;
    Random random;
    int crateX, crateY;
    boolean spawnZombie;
    boolean spawnCrate;
    boolean spawnWeapon;

    public Spawner(WalkersHolder walkersHolder, CrateHolder crateHolder, BulletsHolder bulletsHolder) {
        this.walkersHolder = walkersHolder;
        this.crateHolder = crateHolder;
        this.bulletsHolder = bulletsHolder;
        player = walkersHolder.getPlayer();
        random = new Random();
        crateX = 0;
        crateY = 0;

        spawnZombie = false;
        spawnCrate = true;
    }

    public void update(){
        removeDeadZombies();
        if(isSpawnZombie()){
            spawnNewRandomBasicZombie();
        }
        if(isSpawnCrate()){
            spawnNewRandomCrate();
        }
        if(isSpawnWeapon()){
            spawnNewRandomWeapon();
        }
    }

    private void spawnNewRandomWeapon() {
        Weapon newWeapon = new Weapon(crateX, crateY, 20, 50, ID.Weapon, "ak-47", 2, 30, player, bulletsHolder);
        newWeapon.setOnGround(true);
        crateHolder.addNewObject(newWeapon);
        setSpawnWeapon(false);
    }

    private void spawnNewRandomCrate() {
        int x = random.nextInt(Window.WIDTH);
        int y = random.nextInt(Window.HEIGHT);

        Crate crate = new Crate(x, y, 50, 50, ID.Crate);
        crateHolder.addNewObject(crate);
//        crateX = x;
//        crateY = y;
        spawnCrate = false;
    }

    private void removeDeadZombies() {
        for(int i = 0; i < walkersHolder.getGameObjectLinkedList().size(); i++){
            Walker zombie = (Walker) walkersHolder.getGameObjectLinkedList().get(i);
            if (zombie.getId() == ID.BasicZombie){
                BasicZombie basicZombie = (BasicZombie) zombie;

                if(!basicZombie.isAlive()){
                    walkersHolder.getGameObjectLinkedList().remove(basicZombie);
                }
            }
        }
    }

    private void spawnNewRandomBasicZombie() {
        int x = random.nextInt(Window.WIDTH);
        int y = random.nextInt(Window.HEIGHT);

        walkersHolder.addNewObject(new BasicZombie(x, y, 70, 70, ID.BasicZombie, player, 31));
        setSpawnZombie(false);
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

    public boolean isSpawnWeapon() {
        return spawnWeapon;
    }

    public void setSpawnWeapon(boolean spawnWeapon) {
        this.spawnWeapon = spawnWeapon;
    }

    public boolean isSpawnCrate() {
        return spawnCrate;
    }

    public void setSpawnCrate(boolean spawnCrate) {
        this.spawnCrate = spawnCrate;
    }

    public boolean isSpawnZombie() {
        return spawnZombie;
    }

    public void setSpawnZombie(boolean spawnZombie) {
        this.spawnZombie = spawnZombie;
    }
}
