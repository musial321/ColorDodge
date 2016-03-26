package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ColorDodge;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ColorDodge.WIDTH;
		config.height = ColorDodge.HEIGHT;
		config.title = ColorDodge.TITLE;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(new ColorDodge(), config);
	}
}
