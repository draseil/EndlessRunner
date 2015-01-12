package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class CreditsState implements State {

    public static final String CREDITS_TEXT = "Hey guys!\nThis is my first game, hence why it sucks\n\nIt was made with Java and JSFML.\nThe graphics were made with\n  Paint.NET (Windows) and GIMP (Linux)\n\nA special thanks to the Ludum Dare community\n  for making this possible!\n\n\nPress <backspace> to go back to the menu";

    private Dialog mainText;

    // Gets called whenever the state goes from another one to this one
    public void enter() {
        mainText = new Dialog(CREDITS_TEXT, Main.WIDTH / 2, Main.HEIGHT / 2);
    }

    // Does the opposite of enter()
    public void exit() {}

    // Draws all objects to the RenderWindow
    public void draw(RenderWindow window) {
        window.clear();

        window.draw(mainText);

        window.display();
    }

    // Listens for key presses
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