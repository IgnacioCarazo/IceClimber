package com.lenguajes.iceclimber.Sprites.Enemies;

import com.badlogic.gdx.math.Vector2;

public class EnemyDef {
    public Vector2 position;
    public Class<?> type;
    public boolean facingLeft;

    public EnemyDef(Vector2 position, Class<?> type, boolean facingLeft) {
        this.position = position;
        this.type = type;
        this.facingLeft = facingLeft;
    }
}

