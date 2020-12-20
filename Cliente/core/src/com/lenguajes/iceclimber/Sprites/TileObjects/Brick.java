package com.lenguajes.iceclimber.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Screens.GameScreen;
import com.lenguajes.iceclimber.Sprites.Enemies.Bird;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Nana;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Popo;

/**
 * La clase brick es la correspondiente a aquellos bloques que se pueden romper por algun personaje.
 * Al inicializar el mapa en la clase GameScreen se inicializan todos lo bloques dentro del mapa
 */
public class Brick extends InteractiveTileObject {
    public Brick(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(IceClimber.BRICK_BIT);
    }

    @Override
    public void onHeadHit(boolean popo) {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(IceClimber.DESTROYED_BIT);
        getCell().setTile(null);
        IceClimber.manager.get("audio/sounds/breakblock.wav", Sound.class).play();

        if (popo) {
            Hud.addScore(50, Brick.class, true);
            Hud.addScorePopo(50);
        }
        if (!popo) {
            Hud.addScore(50, Brick.class, false);
            Hud.addScoreNana(50);
        }

    }


}
