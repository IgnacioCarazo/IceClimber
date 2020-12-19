package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Screens.GameScreen;

public class Onion extends Fruit{
    public Onion(GameScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getFruitAtlas().findRegion("fruit"), 17,1,13,17);


    }

    @Override
    public void defineFruit() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / IceClimber.PPM);

        fdef.filter.categoryBits = IceClimber.FRUIT_BIT;
        fdef.filter.maskBits = IceClimber.GROUND_BIT |
                IceClimber.BRICK_BIT |

                IceClimber.PLAYER_BIT;

        fdef.shape = shape;
        this.b2body.createFixture(fdef);
        this.b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(boolean popo) {
        System.out.println("useuseuseuseuse");
        if (!this.toDestroy) {
            if (popo) {
                Hud.addScorePopo(500);
            } else {
                Hud.addScoreNana(300);
            }
        }
        destroy();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

    }
}
