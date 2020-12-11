package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.lenguajes.iceclimber.IceClimber;

public class MainMenuScreen implements Screen {

    private static final int PLAY_BUTTON_X = 375;
    private static final int EXIT_BUTTON_X = 375;
    private static final int ONE_PLAYER_BUTTON_X = 200;
    private static final int TWO_PLAYER_BUTTON_X = 180;
    private static final int PLAY_BUTTON_Y = 800;
    private static final int EXIT_BUTTON_Y = 200;
    private static final int ONE_PLAYER_BUTTON_Y = 600;
    private static final int TWO_PLAYER_BUTTON_Y = 400;
    public int players = 0;

    IceClimber game;
    Texture logo;
    Texture playButton;
    Texture exitButton;
    Texture onePlayerButton;
    Texture twoPlayersButton;

    public MainMenuScreen(IceClimber game) {
        this.game = game;
        logo = new Texture("logo.png");
        playButton = new Texture("play.png");
        exitButton = new Texture("exit.png");
        onePlayerButton = new Texture("1_player.png");
        twoPlayersButton =  new Texture("2_players.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(180/255f, 230/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(logo,150, 1100);
        // play button
        if (Gdx.input.getX() > PLAY_BUTTON_X && Gdx.input.getX() < 625
                && IceClimber.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y && IceClimber.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + 100) {
                game.batch.draw(playButton, PLAY_BUTTON_X,PLAY_BUTTON_Y + 10);
            if (Gdx.input.isTouched() && players != 0) {
                game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButton, PLAY_BUTTON_X,PLAY_BUTTON_Y);
        }
        // 1 player button
        if (Gdx.input.getX() > ONE_PLAYER_BUTTON_X && Gdx.input.getX() < 800
                && IceClimber.HEIGHT - Gdx.input.getY() > ONE_PLAYER_BUTTON_Y && IceClimber.HEIGHT - Gdx.input.getY() < ONE_PLAYER_BUTTON_Y + 100) {
            game.batch.draw(onePlayerButton, ONE_PLAYER_BUTTON_X,ONE_PLAYER_BUTTON_Y + 10);
            if (Gdx.input.isTouched()) {
                players = 1;
            }
        } else {
            game.batch.draw(onePlayerButton, ONE_PLAYER_BUTTON_X,ONE_PLAYER_BUTTON_Y);
        }
        // 2 player button
        if (Gdx.input.getX() > TWO_PLAYER_BUTTON_X && Gdx.input.getX() < 800
                && IceClimber.HEIGHT - Gdx.input.getY() > TWO_PLAYER_BUTTON_Y && IceClimber.HEIGHT - Gdx.input.getY() < TWO_PLAYER_BUTTON_Y + 100) {
            game.batch.draw(twoPlayersButton, TWO_PLAYER_BUTTON_X,TWO_PLAYER_BUTTON_Y + 10);
            if (Gdx.input.isTouched()) {
                players = 2;
            }
        } else {
            game.batch.draw(twoPlayersButton, TWO_PLAYER_BUTTON_X,TWO_PLAYER_BUTTON_Y);
        }
        // exit button
        if (Gdx.input.getX() > EXIT_BUTTON_X && Gdx.input.getX() < 625
                && IceClimber.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y && IceClimber.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + 100) {
            game.batch.draw(exitButton, EXIT_BUTTON_X,EXIT_BUTTON_Y + 10);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButton, EXIT_BUTTON_X,EXIT_BUTTON_Y);
        }

        if (players == 1){
            game.batch.draw(onePlayerButton, 0, 0, 300,50);
        }
        if (players == 2){
            game.batch.draw(twoPlayersButton, 0, 0, 300,50);
        }



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

    }
}
