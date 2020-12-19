package com.lenguajes.iceclimber;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.lenguajes.iceclimber.Screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IceClimber extends Game {

	// Dimensiones de la ventana del menu
	public static final int MENUWIDTH = 1000;
	public static final int MENUHEIGHT = 1400;

	// Dimensiones de la ventana del juego
	public static final int GAMEWIDTH = 320;
	public static final int GAMEHEIGHT = 500;

	//PixelsPerMeter
	public static final float PPM = 100;

	//Box2D collision bits
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short PLAYER_HEAD_BIT = 512;
	public static final short BRICK_BIT = 4;
	public static final short DESTROYED_BIT = 16;
	public static final short ENEMY_BIT = 62;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short FRUIT_BIT = 32;

	public static AssetManager manager;

	public SpriteBatch batch;

	@Override
	public void create () {

		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/game_start.mp3", Music.class);
		manager.load("audio/music/ice_climber.mp3", Music.class);
		manager.load("audio/music/bonus_stage.mp3", Music.class);
		manager.load("audio/sounds/breakblock.wav", Sound.class);
		manager.finishLoading();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();

	}
}