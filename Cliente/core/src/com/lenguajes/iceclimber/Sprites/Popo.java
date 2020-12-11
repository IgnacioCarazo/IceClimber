package com.lenguajes.iceclimber.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;

public class Popo extends Sprite {
    public World world;
    public Body b2body;

    public Popo(World world) {
        this.world= world;
        definePopo();
    }

    public void definePopo() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / IceClimber.PPM,200 / IceClimber.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / IceClimber.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
