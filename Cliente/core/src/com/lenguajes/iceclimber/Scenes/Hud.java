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
import com.lenguajes.iceclimber.Sprites.Enemies.*;
import com.lenguajes.iceclimber.Sprites.Items.Carrot;
import com.lenguajes.iceclimber.Sprites.Items.Onion;
import com.lenguajes.iceclimber.Sprites.Items.Pumpkin;
import com.lenguajes.iceclimber.Sprites.TileObjects.Brick;

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
    public static int pumpkinScore;
    public static int onionScore;
    public static int carrotScore;
    public static int birdScore;
    public static int bearScore;
    public static int yetiScore;
    public static int sealScore;
    public static int teroScore;
    public static int pumpkinScore2;
    public static int onionScore2;
    public static int carrotScore2;
    public static int birdScore2;
    public static int bearScore2;
    public static int yetiScore2;
    public static int sealScore2;
    public static int teroScore2;
    public static int brickScore;
    public static int brickScore2;



    private static Label totalScoreLable1;
    private static Label totalScoreLable2;
    private static Label poposcoreLabel;
    private static Label nanascoreLabel;
    private static Label popoLivesLabel;
    private static Label nanaLivesLabel;
    private static Label pumpkinscoreLabel;
    private static Label onionscoreLabel;
    private static Label carrotscoreLabel;
    private static Label birdscoreLabel;
    private static Label bearscoreLabel;
    private static Label yetiscoreLabel;
    private static Label sealscoreLabel;
    private static Label teroscoreLabel;
    private static Label pumpkinscoreLabel2;
    private static Label onionscoreLabel2;
    private static Label carrotscoreLabel2;
    private static Label birdscoreLabel2;
    private static Label bearscoreLabel2;
    private static Label yetiscoreLabel2;
    private static Label sealscoreLabel2;
    private static Label teroscoreLabel2;
    private static Label brickScoreLabel;
    private static Label brickScoreLabel2;


    private Label total;
    private Label total2;
    private Label pumpkinLabel;
    private Label onionLabel;
    private Label carrotLabel;
    private Label birdLabel;
    private Label bearLabel;
    private Label yetiLabel;
    private Label sealLabel;
    private Label teroLabel;
    private Label pumpkinLabel2;
    private Label onionLabel2;
    private Label carrotLabel2;
    private Label birdLabel2;
    private Label bearLabel2;
    private Label yetiLabel2;
    private Label sealLabel2;
    private Label teroLabel2;
    private Label scoreLabel;
    private Label scoreLabel2;
    private Label livesLabel;
    private Label livesLabel2;
    private Label popoLabel;
    private Label nanaLabel;
    private Label spaceLabel;
    private Label brick;
    private Label brick2;

    public boolean gameover;


    /**
     * Constructor de la clase
     *
     * @param sb es el sprite batch en el que se dibujan los labels de score,lives y sus respectivos valores
     */
    public Hud(SpriteBatch sb) {
        this.gameover = false;
        popoScore = 0;
        nanaScore = 0;
        popoLives = 5;
        nanaLives = 5;
        pumpkinScore = 0;
        onionScore = 0;
        carrotScore = 0;
        birdScore = 0;
        bearScore = 0;
        yetiScore = 0;
        sealScore = 0;
        teroScore = 0;
        pumpkinScore2 = 0;
        onionScore2 = 0;
        carrotScore2 = 0;
        birdScore2 = 0;
        bearScore2 = 0;
        yetiScore2 = 0;
        sealScore2 = 0;
        teroScore2 = 0;
        brickScore = 0;
        brickScore2 = 0;



        viewport = new FitViewport(IceClimber.GAMEWIDTH, IceClimber.GAMEHEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        poposcoreLabel = new Label(String.format("%06d", popoScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanascoreLabel = new Label(String.format("%06d", nanaScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        popoLivesLabel = new Label(String.format("%01d", popoLives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanaLivesLabel = new Label(String.format("%01d", nanaLives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pumpkinscoreLabel = new Label(String.format("%05d", pumpkinScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        onionscoreLabel = new Label(String.format("%05d", onionScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        carrotscoreLabel = new Label(String.format("%05d", carrotScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdscoreLabel = new Label(String.format("%05d", birdScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bearscoreLabel = new Label(String.format("%05d", bearScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        sealscoreLabel = new Label(String.format("%05d", sealScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        teroscoreLabel = new Label(String.format("%05d", teroScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdscoreLabel = new Label(String.format("%05d", birdScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yetiscoreLabel = new Label(String.format("%05d", yetiScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pumpkinscoreLabel2 = new Label(String.format("%05d", pumpkinScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        onionscoreLabel2 = new Label(String.format("%05d", onionScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        carrotscoreLabel2 = new Label(String.format("%05d", carrotScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdscoreLabel2 = new Label(String.format("%05d", birdScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bearscoreLabel2 = new Label(String.format("%05d", bearScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        sealscoreLabel2 = new Label(String.format("%05d", sealScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        teroscoreLabel2 = new Label(String.format("%05d", teroScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdscoreLabel2 = new Label(String.format("%05d", birdScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yetiscoreLabel2 = new Label(String.format("%05d", yetiScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        totalScoreLable1 = new Label(String.format("%06d", popoScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        totalScoreLable2 = new Label(String.format("%06d", nanaScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        brickScoreLabel = new Label(String.format("%06d", brickScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        brickScoreLabel2 = new Label(String.format("%06d", brickScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        popoLabel = new Label("POPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanaLabel = new Label("NANA", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel2 = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2 = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        spaceLabel = new Label("     ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pumpkinLabel = new Label("PUMPKINS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        carrotLabel = new Label("CARROTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        onionLabel = new Label("ONIONS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdLabel = new Label("BIRDS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bearLabel = new Label("BEARS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yetiLabel = new Label("YETIS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        sealLabel = new Label("SEALS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        teroLabel = new Label("TEROS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pumpkinLabel2 = new Label("PUMPKINS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        carrotLabel2 = new Label("CARROTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        onionLabel2 = new Label("ONIONS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        birdLabel2 = new Label("BIRDS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bearLabel2 = new Label("BEARS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yetiLabel2 = new Label("YETIS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        sealLabel2 = new Label("SEALS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        teroLabel2 = new Label("TEROS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        total = new Label("TOTAL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        total2 = new Label("TOTAL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        brick = new Label("BRICKS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        brick2 = new Label("BRICKS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));




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
            table.add(livesLabel2).expandX().padTop(10);
            table.row();
            table.add(poposcoreLabel).expandX();
            table.add(popoLivesLabel).expandX();
            table.add(nanascoreLabel).expandX();
            table.add(nanaLivesLabel).expandX();

        }
        stage.addActor(table);

    }


    public void gameOverHud() {

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        if (MainMenuScreen.players == 1) {
            table.add(popoLabel).expandX().padTop(10);

            table.row();
            table.add(brick).expandX().padTop(10);
            table.add(brickScoreLabel).expandX().padTop(10);

            table.row();
            table.add(livesLabel).expandX().padTop(10);
            table.add(popoLivesLabel).expandX().padTop(10);

            table.row();
            table.add(pumpkinLabel).expandX().padTop(10);
            table.add(pumpkinscoreLabel).expandX().padTop(10);

            table.row();
            table.add(onionLabel).expandX().padTop(10);
            table.add(onionscoreLabel).expandX().padTop(10);

            table.row();
            table.add(carrotLabel).expandX().padTop(10);
            table.add(carrotscoreLabel).expandX().padTop(10);

            table.row();
            table.add(birdLabel).expandX().padTop(10);
            table.add(birdscoreLabel).expandX().padTop(10);

            table.row();
            table.add(bearLabel).expandX().padTop(10);
            table.add(bearscoreLabel).expandX().padTop(10);

            table.row();
            table.add(sealLabel).expandX().padTop(10);
            table.add(sealscoreLabel).expandX().padTop(10);

            table.row();
            table.add(yetiLabel).expandX().padTop(10);
            table.add(yetiscoreLabel).expandX().padTop(10);

            table.row();
            table.add(teroLabel).expandX().padTop(10);
            table.add(teroscoreLabel).expandX().padTop(10);

            table.row();
            table.add(carrotLabel).expandX().padTop(10);
            table.add(carrotscoreLabel).expandX().padTop(10);

            table.row();
            table.add(total).expandX().padTop(10);
            table.add(poposcoreLabel).expandX().padTop(10);
        } else {
            table.add(popoLabel).expandX().padTop(10);
            table.add(spaceLabel).expandX().padTop(10);
            table.add(nanaLabel).expandX().padTop(10);

            table.row();
            table.add(brick).expandX().padTop(10);
            table.add(brickScoreLabel).expandX().padTop(10);
            table.add(brick2).expandX().padTop(10);
            table.add(brickScoreLabel2).expandX().padTop(10);


            table.row();
            table.add(pumpkinLabel).expandX().padTop(10);
            table.add(pumpkinscoreLabel).expandX().padTop(10);
            table.add(pumpkinLabel2).expandX().padTop(10);
            table.add(pumpkinscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(onionLabel).expandX().padTop(10);
            table.add(onionscoreLabel).expandX().padTop(10);
            table.add(onionLabel2).expandX().padTop(10);
            table.add(onionscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(carrotLabel).expandX().padTop(10);
            table.add(carrotscoreLabel).expandX().padTop(10);
            table.add(carrotLabel2).expandX().padTop(10);
            table.add(carrotscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(birdLabel).expandX().padTop(10);
            table.add(birdscoreLabel).expandX().padTop(10);
            table.add(birdLabel2).expandX().padTop(10);
            table.add(birdscoreLabel2).expandX().padTop(10);


            table.row();
            table.add(bearLabel).expandX().padTop(10);
            table.add(bearscoreLabel).expandX().padTop(10);
            table.add(bearLabel2).expandX().padTop(10);
            table.add(bearscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(sealLabel).expandX().padTop(10);
            table.add(sealscoreLabel).expandX().padTop(10);
            table.add(sealLabel2).expandX().padTop(10);
            table.add(sealscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(yetiLabel).expandX().padTop(10);
            table.add(yetiscoreLabel).expandX().padTop(10);
            table.add(yetiLabel2).expandX().padTop(10);
            table.add(yetiscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(teroLabel).expandX().padTop(10);
            table.add(teroscoreLabel).expandX().padTop(10);
            table.add(teroLabel2).expandX().padTop(10);
            table.add(teroscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(carrotLabel).expandX().padTop(10);
            table.add(carrotscoreLabel).expandX().padTop(10);
            table.add(carrotLabel2).expandX().padTop(10);
            table.add(carrotscoreLabel2).expandX().padTop(10);

            table.row();
            table.add(total).expandX().padTop(10);
            table.add(poposcoreLabel).expandX().padTop(10);
            table.add(total2).expandX().padTop(10);
            table.add(nanascoreLabel).expandX().padTop(10);
        }
        stage.clear();
        stage.addActor(table);

    }

    public static void addScore(int value, Class<?> type, boolean popo) {
        if (popo){
            if (type == Onion.class) {
                onionScore += value;
                onionscoreLabel.setText(String.format("%05d", onionScore));
            } else if (type == Carrot.class) {
                carrotScore += value;
                carrotscoreLabel.setText(String.format("%05d", carrotScore));
            } else if (type == Pumpkin.class) {
                pumpkinScore += value;
                pumpkinscoreLabel.setText(String.format("%05d", pumpkinScore));
            } else if (type == Bear.class) {
                bearScore += value;
                bearscoreLabel.setText(String.format("%05d", bearScore));
            } else if (type == Seal.class) {
                sealScore += value;
                sealscoreLabel.setText(String.format("%05d", sealScore));
            } else if (type == Bird.class) {
                birdScore += value;
                birdscoreLabel.setText(String.format("%05d", birdScore));
            } else if (type == Yeti.class) {
                yetiScore += value;
                yetiscoreLabel.setText(String.format("%05d", yetiScore));
            } else if (type == Pterodactyl.class) {
                teroScore += value;
                teroscoreLabel.setText(String.format("%05d", teroScore));
            } else if (type == Brick.class) {
                brickScore += value;
                brickScoreLabel.setText(String.format("%05d", brickScore));
            }
        } else {
            if (type == Onion.class) {
                onionScore2 += value;
                onionscoreLabel2.setText(String.format("%05d", onionScore2));
            } else if (type == Carrot.class) {
                carrotScore2 += value;
                carrotscoreLabel2.setText(String.format("%05d", carrotScore2));
            } else if (type == Pumpkin.class) {
                pumpkinScore2 += value;
                pumpkinscoreLabel2.setText(String.format("%05d", pumpkinScore2));
            } else if (type == Bear.class) {
                bearScore2 += value;
                bearscoreLabel2.setText(String.format("%05d", bearScore2));
            } else if (type == Seal.class) {
                sealScore2 += value;
                sealscoreLabel2.setText(String.format("%05d", sealScore2));
            } else if (type == Bird.class) {
                birdScore2 += value;
                birdscoreLabel2.setText(String.format("%05d", birdScore2));
            } else if (type == Yeti.class) {
                yetiScore2 += value;
                yetiscoreLabel2.setText(String.format("%05d", yetiScore2));
            } else if (type == Pterodactyl.class) {
                teroScore2 += value;
                teroscoreLabel2.setText(String.format("%05d", teroScore2));
            } else if (type == Brick.class) {
                brickScore2 += value;
                brickScoreLabel2.setText(String.format("%05d", brickScore2));
            }
        }


    }

    public static int getPopoLives() {
        return popoLives;
    }

    public static int getNanaLives() {
        return nanaLives;
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
        nanaLives -= value;
        nanaLivesLabel.setText(String.format("%01d", nanaLives));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
