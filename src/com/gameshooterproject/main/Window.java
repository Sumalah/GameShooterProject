package com.gameshooterproject.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9;
    public static int WIDTH;
    public static int HEIGHT;
    public final String TITLE = "Captain Firecracker";

    public Window(MainLoop mainLoop){
        JFrame frame = new JFrame(TITLE);

        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        mainLoop.setIgnoreRepaint(true);
        frame.add(mainLoop);
        frame.setVisible(true);

        WIDTH = frame.getContentPane().getWidth();
        HEIGHT = frame.getContentPane().getHeight();

        mainLoop.start();
    }

}
