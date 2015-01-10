package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/*  TODO LIST:
 * >>>FIX STATE MACHINE<<<
 * 0. Fix the select box issue
 * 1. Change the lost boolean and stuff.
 *    Basically just make it so that when you go back to the main screen, you can start a new game again
 * 2. Add a "Try again" button when you lose
 * 3. Make the points system work
 */

public class Main {

    public static final int WIDTH = 800, HEIGHT = 360;
    public static final String TITLE = "Endless Runner";

    private VideoMode mode;
    private RenderWindow window;
    private MenuState menu;
    private GameState game;
    private String currentState;

    public Main() {
        createObjects();

        while (window.isOpen()) {
            if (currentState.equals("menu")) {
                menu.draw(window);
                menu.update(window);
            } else if (currentState.equals("game")) {
                menu.exit();
                game.draw(window);
                game.update(window);
            }
        }
    }

    public void createObjects() {
        mode            = new VideoMode(WIDTH, HEIGHT);
        window          = new RenderWindow();
        menu = new MenuState();
        game = new GameState();
        menu.enter();
        game.enter();
        currentState = "menu";

        window.create(mode, TITLE);
        window.setFramerateLimit(60);
    }

    public void setState(String newState) {
        currentState = newState;
    }

    public static void main(String[] args) {
        new Main();
    }
}