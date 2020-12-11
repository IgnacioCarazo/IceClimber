package com.lenguajes.iceclimber;



import com.lenguajes.iceclimber.Screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IceClimber extends Game {

	// Dimensiones de la ventana
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1400;
	public static final float PPM = 100;



	public SpriteBatch batch;

	@Override
	public void create () {

		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}


}