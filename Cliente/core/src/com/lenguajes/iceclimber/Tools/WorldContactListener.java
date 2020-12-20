package com.lenguajes.iceclimber.Tools;

import com.badlogic.gdx.physics.box2d.*;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Sprites.Enemies.Enemy;
import com.lenguajes.iceclimber.Sprites.Items.Fruit;
import com.lenguajes.iceclimber.Sprites.MainCharacters.Nana;
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
                if (object.getUserData() instanceof Popo) {
                    ((InteractiveTileObject) object.getUserData()).onHeadHit(true);
                } else {
                    ((InteractiveTileObject) object.getUserData()).onHeadHit(false);
                }

            }
        }

        // COLISIONES
        switch (cDef) {
            // Caso entre el jugador y un bloque
            case IceClimber.PLAYER_HEAD_BIT | IceClimber.BRICK_BIT:
                if (fixA.getFilterData().categoryBits == IceClimber.PLAYER_HEAD_BIT) {
                    if (fixA.getUserData() instanceof Popo) {
                        ((InteractiveTileObject) fixB.getUserData()).onHeadHit(true);
                    } else if (fixA.getUserData() instanceof Nana){
                        ((InteractiveTileObject) fixB.getUserData()).onHeadHit(false);
                    }

                } else {
                    if (fixB.getUserData() instanceof Popo) {
                        ((InteractiveTileObject) fixA.getUserData()).onHeadHit(true);
                    } else if (fixB.getUserData() instanceof Nana){
                        ((InteractiveTileObject) fixA.getUserData()).onHeadHit(false);
                    }

                }
                break;

            // Caso entre la cabeza del enemigo y el jugador
            case IceClimber.ENEMY_HEAD_BIT | IceClimber.PLAYER_BIT:
                if (fixA.getFilterData().categoryBits == IceClimber.ENEMY_HEAD_BIT)

                    if (fixA.getUserData() instanceof Enemy) {
                        if (fixB.getUserData() instanceof Popo) {
                            ((Enemy) fixA.getUserData()).hitOnHead((Popo) fixB.getUserData(), true);
                        } else {
                            ((Enemy) fixA.getUserData()).hitOnHead((Nana) fixB.getUserData(), true);
                        }

                    } else if (fixB.getUserData() instanceof Enemy) {
                        if (fixA.getUserData() instanceof Popo) {
                            ((Enemy) fixB.getUserData()).hitOnHead((Popo) fixA.getUserData(), true);
                        } else {
                            ((Enemy) fixB.getUserData()).hitOnHead((Nana) fixA.getUserData(), true);
                        }

                    }

                break;

            // Caso entre jugador y enemigo
            case IceClimber.PLAYER_BIT | IceClimber.ENEMY_BIT:
                if (fixA.getUserData() instanceof Enemy) {
                    if (fixB.getUserData() instanceof Popo) {
                        ((Popo) fixB.getUserData()).hit((Enemy) fixA.getUserData());
                    } else if (fixB.getUserData() instanceof Nana) {
                        ((Nana) fixB.getUserData()).hit((Enemy) fixA.getUserData());
                    }

                } else if (fixB.getUserData() instanceof Enemy) {
                    if (fixA.getUserData() instanceof Popo) {
                        ((Popo) fixA.getUserData()).hit((Enemy) fixB.getUserData());
                    } else if (fixB.getUserData() instanceof Nana) {
                        ((Nana) fixA.getUserData()).hit((Enemy) fixB.getUserData());
                    }
                }
                break;

            // Caso entre jugador y fruta
            case IceClimber.FRUIT_BIT | IceClimber.PLAYER_BIT:
                if (fixA.getUserData() instanceof Fruit) {
                    if (fixB.getUserData() instanceof Popo) {
                        ((Fruit) fixA.getUserData()).use(true);
                    } else if (fixB.getUserData() instanceof Nana) {
                        ((Fruit) fixA.getUserData()).use(false);
                    }
                } else if (fixB.getUserData() instanceof Fruit) {
                    if (fixA.getUserData() instanceof Popo) {
                        ((Fruit) fixB.getUserData()).use(true);
                    } else if (fixA.getUserData() instanceof Nana) {
                        ((Fruit) fixB.getUserData()).use(false);
                    }
                }
                break;
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



