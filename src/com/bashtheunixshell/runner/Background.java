package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

public class Background extends Sprite {

    private Texture texture;

    public Background(String filename) {
        texture = new Texture();

        try {
            texture.loadFromFile(Paths.get("res/" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);
    }

    public void animate(RenderWindow window, float speed, int windowWidth) {
        move(speed, 0);

        if (getPosition().x <= -windowWidth) {
            setPosition(windowWidth, 0);
        } else if (getPosition().x <= -windowWidth) {
            setPosition(windowWidth, 0);
        }

        window.draw(this);
    }

    public void draw(RenderWindow window) {
        window.draw(this);
    }
}