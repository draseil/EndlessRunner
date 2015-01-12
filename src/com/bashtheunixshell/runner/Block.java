package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class Block extends Sprite {

    public Block(int x, int y) {
        Texture texture = new Texture();

        try {
            texture.loadFromFile(Paths.get("res/block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);
        setPosition(x, y);
    }

    public void checkBoundaries(int width, int height) {
        if (X1() <= -100) {
            Random number = new Random();
            int position = number.nextInt(1000);
            setPosition(width + position, height - 64);
        }
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
