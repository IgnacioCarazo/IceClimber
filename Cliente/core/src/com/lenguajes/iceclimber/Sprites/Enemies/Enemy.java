package com.lenguajes.iceclimber.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public Vector2 birdVelocity;
    private boolean facingLeft;


    public Enemy(GameScreen screen, float floor, boolean facingLeft) {
        this.world = screen.getWorld();
        this.screen = screen;
        this.facingLeft = facingLeft;
        int x;
        // Se settea la posicion en x dependiendo de hacia que direccion se le dice que vaya
        if (facingLeft) {
            x = 270;
        } else {
            x = 0;
        }
        // se settea la posicion en x y en y acorde a la
        setPosition(x, floor);
        defineEnemy();


    }

    // Polimorfismo
    protected abstract void defineEnemy();

    public abstract void hitOnHead();

    public abstract void update(float dt);


}
