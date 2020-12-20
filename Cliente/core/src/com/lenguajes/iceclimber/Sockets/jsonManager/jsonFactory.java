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
        ITEMS = new int[] {1, 44, 322};
        LVL = "bonus";
    }
}
