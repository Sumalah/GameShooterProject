package com.gameshooterproject.main;

import com.gameshooterproject.basic.Camera;
import com.gameshooterproject.basic.GameMapHolder;
import com.gameshooterproject.basic.WalkersHolder;
import com.gameshooterproject.handlers.CollisionHandler;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;

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
        player = new Player(0, 0, 20, 50, ID.Player);
        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        gameMapHolder = new GameMapHolder(gameMap);
        walkersHolder = new WalkersHolder(player);
        camera = new Camera(gameMapHolder, walkersHolder);
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
        gameMap.draw(g);
        player.draw(g);
    }
}