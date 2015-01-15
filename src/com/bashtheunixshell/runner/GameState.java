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
    private Background      sky, clouds1, clouds2, building1, building2;
    private Dialog          pointsText, lostText;
    private float           velocityX;
    private boolean         lost;
    private int             points;

    // Initializes all objects when the game goes from another state to this one
    public void enter() {
        player          = new Player(32, Main.HEIGHT - 64);
        block           = new Block(Main.WIDTH, Main.HEIGHT - 64);
        ground          = new Ground(0, Main.HEIGHT - 32);
        sky             = new Background("sky", 0, 0);
        clouds1         = new Background("clouds", 0, 0);
        clouds2         = new Background("clouds", Main.WIDTH, 0);
        building1       = new Background("buildings", 0, 0);
        building2       = new Background("buildings", Main.WIDTH, 0);
        pointsText      = new Dialog("0", 0, 0, Color.BLACK);
        velocityX       = -3;
        points          = 0;

        pointsText.move(pointsText.width() / 2 + 5, pointsText.height() / 2 + 5);
    }

    // Gets called when the game goes from this state to another
    public void exit() {}

    // Draws all objects and animates the background
    public void draw(RenderWindow window) {
        window.clear();

        window.draw(sky);
        window.draw(clouds1);
        window.draw(clouds2);
        window.draw(building1);
        window.draw(building2);
        window.draw(ground);
        window.draw(block);
        window.draw(player);

        window.draw(pointsText);
        if (lost) {
            lostText = new Dialog("                   You lost\n" +
                                  "              Your score was: " + points + "\n" +
                                  "Press <backspace> to go to the main menu\n" +
                                  "      Press <Return> to restart", Main.WIDTH / 2, Main.HEIGHT / 2, Color.BLACK);
            window.draw(lostText);
        }

        window.display();
    }

    // Checks for losing, collision and updates the speed of movement
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

        clouds1.animate(velocityX / 3, Main.WIDTH);
        clouds2.animate(velocityX / 3, Main.WIDTH);
        building1.animate(velocityX / 3 * 2, Main.WIDTH);
        building2.animate(velocityX / 3 * 2, Main.WIDTH);

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

            if (Keyboard.isKeyPressed(Key.RETURN)) {
                lost = false;
                exit();
                enter();
            }
        }

        if (lost) {
            velocityX = 0;
        }
    }
}