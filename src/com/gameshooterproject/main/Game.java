package com.gameshooterproject.main;

import com.gameshooterproject.basic.*;
import com.gameshooterproject.handlers.CollisionHandler;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.handlers.Spawner;
import com.gameshooterproject.objects.BasicZombie;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.core.Weapon;
import com.gameshooterproject.objects.mapelements.MapObstacle;

import java.awt.*;

public class Game extends Canvas {
    MainLoop mainLoop;
    CollisionHandler collisionHandler;
    GameMapHolder gameMapHolder;
    WalkersHolder walkersHolder;
    BulletsHolder bulletsHolder;
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
    }

    private void initGameObjects() {
        bulletsHolder = new BulletsHolder();
        initPlayer();
        initMap();
        makeGameLevel();

        hud = new HUD(50, 50, 100, 25, ID.Hud, player);
        camera = new Camera(gameMapHolder, walkersHolder);
    }

    private void initPlayer() {
        player = new Player(0, 0, 50, 50, ID.Player, 200);
        weapon = new Weapon("Pistol", 1, 30, player, bulletsHolder);
        player.addWeapon(weapon);

        walkersHolder = new WalkersHolder(player);
    }

    private void initMap() {
        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        gameMapHolder = new GameMapHolder(gameMap);
    }

    private void makeGameLevel() {
        gameMapHolder.addNewObject(new MapObstacle(100, 100, 200, 50, ID.MapObstacles));
        gameMapHolder.addNewObject(new MapObstacle(100, 300, 200, 50, ID.MapObstacles));
        gameMapHolder.addNewObject(new MapObstacle(600, 600, 100, 500, ID.MapObstacles));
    }

    private void initHandlers() {
        spawner = new Spawner(walkersHolder);
        mainLoop.addKeyListener(new PlayerKeyHandler(player, bulletsHolder));
        collisionHandler = new CollisionHandler(gameMapHolder, walkersHolder, bulletsHolder);
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update(){
        weapon.update();
        spawner.update();

        gameMapHolder.update();
        walkersHolder.update();
        bulletsHolder.update();

        collisionHandler.update();
        hud.update();
        camera.update();
    }

    public void draw(Graphics g){
        gameMapHolder.draw(g);
        walkersHolder.draw(g);
        bulletsHolder.draw(g);
        hud.draw(g);
    }
}