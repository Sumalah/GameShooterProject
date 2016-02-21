package com.gameshooterproject.main;

import com.gameshooterproject.basic.Camera;
import com.gameshooterproject.basic.GameMapHolder;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.handlers.CollisionHandler;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;
import com.gameshooterproject.objects.mapelements.MapObstacle;

import java.awt.*;

public class Game extends Canvas {
    MainLoop mainLoop;
    CollisionHandler collisionHandler;
    GameMapHolder gameMapHolder;
    WalkersHolder walkersHolder;
    GameMap gameMap;
    Player player;
    Camera camera;

    public Game(){
        mainLoop = new MainLoop(this);
        new Window(mainLoop);

        initGameObjects();
        initHandlers();
    }

    private void initGameObjects() {
        player = new Player(0, 0, 50, 50, ID.Player);
        walkersHolder = new WalkersHolder(player);

        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        gameMapHolder = new GameMapHolder(gameMap);
        makeGameLevel();

        camera = new Camera(gameMapHolder, walkersHolder);
    }

    private void makeGameLevel() {
        gameMapHolder.addNewMapObject(new MapObstacle(100, 100, 200, 50, ID.MapObstacles));
    }

    private void initHandlers() {
        mainLoop.addKeyListener(new PlayerKeyHandler(player));
        collisionHandler = new CollisionHandler(gameMapHolder, walkersHolder);
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update(){
        gameMapHolder.update();
        walkersHolder.update();
        collisionHandler.update();
        camera.update();
    }

    public void draw(Graphics g){
        buildBasicScene(g);
    }

    private void buildBasicScene(Graphics g) {
        gameMapHolder.draw(g);
        player.draw(g);
    }
}