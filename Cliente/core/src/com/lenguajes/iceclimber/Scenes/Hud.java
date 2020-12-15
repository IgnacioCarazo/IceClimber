package com.lenguajes.iceclimber.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.MainMenuScreen;

import java.awt.*;


/**
 * La clase Hud  se utiliza para dibujar en pantalla lo que coresponde a las vidas y el score de los jugadores
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;


    private static int popoScore;
    private static int nanaScore;
    private static int popoLives;
    private static int nanaLives;

    private static Label poposcoreLabel;
    private static Label nanascoreLabel;
    private static Label popoLivesLabel;
    private static Label nanaLivesLabel;

    private Label livesLabel;
    private Label popoLabel;
    private Label nanaLabel;


    /**
     * Constructor de la clase
     * @param sb es el sprite batch en el que se dibujan los labels de score,lives y sus respectivos valores
     */
    public Hud(SpriteBatch sb) {

        popoScore = 0;
        nanaScore = 0;
        popoLives = 5;
        nanaLives = 5;

        viewport = new FitViewport(IceClimber.GAMEWIDTH,IceClimber.GAMEHEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        poposcoreLabel = new Label(String.format("%06d", popoScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanascoreLabel = new Label(String.format("%06d", nanaScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        popoLivesLabel = new Label(String.format("%01d", popoLives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanaLivesLabel = new Label(String.format("%01d", nanaLives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        popoLabel = new Label("POPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanaLabel = new Label("NANA", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));



        if (MainMenuScreen.players == 1) {
            table.add(popoLabel).expandX().padTop(10);
            table.add(livesLabel).expandX().padTop(10);
            table.row();
            table.add(poposcoreLabel).expandX();
            table.add(popoLivesLabel).expandX();
        } else {
            table.add(popoLabel).expandX().padTop(10);
            table.add(livesLabel).expandX().padTop(10);
            table.add(nanaLabel).expandX().padTop(10);
            table.add(livesLabel).expandX().padTop(10);
            table.row();
            table.add(poposcoreLabel).expandX();
            table.add(popoLivesLabel).expandX();
            table.add(nanascoreLabel).expandX();
            table.add(nanaLivesLabel).expandX();
        }



        stage.addActor(table);


    }

    public static void addScorePopo(int value) {
        popoScore += value;
        poposcoreLabel.setText(String.format("%06d", popoScore));
    }

    public static void addScoreNana(int value) {
        nanaScore += value;
        nanascoreLabel.setText(String.format("%06d", nanaScore));
    }

    public static void removeLivePopo(int value) {
        popoLives -= value;
        popoLivesLabel.setText(String.format("%01d", popoLives));
    }
    public static void removeLiveNana(int value) {
        popoLives -= value;
        nanaLivesLabel.setText(String.format("%01d", nanaLives));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
