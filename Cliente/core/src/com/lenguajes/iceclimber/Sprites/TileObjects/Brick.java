package com.lenguajes.iceclimber.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Screens.GameScreen;

public class Brick extends InteractiveTileObject{
    public Brick(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(IceClimber.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(IceClimber.DESTROYED_BIT);
        getCell().setTile(null);
        IceClimber.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        Hud.addScorePopo(200);
    }
}
