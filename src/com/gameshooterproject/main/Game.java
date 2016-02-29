package com.gameshooterproject.main;

import com.gameshooterproject.basic.*;
import com.gameshooterproject.graphics.BufferedImageLoader;
import com.gameshooterproject.handlers.CollisionHandler;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.handlers.Spawner;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends Canvas {
    MainLoop mainLoop;
    CollisionHandler collisionHandler;
    GameMapHolder gameMapHolder;
    WalkersHolder walkersHolder;
    BulletsHolder bulletsHolder;
    GeneralHolder generalHolder;
    CrateHolder crateHolder;
    GameMap gameMap;
    Player player;
    Camera camera;
    Weapon weapon;
    HUD hud;
    Spawner spawner;

    public Game(){
        mainLoop = new MainLoop(this);
        new Window(mainLoop);

        initGameObjects();
        initHandlers();

        mainLoop.start();

    }

    private void initGameObjects() {
        bulletsHolder = new BulletsHolder();
        crateHolder = new CrateHolder();
        initPlayer();
        initMap();

        generalHolder = new GeneralHolder(bulletsHolder, crateHolder, gameMapHolder, walkersHolder);
        hud = new HUD(50, 50, 100, 25, ID.Hud, player);
        camera = new Camera(generalHolder);
    }

    private void initPlayer() {
        player = new Player(0, 0, 50, 50, ID.Player, 200);
        weapon = new Weapon(0, 0, 0, 0, ID.Weapon, "Pistol", 10, 30, player, bulletsHolder);
        player.addWeapon(weapon);

        walkersHolder = new WalkersHolder(player);
    }

    private void initMap() {
        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        gameMapHolder = new GameMapHolder(gameMap);
    }

    private void initHandlers() {
        spawner = new Spawner(generalHolder);
        mainLoop.addKeyListener(new PlayerKeyHandler(player, bulletsHolder));
        collisionHandler = new CollisionHandler(generalHolder, spawner);
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update(){
        player.getWeapon().update();
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