package com.gameshooterproject.basic;

import com.gameshooterproject.objects.Player;

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
}
