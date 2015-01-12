package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

/*  TODO LIST:
 * 2. Add a "Try again" button when you lose
 */

public class Main {

    public static final int WIDTH = 800, HEIGHT = 360;
    public static final String TITLE = "Endless Runner";

    public static Main mainGame;

    private State currentState;
    private VideoMode mode;
    private RenderWindow window;
    private int level;

    public Main() {
        createObjects();

        while (window.isOpen()) {
            currentState.update(window);
            currentState.draw(window);
        }
    }

    public void createObjects() {
        mainGame = this;
        mode            = new VideoMode(WIDTH, HEIGHT);
        window          = new RenderWindow();
        currentState = new MenuState();
        currentState.enter();

        window.create(mode, TITLE, Window.RESIZE);
        window.setFramerateLimit(60);
    }

    public void startGame() {
        currentState.exit();
        currentState = new GameState();
        currentState.enter();
    }

    public void startMenu() {
        currentState.exit();
        currentState = new MenuState();
        currentState.enter();
    }

    public void showCredits() {
        currentState.exit();
        currentState = new CreditsState();
        currentState.enter();
    }

    public static void main(String[] args) {
        new Main();
    }
}