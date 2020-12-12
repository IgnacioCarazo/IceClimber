package com.lenguajes.iceclimber.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;
import com.lenguajes.iceclimber.Sprites.TileObjects.Brick;

public class B2WorldCreator {

    public B2WorldCreator(GameScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / IceClimber.PPM, (rect.getY() + rect.getHeight() / 2) / IceClimber.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / IceClimber.PPM, rect.getHeight() / 2 / IceClimber.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // create bricks bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);
        }
    }
}
