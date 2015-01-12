package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class CreditsState implements State {

    public static final String CREDITS_TEXT = "Hey guys!\nThis is my first game, hence why it sucks\n\nIt was made with Java and JSFML.\nThe graphics were made with\n  Paint.NET (Windows) and GIMP (Linux)\n\nA special thanks to the Ludum Dare community\n  for making this possible!\n\n\nPress <backspace> to go back to the menu";

    private Dialog mainText;

    public void enter() {
        mainText = new Dialog(CREDITS_TEXT, Main.WIDTH / 2, Main.HEIGHT / 2);
    }

    public void exit() {}

    public void draw(RenderWindow window) {
        window.clear();

        mainText.draw(window);

        window.display();
    }

    public void update(RenderWindow window) {
        for (Event event : window.pollEvents()) {
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE)) {
                window.close();
                System.exit(0);
            }
        }
        if (Keyboard.isKeyPressed(Key.BACKSPACE)) {
            Main.mainGame.startMenu();
        }
    }
}