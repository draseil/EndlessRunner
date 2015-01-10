package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class GameState implements State {

    private Player          player;
    private Block           block;
    private Ground          ground;
    private Background      sky, clouds1, clouds2, building1, building2, floor;
    private float           velocityX, velocityY;
    private boolean         lost, clearedBlock, keyPressed;
    private int             points;

    public void enter() {
        player          = new Player(32, Main.HEIGHT - 64);
        block           = new Block(Main.WIDTH, Main.HEIGHT - 64);
        ground          = new Ground(0, Main.HEIGHT - 32);
        sky             = new Background("sky");
        clouds1         = new Background("clouds");
        clouds2         = new Background("clouds");
        building1       = new Background("buildings");
        building2       = new Background("buildings");
        floor           = new Background("floor");
        velocityX       = -3;
        points          = 0;
    }

    public void exit() {}

    public void draw(RenderWindow window) {
        window.clear();

        sky.draw(window);
        clouds1.draw(window);
        clouds1.animate(window, velocityX / 3, Main.WIDTH);
        clouds2.animate(window, velocityX / 3, Main.WIDTH);
        building1.animate(window, velocityX / 3 * 2, Main.WIDTH);
        building2.animate(window, velocityX / 3 * 2, Main.WIDTH);
        ground.draw(window);
        block.draw(window);
        player.draw(window);

        window.display();
    }

    public void update(RenderWindow window) {
        for (Event event : window.pollEvents()) {
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE)) {
                window.close();
                System.exit(0);
            }
        }

        velocityX -= 0.001;
        block.move(velocityX, 0);
        block.checkBoundaries(Main.WIDTH, Main.HEIGHT);
        player.move(ground);
        player.checkBoundaries(Main.WIDTH, Main.HEIGHT);
    }
}