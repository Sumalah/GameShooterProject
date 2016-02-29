package com.gameshooterproject.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainLoop extends Canvas implements Runnable{

    private Game game;
    private Thread thread;
    private boolean running = false;
    private boolean readyToRender;

    public MainLoop(Game game){
        this.game = game;
        readyToRender = false;
    }

    public synchronized  void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized  void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void update(){
        game.update();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

        game.draw(g);

        g.dispose();
        bs.show();
    }

    public void setReadyToRender(boolean value){
        this.readyToRender = value;
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Fps: " + frames);
                frames = 0;
            }
        }
        stop();
    }
}
