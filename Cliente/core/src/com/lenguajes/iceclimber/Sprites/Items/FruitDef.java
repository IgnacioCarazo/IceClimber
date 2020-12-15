package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.math.Vector2;

/**
 * Esta clase funciona como una especie de factory para las diferentes frutas
 */
public class FruitDef {
    public Vector2 position;
    public Class<?> type;

    /**
     * Constructore de la clase.
     * @param position vector con posx y posy donde se desea crear la fruta.
     * @param type el tipo de fruta que es (Carrot, Onion, Pumpkin).
     */
    public FruitDef(Vector2 position, Class<?> type) {
        this.position = position;
        this.type = type;
    }
}
