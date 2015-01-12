package com.bashtheunixshell.runner;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;

public class Dialog extends Text {

    private Font font;

    // Sets a font, size, text and position
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

    // Sets a font, size, text, position and colour
    public Dialog(String text, int x, int y, Color color) {
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
        setColor(color);
    }

    // Returns its geometry

    public int width() {
        return (int) getGlobalBounds().width;
    }

    public int height() {
        return (int) getGlobalBounds().height;
    }
}