package com.gameshooterproject.main;

import com.gameshooterproject.basic.Camera;
import com.gameshooterproject.basic.ID;
import com.gameshooterproject.handlers.PlayerKeyHandler;
import com.gameshooterproject.objects.GameMap;
import com.gameshooterproject.objects.Player;

import java.awt.*;

public class Game extends Canvas {
    MainLoop mainLoop;
    GameMap gameMap;
    Player player;
    Camera camera;

    public Game(){
        mainLoop = new MainLoop(this);
        new Window(mainLoop);
        camera = new Camera();
        initGameObjects();
        initHandlers();
    }

    private void initGameObjects() {
        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        player = new Player(0, 0, 50, 20, ID.Player);
    }

    private void initHandlers() {
        mainLoop.addKeyListener(new PlayerKeyHandler(camera));
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update(){
        player.update();
        camera.update();
    }

    public void draw(Graphics g){
        buildBasicScene(g);
    }

    private void buildBasicScene(Graphics g) {
        camera.addNewObject(gameMap);
        camera.addNewObject(player);

        gameMap.draw(g);
        player.draw(g);
    }
}