package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Screens.GameScreen;
import com.lenguajes.iceclimber.Sprites.Enemies.Bird;

public class Pumpkin extends Fruit {
    public Pumpkin(GameScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getFruitAtlas().findRegion("fruit"), 2, 21, 16, 16);


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
        if (!this.toDestroy) {
            if (popo) {
                Hud.addScore(600, Pumpkin.class,true);
                Hud.addScorePopo(600);
            } else {
                Hud.addScore(600,Pumpkin.class,false);
                Hud.addScoreNana(600);
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
