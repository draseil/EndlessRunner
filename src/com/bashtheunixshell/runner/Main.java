package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

/*  TODO LIST:
 * 2. Add a "Try again" button when you lose
 */

public class Main {

    // Variables for the window
    public static final int WIDTH = 800, HEIGHT = 360;
    public static final String TITLE = "Endless Runner";

    // Basically created just for state switching
    // I don't know if it's the right way to do it
    public static Main mainGame;

    private State currentState;
    private VideoMode mode;
    private RenderWindow window;

    public Main() {
        createObjects();

        // Game loop
        while (window.isOpen()) {
            currentState.update(window);
            currentState.draw(window);
        }
    }

    // Initializes every object
    public void createObjects() {
        mainGame = this;
        mode            = new VideoMode(WIDTH, HEIGHT);
        window          = new RenderWindow();
        currentState = new MenuState();
        currentState.enter();

        window.create(mode, TITLE, Window.RESIZE);
        window.setFramerateLimit(60);
    }

    // These next few methods allow the changing of states
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