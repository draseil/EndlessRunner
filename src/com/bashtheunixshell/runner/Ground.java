package com.bashtheunixshell.runner;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.nio.file.Paths;
import java.io.IOException;

public class Ground extends RectangleShape {

    private Texture texture;
    private Vector2f size;

    public Ground(int x, int y) {
        texture = new Texture();
        size = new Vector2f(800, 32);

/*
        try {
            texture.loadFromFile(Paths.get("res/ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);*/

        setSize(size);
        setPosition(x, y);
//        setColor(Color.BLACK);
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