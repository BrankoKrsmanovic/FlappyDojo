package com.dojo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dojo.game.FlappyDojo;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = FlappyDojo.WIDTH;
        config.height = FlappyDojo.HEIGHT;
        config.title = FlappyDojo.TITTLE;
        new LwjglApplication(new FlappyDojo(), config);
    }
}
