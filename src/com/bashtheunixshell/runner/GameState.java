package com.bashtheunixshell.runner;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class GameState implements State {

    private Player          player;
    private Block           block;
    private Ground          ground;
    private Background      sky, clouds1, clouds2, building1, building2, floor;
    private Dialog          pointsText, lostText;
    private float           velocityX;
    private boolean         lost, clearedblock, keyPressed;
    private int             points;

    public void enter() {
        player          = new Player(32, Main.HEIGHT - 64);
        block           = new Block(Main.WIDTH, Main.HEIGHT - 64);
        ground          = new Ground(0, Main.HEIGHT - 32);
        sky             = new Background("sky", 0, 0);
        clouds1         = new Background("clouds", 0, 0);
        clouds2         = new Background("clouds", Main.WIDTH, 0);
        building1       = new Background("buildings", 0, 0);
        building2       = new Background("buildings", Main.WIDTH, 0);
        floor           = new Background("floor", 0, Main.HEIGHT - 32);
        pointsText      = new Dialog("0", 0, 0, Color.BLACK);
        lostText        = new Dialog("     You lost\nPress <backspace>", Main.WIDTH / 2, Main.HEIGHT / 2, Color.BLACK);
        velocityX       = -3;
        points          = 0;

        pointsText.move(pointsText.width() / 2 + 5, pointsText.height() / 2 + 5);
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

        pointsText.draw(window);
        if (lost) {
            lostText.draw(window);
        }
        //System.out.println(points);

        window.display();
    }

    public void update(RenderWindow window) {
        for (Event event : window.pollEvents()) {
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE)) {
                window.close();
                System.exit(0);
            }
        }
        if (Keyboard.isKeyPressed(Key.BACKSPACE)) {
            Main.mainGame.startMenu();
        }

        velocityX -= 0.001;
        block.move(velocityX, 0);
        block.checkBoundaries(Main.WIDTH, Main.HEIGHT);
        player.move(ground);
        player.checkBoundaries(Main.WIDTH, Main.HEIGHT);
        player.changeLevel(points);

        if (!lost) {
            points = player.addPoints(points, block);
            pointsText.setString("" + points);
        }

        if (player.isColliding(block)) {
            lost = true;
        }

        if (lost) {
            velocityX = 0;
        }
    }
}