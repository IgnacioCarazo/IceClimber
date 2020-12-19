package com.lenguajes.iceclimber.Sockets.jsonManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class jsonHandler {
    public String jsonWriter(jsonFactory student) {
        Gson gson = null;
        if (student.getID() == null) {
            gson = new GsonBuilder()
                    .create();
        } else {
            gson = new Gson();
        }
        return gson.toJson(student);
    }
    public boolean jsonReader(String serverMessage){
        Gson gson = new Gson();
        jsonFactory message = gson.fromJson(serverMessage, jsonFactory.class);
        System.out.println(message.getID());
        int items[] = message.getItems();
        System.out.println(message.getLevel());
        return true;
    }
}
