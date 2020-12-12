package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;

public class Onion extends Fruit{
    public Onion(GameScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getFruitAtlas().findRegion("fruit"), 17,1,13,17);
    }

    @Override
    public void defineFruit() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX() / IceClimber.PPM, getY() / IceClimber.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / IceClimber.PPM);


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


    }

    @Override
    public void use() {
        destroy();
    }

    @Override
    public void update(float dt) {
        super.update(dt);

    }
}
