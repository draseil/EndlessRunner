package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class MenuState implements State {

    private Dialog newGame, credits, exit;
    private SelectBox box;

    // Gets called whenever the state switches to this one
    public void enter() {
        newGame = new Dialog("New Game", Main.WIDTH / 2, Main.HEIGHT / 2 - 48);
        credits = new Dialog("Credits", Main.WIDTH / 2, Main.HEIGHT / 2);
        exit = new Dialog("Exit", Main.WIDTH / 2, Main.HEIGHT / 2 + 48);
        box = new SelectBox(newGame.getPosition());
    }

    // Gets called whenever this state switches to another one
    public void exit() {
        while (newGame.getPosition().x < Main.HEIGHT && credits.getPosition().x < Main.HEIGHT && exit.getPosition().x < Main.HEIGHT) {
            newGame.move(0, 8);
            credits.move(0, 8);
            exit.move(0, 8);
        }
    }

    // Draws objects to the RenderWindow
    public void draw(RenderWindow window) {
        window.clear();

        window.draw(newGame);
        window.draw(credits);
        window.draw(exit);
        window.draw(box);

        window.display();
    }

    // Checks user input and such
    public void update(RenderWindow window) {
        for (Event event : window.pollEvents()) {
            if (event.type == Event.Type.CLOSED || Keyboard.isKeyPressed(Key.ESCAPE)) {
                window.close();
                System.exit(0);
            }
        }

        box.move(newGame, credits, exit);
    }
}