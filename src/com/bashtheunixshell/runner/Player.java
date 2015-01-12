package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;

import java.io.IOException;
import java.nio.file.Paths;

public class Player extends Sprite {

    private Texture texture;
    private int gravity;
    private boolean hasJumped = false;
    private int level = 1;

    // Sets the image to this class
    public Player(int x, int y) {
        texture = new Texture();
        gravity = 0;

        try {
            texture.loadFromFile(Paths.get("res/block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);
        setPosition(x, y);
    }

    // Moves the player with key input
    public void move(Ground ground) {
        gravity++;
        move(0, gravity);
        if (onTheGround(ground)) {
            if (Keyboard.isKeyPressed(Key.LCONTROL)) {
                gravity = -20;
            }
            setPosition(X1(), ground.Y1() - 32);
        }

        if (level == 2) {
            if (Keyboard.isKeyPressed(Key.RIGHT)) {
                move(4, 0);
            }
        } else if (level == 3) {
            if (Keyboard.isKeyPressed(Key.RIGHT)) {
                move(4, 0);
            }
            if (Keyboard.isKeyPressed(Key.LEFT)) {
                move(-4, 0);
            }
        }
    }

    // Stops the player whenever it touches one of the side edges
    public void checkBoundaries(int width, int height) {
        if (X1() <= 0) {
            setPosition(0, Y1());
        } else if (X2() >= width) {
            setPosition(width - 32, Y1());
        }
    }

    // Adds a point whenever the player jumps over a block
    // Take note of this flag
    public int addPoints(int points, Block block) {
        if (X1() > block.X1() && X1() < block.X2()) {
            if (!hasJumped) {
                ++points;
                hasJumped = true;
            }
        } else {
            hasJumped = false;
        }

        return points;
    }

    // Changes the game's level based on the current score
    public void changeLevel(int points) {
        if (points == 10) {
            level = 2;
        } else if (points == 20) {
            level = 3;
        }
    }

    // Returns whether the player is on the ground or not
    public boolean onTheGround(Ground ground) {
        return (Y2() > ground.Y1());
    }

    // Checks the collision with a block
    public boolean isColliding(Block block) {
        return (X1() < block.X2() && X2() > block.X1() && Y1() < block.Y2() && Y2() > block.Y1());
    }

    // These next few methods return the geometry of the player

    public float X1() {
        return getGlobalBounds().left;
    }

    public float X2() {
        return getGlobalBounds().left + getGlobalBounds().width;
    }

    public float Y1() {
        return getGlobalBounds().top;
    }

    public float Y2() {
        return getGlobalBounds().top + getGlobalBounds().height;
    }
}