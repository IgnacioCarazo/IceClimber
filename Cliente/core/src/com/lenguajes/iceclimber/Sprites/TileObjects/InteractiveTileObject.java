package com.lenguajes.iceclimber.Sprites.TileObjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;

/**
 * Esta clase permite compartirle atributos a cualquier tiled object dentro del mapa que sea interactuable
 */
public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    protected GameScreen screen;

    /**
     * Constructor de la clase
     * @param screen La pantalla donde se quiera crear
     * @param bounds Los bounds de un rectangulo
     */
    public InteractiveTileObject(GameScreen screen, Rectangle bounds) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / IceClimber.PPM, (bounds.getY() + bounds.getHeight() / 2) / IceClimber.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / IceClimber.PPM, bounds.getHeight() / 2 / IceClimber.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    /**
     * Se llama si algun personaje golpeo con la cabeza un bloque
     */
    public abstract void onHeadHit();

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int) (body.getPosition().x * IceClimber.PPM / 16), (int) (body.getPosition().y * IceClimber.PPM / 16));
    }

}
