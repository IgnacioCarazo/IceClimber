package com.lenguajes.iceclimber.Sockets.jsonManager;
public class jsonFactory {
    private String ID;
    private int[] ITEMS;
    private String LVL;

    public String getID(){
        return ID;
    }
    public int[] getItems(){
        return ITEMS;
    }
    public String getLevel(){
        return LVL;
    }

    public jsonFactory(int t) {
        ID = "popo";
        ITEMS = new int[] {6,20,30};
        LVL = "bonus";
    }
}
