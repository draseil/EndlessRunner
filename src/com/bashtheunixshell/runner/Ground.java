package com.bashtheunixshell.runner;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Ground extends RectangleShape {
    private Vector2f size;

    // Sets a size and position
    public Ground(int x, int y) {
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