package com.lenguajes.iceclimber.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Scenes.Hud;
import com.lenguajes.iceclimber.Sprites.Enemies.Enemy;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Popo;
import com.lenguajes.iceclimber.Sprites.TileObjects.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

        switch (cDef) {
            case IceClimber.PLAYER_HEAD_BIT | IceClimber.BRICK_BIT:
                if(fixA.getFilterData().categoryBits == IceClimber.PLAYER_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit();
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit();
                break;
            case IceClimber.ENEMY_HEAD_BIT | IceClimber.PLAYER_BIT:
                if (fixA.getFilterData().categoryBits == IceClimber.ENEMY_HEAD_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                } else if (fixA.getFilterData().categoryBits == IceClimber.ENEMY_HEAD_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnHead();
                }
            //case IceClimber.PLAYER_BIT | IceClimber.ENEMY_BIT:
               // Hud.removeLivePopo(1);
                //break;

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}



