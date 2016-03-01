package com.gameshooterproject.holders;

import com.gameshooterproject.basic.ID;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Walker;

public class WalkersHolder extends BasicHolder{
    Player player;

    public WalkersHolder(Player player) {
        super();
        this.player = player;
        addNewObject(player);
    }

    public Player getPlayer(){
        return player;
    }

    private void removeZombieIfDead(Walker zombie) {
                if(!zombie.isAlive()){
                    gameObjectLinkedList.remove(zombie);
                }
    }

    @Override
    public void update() {
        player.getWeapon().update();

        for(int i = 0; i < gameObjectLinkedList.size(); i++){
            Walker walker = (Walker) gameObjectLinkedList.get(i);
            walker.update();

            if (walker.getId() == ID.BasicZombie) {
                removeZombieIfDead(walker);
            }
        }
    }
}
