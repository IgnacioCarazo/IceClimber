package com.lenguajes.iceclimber.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;

public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / IceClimber.PPM, (bounds.getY() + bounds.getHeight() / 2) / IceClimber.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / IceClimber.PPM, bounds.getHeight() / 2 / IceClimber.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
