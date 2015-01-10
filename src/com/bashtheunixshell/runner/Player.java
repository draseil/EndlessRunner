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

    public void move(Ground ground) {
        gravity++;
        move(0, gravity);
        if (Keyboard.isKeyPressed(Key.LCONTROL)) {
            if (onTheGround(ground)) {
                gravity = -20;
            }
        }
    }

    public void checkBoundaries(int width, int height) {
        if (X1() <= 0) {
            setPosition(0, Y1());
        } else if (X2() >= width) {
            setPosition(width - 32, Y1());
        }
    }

    public boolean onTheGround(Ground ground) {
        return (Y2() > ground.Y1());
    }

    public void draw(RenderWindow window) {
        window.draw(this);
    }

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