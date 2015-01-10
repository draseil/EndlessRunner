package com.bashtheunixshell.runner;

import org.jsfml.graphics.RenderWindow;

public interface State {
    public abstract void enter();
    public abstract void exit();
    public abstract void draw(RenderWindow window);
    public abstract void update(RenderWindow window);
}