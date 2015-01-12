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
    private boolean keyIsPressed = false;;

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
        if (Keyboard.isKeyPressed(Key.DOWN) || Keyboard.isKeyPressed(Key.UP) || Keyboard.isKeyPressed(Key.RETURN)) {
            if (Keyboard.isKeyPressed(Key.DOWN)) {
                if (getPosition() == text1.getPosition()) {
                    if (!keyIsPressed) {
                        setPosition(text2.getPosition());
                        keyIsPressed = true;
                    }
                } else if (getPosition() == text2.getPosition()) {
                    if (!keyIsPressed) {
                        setPosition(text3.getPosition());
                        keyIsPressed = true;
                    }
                }
            }
            if (Keyboard.isKeyPressed(Key.UP)) {
                if (getPosition() == text2.getPosition()) {
                    if (!keyIsPressed) {
                        setPosition(text1.getPosition());
                        keyIsPressed = true;
                    }
                } else if (getPosition() == text3.getPosition()) {
                    if (!keyIsPressed) {
                        setPosition(text2.getPosition());
                        keyIsPressed = true;
                    }
                }
            }
            if (Keyboard.isKeyPressed(Key.RETURN)) {
                if (getPosition() == text1.getPosition()) {
                    Main.mainGame.startGame();
                }
                else if (getPosition() == text2.getPosition()) {
                    Main.mainGame.showCredits();
                }
                else if (getPosition() == text3.getPosition()) {
                    System.exit(0);
                }
            }
        } else {
            keyIsPressed = false;
        }
    }

    public void draw(RenderWindow window) {
        window.draw(this);
    }
}