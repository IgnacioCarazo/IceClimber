package com.lenguajes.iceclimber.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Screens.GameScreen;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Nana;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Popo;

public class Yeti extends Enemy{

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean facingLeft;

    public Yeti(GameScreen screen, float floor, boolean facingLeft) {
        super(screen, floor-20, facingLeft);

        // define su velocidad dependiendo de hacia donde esta viendo y si hay bonus
        if (facingLeft) {
            if (!screen.bonus) {
                velocity = new Vector2(-0.3f, 0);
            } else {
                velocity = new Vector2(-0.7f, 0);
            }

        } else {
            if (!screen.bonus) {
                velocity = new Vector2(0.3f, 0);
            } else {
                velocity = new Vector2(0.7f, 0);
            }
        }

        frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("yeti_left"), i * 20, 3, 20, 16));
        }
        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 30 / IceClimber.PPM, 26 / IceClimber.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float dt) {
        stateTime += dt;
        if (b2body.getPosition().x * 100 > 350) {
            setToDestroy = true;
        } else if (b2body.getPosition().x * 100 < -10) {
            setToDestroy = true;
        }
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("yeti_left"), 0, 0, 20, 16));
            stateTime = 0;
        }
        else if (!destroyed) {

            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime, true));
        }

    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX() / IceClimber.PPM, getY() / IceClimber.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / IceClimber.PPM);

        fdef.filter.categoryBits = IceClimber.ENEMY_BIT;
        fdef.filter.maskBits = IceClimber.GROUND_BIT |
                IceClimber.BRICK_BIT |
                IceClimber.ENEMY_BIT |
                IceClimber.PLAYER_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-8,12).scl(1/IceClimber.PPM);
        vertice[1] = new Vector2(8,12).scl(1/IceClimber.PPM);
        vertice[2] = new Vector2(-3,3).scl(1/IceClimber.PPM);
        vertice[3] = new Vector2(3,3).scl(1/IceClimber.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = IceClimber.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch){
        if (!destroyed || stateTime < 1) {
            super.draw(batch);
        }
    }

    @Override
    public void hitOnHead(Popo popo, boolean head) {
        if (!setToDestroy && head){
            Hud.addScore(300,Yeti.class,true);
            Hud.addScorePopo(300);
        } else if (!setToDestroy && !head) {
            Hud.removeLivePopo(1);
        }

        setToDestroy = true;
    }
    @Override
    public void hitOnHead(Nana nana, boolean head) {
        if (!setToDestroy && head){
            Hud.addScore(300,Yeti.class,false);
            Hud.addScoreNana(300);
        } else if (!setToDestroy && !head) {
            Hud.removeLiveNana(1);
        }
        setToDestroy = true;
    }
}
