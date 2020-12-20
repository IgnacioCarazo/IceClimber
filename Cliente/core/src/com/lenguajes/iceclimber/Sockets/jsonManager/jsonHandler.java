package com.lenguajes.iceclimber.Sockets.jsonManager;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lenguajes.iceclimber.IceClimber;
import com.lenguajes.iceclimber.Screens.GameScreen;
import com.lenguajes.iceclimber.Sprites.Enemies.*;
import com.lenguajes.iceclimber.Sprites.Items.Carrot;
import com.lenguajes.iceclimber.Sprites.Items.FruitDef;
import com.lenguajes.iceclimber.Sprites.Items.Onion;
import com.lenguajes.iceclimber.Sprites.Items.Pumpkin;

public class jsonHandler {
    public String jsonWriter(jsonFactory student) {
        Gson gson = null;
        if (student.getID() == null) {
            gson = new GsonBuilder()
                    .create();
        }
        else {
            gson = new Gson();
        }
        return gson.toJson(student);
    }
    public boolean jsonReader(String serverMessage, GameScreen gameScreen) {

        Gson gson = new Gson();

        jsonFactory message = gson.fromJson(serverMessage, jsonFactory.class);

        //System.out.println(message.getID());
        boolean facing = true;

        int items[] = message.getItems();

        if(gameScreen.host) {
            if (items[0] == 1 || items[0] == 2 || items[0] == 3 || items[0] == 4) {
                if (items[1] == 0) {
                    facing = false;
                }
            }
            if (items[0] == 1) {
                gameScreen.spawnEnemy(new EnemyDef(new Vector2(10, items[2]), Yeti.class, facing));
            } else if (items[0] == 2) {
                gameScreen.spawnEnemy(new EnemyDef(new Vector2(10, items[2]), Bird.class, facing));
            } else if (items[0] == 3) {
                gameScreen.spawnEnemy(new EnemyDef(new Vector2(10, items[2]), Bear.class, facing));
            } else if (items[0] == 4) {
                gameScreen.spawnEnemy(new EnemyDef(new Vector2(10, items[2]), Seal.class, facing));
            } else if (items[0] == 5) {
                gameScreen.spawnFruit(new FruitDef(new Vector2(items[1] / IceClimber.PPM, items[2]/ IceClimber.PPM), Carrot.class));
            } else if (items[0] == 6) {
                gameScreen.spawnFruit(new FruitDef(new Vector2(items[1] / IceClimber.PPM, items[2]/ IceClimber.PPM), Onion.class));
            } else if (items[0] == 7) {
                gameScreen.spawnFruit(new FruitDef(new Vector2(items[1] / IceClimber.PPM, items[2] / IceClimber.PPM), Pumpkin.class));
            }
        }
        //System.out.println(message.getLevel());

        return true;
    }
}
