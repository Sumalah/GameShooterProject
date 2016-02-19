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
        initGameObjects();
        initHandlers();
    }

    private void initGameObjects() {
        camera = new Camera();
        gameMap = new GameMap(0, 0, 0, 0, ID.Map);
        player = new Player(0, 0, 50, 20, ID.Player, gameMap);

        camera.addNewObject(gameMap);
        camera.addNewObject(player);
    }

    private void initHandlers() {
        mainLoop.addKeyListener(new PlayerKeyHandler(camera));
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update(){
        player.update();  // prawdopodobnie to musi byæ pierwsze
        gameMap.update();
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