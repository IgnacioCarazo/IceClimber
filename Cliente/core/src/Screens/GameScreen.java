package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lenguajes.iceclimber.IceClimber;
import Characters.Player;

public class GameScreen implements Screen {

    public static final float SPEED = 200;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int PLAYER_PIXEL_WIDTH = 20;
    public static final int PLAYER_PIXEL_HEIGHT = 30;
    public float DELTATIME;

    private TextureAtlas atlas;



    float x;
    float y;




    IceClimber game;

    public GameScreen(IceClimber game) {
        this.game = game;
        atlas = new TextureAtlas("Popo_Nana_and_Enemies.pack");

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        DELTATIME = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += SPEED * DELTATIME;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= SPEED * DELTATIME;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += SPEED * DELTATIME;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= SPEED * DELTATIME;
        }



        game.batch.begin();
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

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
        game.batch.dispose();

    }
}
