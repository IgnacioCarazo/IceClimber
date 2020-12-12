package com.lenguajes.iceclimber.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Sprites.Enemies.*;
import com.lenguajes.iceclimber.Sprites.Items.Onion;
import com.lenguajes.iceclimber.Sprites.Items.Fruit;
import com.lenguajes.iceclimber.Sprites.Items.FruitDef;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Nana;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Popo;
import com.lenguajes.iceclimber.Tools.B2WorldCreator;
import com.lenguajes.iceclimber.Tools.WorldContactListener;
import sun.rmi.rmic.Main;

import java.util.concurrent.LinkedBlockingQueue;

public class GameScreen implements Screen {

    public static final float SPEED = 200;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int PLAYER_PIXEL_WIDTH = 20;
    public static final int PLAYER_PIXEL_HEIGHT = 30;
    public float DELTATIME;
    IceClimber game;
    private TextureAtlas atlas;
    private TextureAtlas fruitAtlas;



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

    private Music music;


    private Yeti yeti;

    private LinkedBlockingQueue<EnemyDef> enemiesToSpawn;
    private Array<Enemy> enemies;
    public LinkedBlockingQueue<FruitDef> fruitsToSpawn;
    private Array<Fruit> fruits;

    // constructor de GameScreen
    public GameScreen(IceClimber game) {
        this.game = game;
        atlas = new TextureAtlas("Popo_Nana_and_Enemies.pack");
        fruitAtlas = new TextureAtlas("Fruits.pack");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(IceClimber.GAMEWIDTH / IceClimber.PPM, IceClimber.GAMEHEIGHT / IceClimber.PPM, gamecam);
        hud = new Hud(game.batch);

        // inicializa el mapa
        maploader = new TmxMapLoader();
        map = maploader.load("iceclimber.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / IceClimber.PPM);

        music = IceClimber.manager.get("audio/music/ice_climber.mp3",Music.class);
        music.setLooping(true);
        music.play();

        //coloca la posicion de la camara
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        System.out.println(gameport.getWorldHeight() / 2);
        System.out.println(gameport.getWorldWidth() / 2);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        enemies = new Array<Enemy>();
        fruits = new Array<Fruit>();
        enemiesToSpawn = new LinkedBlockingQueue<>();
        fruitsToSpawn = new LinkedBlockingQueue<>();
        new B2WorldCreator(this);

        if (MainMenuScreen.players == 1) {
            popoPlayer = new Popo(this);
        } else {
            popoPlayer = new Popo(this);
            nanaPlayer = new Nana(this);
        }

        world.setContactListener(new WorldContactListener());
        float floor = 5;

        yeti = new Yeti(this, floor * 10, true);
    }

    public void spawnEnemy(EnemyDef enemydef) {
        enemiesToSpawn.add(enemydef);
    }

    public void handleSpawningEnemies() {
        if (!enemiesToSpawn.isEmpty()) {
            EnemyDef edef = enemiesToSpawn.poll();
            if (edef.type == Bird.class) {
                enemies.add(new Bird(this, edef.position.y, edef.facingLeft));
            } else if (edef.type == Bear.class) {
                enemies.add(new Bear(this, edef.position.y, edef.facingLeft));
            } else if (edef.type == Pterodactyl.class) {
                enemies.add(new Pterodactyl(this, edef.position.y, edef.facingLeft));
            } else if (edef.type == Seal.class) {
                enemies.add(new Seal(this, edef.position.y, edef.facingLeft));
            } else if (edef.type == Yeti.class) {
                enemies.add(new Yeti(this, edef.position.y, edef.facingLeft));
            }
        }
    }

    public void spawnFruit(FruitDef fruitdef) {
        fruitsToSpawn.add(fruitdef);
    }

    public void handleSpawningFruits() {
        if (!fruitsToSpawn.isEmpty()) {
            FruitDef fruitdef = fruitsToSpawn.poll();
            if (fruitdef.type == Onion.class) {
                fruits.add(new Onion(this, fruitdef.position.x, fruitdef.position.y));
            }
        }
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TextureAtlas getFruitAtlas() {
        return fruitAtlas;
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput(dt);
        handleSpawningFruits();
        handleSpawningEnemies();
        world.step(1 / 60f, 6, 2);

        if (MainMenuScreen.players == 1) {
            popoPlayer.update(dt);
        } else {
            popoPlayer.update(dt);
            nanaPlayer.update(dt);
        }


        if (popoPlayer.b2body.getPosition().y * 100 > 500) {
            gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() + 9) / 2, 0);
        }

        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }
        for (Fruit fruit : fruits) {
            fruit.update(dt);
        }

        yeti.update(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    private void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && popoPlayer.currentState != Popo.State.JUMPING && popoPlayer.currentState != Popo.State.FALLING) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(0, 4.5f), popoPlayer.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && popoPlayer.b2body.getLinearVelocity().x <= 2) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(0.1f, 0), popoPlayer.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && popoPlayer.b2body.getLinearVelocity().x >= -2) {
            popoPlayer.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), popoPlayer.b2body.getWorldCenter(), true);
        }

        if (MainMenuScreen.players == 2) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && nanaPlayer.currentState != Nana.State.JUMPING && nanaPlayer.currentState != Nana.State.FALLING) {
                nanaPlayer.b2body.applyLinearImpulse(new Vector2(0, 4.5f), nanaPlayer.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && nanaPlayer.b2body.getLinearVelocity().x <= 2) {
                nanaPlayer.b2body.applyLinearImpulse(new Vector2(0.1f, 0), nanaPlayer.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && nanaPlayer.b2body.getLinearVelocity().x >= -2) {
                nanaPlayer.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), nanaPlayer.b2body.getWorldCenter(), true);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 30), Bird.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 30), Yeti.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 30), Bear.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 30), Seal.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 30), Pterodactyl.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            this.spawnFruit(new FruitDef(new Vector2(50 / IceClimber.PPM, 100 / IceClimber.PPM), Onion.class));
        }



    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        if (MainMenuScreen.players == 1) {
            popoPlayer.draw(game.batch);
        } else {
            nanaPlayer.draw(game.batch);
            popoPlayer.draw(game.batch);
        }

        yeti.draw(game.batch);
        for (Fruit fruit : fruits) {
            fruit.draw(game.batch);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(game.batch);
        }
        game.batch.end();
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }


    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
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
