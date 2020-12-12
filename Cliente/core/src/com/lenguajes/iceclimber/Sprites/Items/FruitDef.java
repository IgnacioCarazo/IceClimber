package com.lenguajes.iceclimber.Sprites.Items;

import com.badlogic.gdx.math.Vector2;

public class FruitDef {
    public Vector2 position;
    public Class<?> type;

    public FruitDef(Vector2 position, Class<?> type) {
        this.position = position;
        this.type = type;
    }
}
