package com.lenguajes.iceclimber.Sprites.Enemies;

import com.badlogic.gdx.math.Vector2;

/**
 * Esta clase funciona como un tipo de factory para los enemigos
 */
public class EnemyDef {
    public Vector2 position;
    public Class<?> type;
    public boolean facingLeft;

    /**
     * Constructor de la clase.
     * @param position Un vector con la posicion x y y donde quiere crearse el enemigo
     * @param type El tipo de enemigo (Yeti, Bear, Bird, Pterodactyl, Seal, Yeti)
     * @param facingLeft hacia que direccion se esta moviendo
     */
    public EnemyDef(Vector2 position, Class<?> type, boolean facingLeft) {
        this.position = position;
        this.type = type;
        this.facingLeft = facingLeft;
    }
}

