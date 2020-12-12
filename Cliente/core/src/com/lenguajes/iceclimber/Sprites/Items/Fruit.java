package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;

public abstract class Fruit extends Sprite {
    protected GameScreen screen;
    protected World world;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body b2body;



    public Fruit (GameScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        setBounds(getX(), getY(), 16 / IceClimber.PPM, 16 / IceClimber.PPM );
    }

    public abstract void defineFruit();
    public abstract void use();

    public void update(float dt) {
        if (toDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
        }
    }

    public void destroy() {
        toDestroy = true;
    }

    public void draw(Batch batch) {
        if (!destroyed) {
            super.draw(batch);
        }
    }

}
