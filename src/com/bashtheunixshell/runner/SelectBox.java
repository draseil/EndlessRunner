package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

public class SelectBox extends Sprite {

    private Texture texture;

    public SelectBox(Vector2f position) {
        texture = new Texture();

        try {
            texture.loadFromFile(Paths.get("res/selectbox.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTexture(texture);
        setOrigin(getLocalBounds().width / 2, getLocalBounds().height / 2);
        setPosition(position);
    }

    public void move(Dialog text1, Dialog text2, Dialog text3) {
        if (Keyboard.isKeyPressed(Key.DOWN)) {
            if (getPosition() == text1.getPosition()) {
                setPosition(text2.getPosition());
            } else if (getPosition() == text2.getPosition()) {
                setPosition(text3.getPosition());
            }
        }
        if (Keyboard.isKeyPressed(Key.UP)) {
            if (getPosition() == text2.getPosition()) {
                setPosition(text1.getPosition());
            } else if (getPosition() == text3.getPosition()) {
                setPosition(text2.getPosition());
            }
        }
        if (Keyboard.isKeyPressed(Key.RETURN)) {
            if (getPosition() == text1.getPosition()) {}
            else if (getPosition() == text2.getPosition()) {} 
            else if (getPosition() == text3.getPosition()) {
                System.exit(0);
            }
        }
    }

    public void draw(RenderWindow window) {
        window.draw(this);
    }
}