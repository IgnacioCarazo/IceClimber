package com.lenguajes.iceclimber.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Sprites.Nana;
import com.lenguajes.iceclimber.Sprites.Popo;
import com.lenguajes.iceclimber.Tools.B2WorldCreator;

public class GameScreen implements Screen {

    public static final float SPEED = 200;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int PLAYER_PIXEL_WIDTH = 20;
    public static final int PLAYER_PIXEL_HEIGHT = 30;
    public float DELTATIME;
    IceClimber game;
    private TextureAtlas atlas;

    float x;
    float y;

    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;

    //box 2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //players
    private Popo popoPlayer;
    private Nana nanaPlayer;

    // constructor de GameScreen
    public GameScreen(IceClimber game) {
        this.game = game;
        atlas = new TextureAtlas("Popo_Nana_and_Enemies.pack");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(IceClimber.WIDTH / IceClimber.PPM, IceClimber.HEIGHT / IceClimber.PPM, gamecam);
        hud = new Hud(game.batch);

        // inicializa el mapa
        maploader = new TmxMapLoader();
        map = maploader.load("iceclimber.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / IceClimber.PPM);

        //coloca la posicion de la camara
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        if (MainMenuScreen.players == 1) {
            popoPlayer = new Popo(world);
        }



    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        gamecam.update();
        renderer.setView(gamecam);
    }

    private void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(0,4f), popoPlayer.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && popoPlayer.b2body.getLinearVelocity().x <= 2) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(0.3f,0),popoPlayer.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && popoPlayer.b2body.getLinearVelocity().x >= -2) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(-0.3f,0),popoPlayer.b2body.getWorldCenter(), true);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        game.batch.dispose();

    }
}
