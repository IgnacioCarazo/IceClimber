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

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;


    private int popoScore;
    private int nanaScore;

    Label poposcoreLabel;
    Label nanascoreLabel;

    Label popoLabel;
    Label nanaLabel;

    public Hud(SpriteBatch sb) {

        popoScore = 0;
        nanaScore = 0;

        viewport = new FitViewport(IceClimber.WIDTH,IceClimber.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        poposcoreLabel = new Label(String.format("%06d", popoScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanascoreLabel = new Label(String.format("%06d", nanaScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        popoLabel = new Label("POPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nanaLabel = new Label("NANA", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        if (MainMenuScreen.players == 1) {
            table.add(popoLabel).expandX().padTop(10);
            table.row();
            table.add(poposcoreLabel).expandX();
        } else {
            table.add(popoLabel).expandX().padTop(10);
            table.add(nanaLabel).expandX().padTop(10);
            table.row();
            table.add(poposcoreLabel).expandX();
            table.add(nanascoreLabel).expandX();
        }



        stage.addActor(table);


    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
