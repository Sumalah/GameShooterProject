package com.gameshooterproject.main;

import com.gameshooterproject.basic.*;
import com.gameshooterproject.handlers.CollisionHandler;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.handlers.Spawner;
import com.gameshooterproject.objects.GameMapField;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.Weapon;

import java.awt.*;

public class Game extends Canvas {

    private CollisionHandler collisionHandler;
    private GeneralHolder generalHolder;
    private GameMapField gameMapField;
    private Player player;
    private Camera camera;
    private HUD hud;
    private Spawner spawner;

    public Game(){
        MainLoop mainLoop = new MainLoop(this);
        new Window(mainLoop);

        initPlayerAndMapField();
        initMainComponents();
        addBasicWeaponToPlayer();

        mainLoop.addKeyListener(new PlayerKeyHandler(player));
        mainLoop.start();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void initPlayerAndMapField() {
        player = new Player(0, 0, 50, 50, ID.Player, 200);
        gameMapField = new GameMapField(0, 0, 0, 0, ID.Map);
    }

    private void initMainComponents() {
        generalHolder = new GeneralHolder(player, gameMapField);
        hud = new HUD(50, 50, 100, 25, ID.Hud, player);
        camera = new Camera(generalHolder);
        spawner = new Spawner(generalHolder);
        collisionHandler = new CollisionHandler(generalHolder, spawner);
    }

    private void addBasicWeaponToPlayer() {
        Weapon weapon = new Weapon(0, 0, 0, 0, ID.Weapon, "Pistol", 10, 30, player, generalHolder.getBulletsHolder());
        player.addWeapon(weapon);
    }

    public void update(){
        spawner.update();
        generalHolder.update();
        collisionHandler.update();
        hud.update();
        camera.update();
    }

    public void draw(Graphics g){
        generalHolder.draw(g);
        hud.draw(g);
    }
}