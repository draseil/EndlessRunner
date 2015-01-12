package com.bashtheunixshell.runner;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Background extends Sprite {

    private Texture texture;

    // Gives it an image based on the parameters
    public Background(String filename, int x, int y) {
        texture = new Texture();

        try {
            texture.loadFromFile(Paths.get("res/" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);
        setPosition(x, y);
    }

    // Animates the background so that it scrolls
    public void animate(float speed, int windowWidth) {
        move(speed, 0);

        if (getPosition().x <= -windowWidth) {
            setPosition(windowWidth, 0);
        } else if (getPosition().x <= -windowWidth) {
            setPosition(windowWidth, 0);
        }
    }
}