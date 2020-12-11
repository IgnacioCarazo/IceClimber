package com.lenguajes.iceclimber.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lenguajes.iceclimber.IceClimber;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.height = IceClimber.HEIGHT;
		config.width = IceClimber.WIDTH;
		config.resizable = false;
		config.forceExit = false;
		new LwjglApplication(new IceClimber(), config);
	}
}
