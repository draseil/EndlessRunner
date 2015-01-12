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

    // Sets a size and position
    public Ground(int x, int y) {
        texture = new Texture();
        size = new Vector2f(800, 32);

        setSize(size);
        setPosition(x, y);
    }

    // Returns objects geometry

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