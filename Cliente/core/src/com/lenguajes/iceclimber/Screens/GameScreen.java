package com.lenguajes.iceclimber.Screens;

import com.badlogic.gdx.Game;
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
import com.lenguajes.iceclimber.Sockets.Connect;
import com.lenguajes.iceclimber.Sockets.jsonManager.jsonFactory;
import com.lenguajes.iceclimber.Sockets.jsonManager.jsonHandler;
import com.lenguajes.iceclimber.Sprites.Enemies.*;
import com.lenguajes.iceclimber.Sprites.Items.*;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Nana;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Popo;
import com.lenguajes.iceclimber.Tools.B2WorldCreator;
import com.lenguajes.iceclimber.Tools.WorldContactListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * La clase GameScreen que implemente Screen siendo esta una clase predeterminada de la libreria
 * libgdx se utiliza como la pantalla del juego principal, donde basicamente ocurre todo
 */
public class GameScreen implements Screen, Runnable{

    // variables finales
    public static final float SPEED = 200;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int PLAYER_PIXEL_WIDTH = 20;
    public static final int PLAYER_PIXEL_HEIGHT = 30;


    public int players;
    public boolean host;

    IceClimber game;
    private TextureAtlas atlas;
    private TextureAtlas fruitAtlas;
    public boolean bonus;

    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;


    //variables de box 2d
    private World world;
    private Box2DDebugRenderer b2dr;

    //variables del tile map
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //jugadores
    private Popo popoPlayer;
    private Nana nanaPlayer;


    //musica del juego
    private Music music;


    private Pterodactyl teroFinal;

    private LinkedBlockingQueue<EnemyDef> enemiesToSpawn;
    private Array<Enemy> enemies;
    public LinkedBlockingQueue<FruitDef> fruitsToSpawn;
    private Array<Fruit> fruits;

    Connect connect;

    private Thread t;

    /**
     * Constructor de la clase GameScreen. Aqui se inicializa lo necesario para que se inicie el juego
     * @param game Como todas las pantallas se necesita un parametro de la clase Game, en este caso es IceClimber
     */
    public GameScreen(IceClimber game, boolean bonus, boolean host, int players) {
        //crea un hilo para iniciar la conexion cliente-servidor
        t = new Thread(this);
        t.start();

        this.players = players;
        this.host = host;
        this.game = game;
        this.bonus = bonus;
        // Los atlas se utilizan para hacer las animaciones de los sprites
        atlas = new TextureAtlas("Popo_Nana_and_Enemies.pack");
        fruitAtlas = new TextureAtlas("Fruits.pack");
        // La camara y el gameport que se utilizan para mostrar el juego
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(IceClimber.GAMEWIDTH / IceClimber.PPM, IceClimber.GAMEHEIGHT / IceClimber.PPM, gamecam);
        hud = new Hud(game.batch);


        // inicializa el mapa
        maploader = new TmxMapLoader();
        map = maploader.load("iceclimber.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / IceClimber.PPM);

        // inicializa la musica
        if (!this.bonus) {
            music = IceClimber.manager.get("audio/music/ice_climber.mp3",Music.class);
            music.setLooping(true);
            music.play();
        } else {
            music = IceClimber.manager.get("audio/music/bonus_stage.mp3",Music.class);
            music.setLooping(true);
            music.play();
        }


        //coloca la posicion de la camara e inicializa algunas variables necesarias para el funcionamiento del juego
        gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() +0)/ 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        // Estos arrays contienen a todos loas actores que hay en escena de su respectiva clase
        enemies = new Array<Enemy>();
        fruits = new Array<Fruit>();
        // Estas dos variables funcionan como una lista de espera para los enemigos o frutas que se desean crear
        enemiesToSpawn = new LinkedBlockingQueue<>();
        fruitsToSpawn = new LinkedBlockingQueue<>();
        new B2WorldCreator(this);


        //Aqui se verifica si el juego va a tener un jugador o dos

        if (this.players == 1) {
            popoPlayer = new Popo(this);
        } else {
            popoPlayer = new Popo(this);
            nanaPlayer = new Nana(this);
        }



        world.setContactListener(new WorldContactListener());



        teroFinal = new Pterodactyl(this, 760, true);


    }

    /**
     * Esta funcion permite crear enemigos en el GameScreen.
     * @param enemydef Este enemydef es la definicion de un enemigo que se desea crear en pantalla y se agrega a enemiesToSpawn.
     */
    public void spawnEnemy(EnemyDef enemydef) {
        enemiesToSpawn.add(enemydef);
    }

    /**
     * Esta funcion revisa si hay algo nuevo en enemiesToSpawn y si encuentra que no esta vacia revisa a que tipo de
     * enemigo pertenece y lo ingresa al array de enemies siendo de su clase correspondiente.
     */
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

    /**
     * Esta funcion permite crear frutas en el GameScreen.
     * @param fruitdef Este fruitdef es la definicion de una fruta que se desea crear en pantalla y se agrega a fruitsToSpawn.
     */
    public void spawnFruit(FruitDef fruitdef) {
        fruitsToSpawn.add(fruitdef);
    }


    /**
     * Esta funcion revisa si hay algo nuevo en fruitsToSpawn y si encuentra que no esta vacia revisa a que tipo de
     * fruta pertenece y lo ingresa al array de frutas siendo de su clase correspondiente.
     */
    public void handleSpawningFruits() {
        if (!fruitsToSpawn.isEmpty()) {
            FruitDef fruitdef = fruitsToSpawn.poll();
            if (fruitdef.type == Onion.class) {
                fruits.add(new Onion(this, fruitdef.position.x, fruitdef.position.y));
            } else if (fruitdef.type == Pumpkin.class) {
                fruits.add(new Pumpkin(this, fruitdef.position.x, fruitdef.position.y));
            } else if (fruitdef.type == Carrot.class) {
                fruits.add(new Carrot(this, fruitdef.position.x, fruitdef.position.y));
            }
        }
    }

    /**
     * Retorna el atlas de los personajes
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Retorna el atlas de las frutas
     */
    public TextureAtlas getFruitAtlas() {
        return fruitAtlas;
    }

    @Override
    public void show() {

    }

    /**
     * La funcion update lo que hace es actualizar todos los actores presentes dentro de la pantalla, sea su posicion,
     * su sprite, agregar, eliminar, etc
     * @param dt El deltatime permite mover los objetos en pantalla no por frames de forma que si fuera por frames
     *           funcionaria diferente dependiendo del hardware.
     */
    public void update(float dt) {

        handleInput(dt);
        handleSpawningFruits();
        handleSpawningEnemies();
        world.step(1 / 60f, 6, 2);

        /**
        jsonHandler jsonChecker = new jsonHandler();
        jsonFactory firstmsg = new jsonFactory(0);
        jsonChecker.jsonWriter(firstmsg);
        connect.out.println(jsonChecker.jsonWriter(firstmsg));
        */

        if (this.players == 1) {
            popoPlayer.update(dt);
            if (Hud.getPopoLives() < 1) {
                game.setScreen(new GameOverScreen(game, hud));
            }
            if (teroFinal.destroyed) {
                game.setScreen(new GameOverScreen(game, hud));
            }
        } else {
            if (Hud.getPopoLives() < 1) {
                game.setScreen(new GameOverScreen(game, hud));
            }
            if (Hud.getNanaLives() < 1) {
                game.setScreen(new GameOverScreen(game, hud));
            }
            if (teroFinal.destroyed) {
                game.setScreen(new GameOverScreen(game, hud));
            }
            popoPlayer.update(dt);
            nanaPlayer.update(dt);
        }

        // Si la posicion de popo aumenta arriba de 500 cambia de posicion la camara
        if (this.players == 2) {
            if ((popoPlayer.b2body.getPosition().y * 100 > 700 | nanaPlayer.b2body.getPosition().y * 100 > 700) && !bonus) {
                music.dispose();
                game.setScreen(new GameScreen(game, true, this.host, this.players));
            }
            if (popoPlayer.b2body.getPosition().y * 100 > 450 && nanaPlayer.b2body.getPosition().y * 100 > 450) {
                gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() + 9) / 2, 0);
            } else {
                gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() + 0) / 2, 0);
            }
        } else {
            if (popoPlayer.b2body.getPosition().y * 100 > 450) {
                gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() + 9) / 2, 0);
            } else {
                gamecam.position.set(gameport.getWorldWidth() / 2, (gameport.getWorldHeight() + 0) / 2, 0);
            }
            if (popoPlayer.b2body.getPosition().y * 100 > 700 && !bonus) {
                music.dispose();
                game.setScreen(new GameScreen(game, true, this.host, this.players));
            }
        }



        // Actualiza cada enemigo en la pantalla
        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }
        // Actualiza cada fruta en la pantalla
        for (Fruit fruit : fruits) {
            fruit.update(dt);
        }

        teroFinal.update(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    /**
     * Aqui se revisa el input ingresado por el usuario, usualmente utilizado para mover a popo o nana
     * @param dt El deltatime permite mover los objetos en pantalla no por frames de forma que si fuera por frames
     *           funcionaria diferente dependiendo del hardware.
     */
    private void handleInput(float dt) {
        if (this.host) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W) && popoPlayer.currentState != Popo.State.JUMPING && popoPlayer.currentState != Popo.State.FALLING) {
                popoPlayer.b2body.applyLinearImpulse(new Vector2(0, 4.5f), popoPlayer.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && popoPlayer.b2body.getLinearVelocity().x <= 2) {

                popoPlayer.b2body.applyLinearImpulse(new Vector2(0.1f, 0), popoPlayer.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) && popoPlayer.b2body.getLinearVelocity().x >= -2) {
                popoPlayer.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), popoPlayer.b2body.getWorldCenter(), true);
            }

            if (this.players == 2) {
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
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            game.setScreen(new GameOverScreen(game, hud));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 700), Yeti.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 700), Bear.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 700), Seal.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 680), Pterodactyl.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            this.spawnEnemy(new EnemyDef(new Vector2(10, 640), Bird.class, true));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            this.spawnFruit(new FruitDef(new Vector2(50 / IceClimber.PPM, 100 / IceClimber.PPM), Onion.class));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            this.spawnFruit(new FruitDef(new Vector2(50 / IceClimber.PPM, 100 / IceClimber.PPM), Carrot.class));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            this.spawnFruit(new FruitDef(new Vector2(50 / IceClimber.PPM, 100 / IceClimber.PPM), Pumpkin.class));
        }



    }

    @Override
    /**
     * Este metodo dibuja lo que tenga que dibujar dentro de la pantalla
     */
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        // dibuja los jugadores
        if (this.players == 1) {
            popoPlayer.draw(game.batch);
        } else {
            nanaPlayer.draw(game.batch);
            popoPlayer.draw(game.batch);
        }

        teroFinal.draw(game.batch);
        // dibuja las frutas
        for (Fruit fruit : fruits) {
            fruit.draw(game.batch);
        }
        // dibuja los enemigos
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

    @Override

    /**
    public void run() {
        int msg = 0;
        jsonHandler jsonChecker = new jsonHandler();
        connect = new Connect();
        Connect.setUp();
        int a = 0;
        while (true) {
            try {
                jsonChecker.jsonReader(connect.in.readLine(), this);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    */

     public void run() {
     int msg = 0;
     jsonHandler jsonChecker = new jsonHandler();
     connect = new Connect();
     Connect.setUp();
     try {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

     while ((userInput = stdIn.readLine()) != null) {
        connect.out.println(userInput);

     if (msg != 0) {
        jsonChecker.jsonReader(connect.in.readLine(), this);
     }
     if(msg==0) {
        System.out.println("SERVER: " + connect.in.readLine());
     }
        msg = 1;
        }
     }
     catch (IOException e) {
        e.printStackTrace();
        }
     }

}
