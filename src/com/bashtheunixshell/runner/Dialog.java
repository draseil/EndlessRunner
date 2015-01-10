package com.bashtheunixshell.runner;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;

import java.io.IOException;
import java.nio.file.Paths;

public class Dialog extends Text {

    private Font font;

    public Dialog(String text, int x, int y) {
        font = new Font();

        try {
            font.loadFromFile(Paths.get("res/pixelmix.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setFont(font);
        setCharacterSize(24);
        setString(text);
        setOrigin(getLocalBounds().width / 2, getLocalBounds().height / 2);
        setPosition(x, y);
    }

    public void draw(RenderWindow window) {
        window.draw(this);
    }
}