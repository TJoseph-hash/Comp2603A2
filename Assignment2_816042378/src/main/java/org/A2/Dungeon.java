package org.A2;

import java.util.ArrayList;

public class Dungeon {
    private ArrayList<Room> rooms;
    private Player player;
    private int currentRoomIndex;

    public Dungeon(ArrayList<Room> rooms, Player player, int currentRoomIndex){
        this.rooms= new ArrayList<>();
        this.player = player;
        this.currentRoomIndex = 0;
    }
    public void addRoom (Room room){

    }
    public Player getPlayer(){return player;}
    public ArrayList<Room> getRooms(){return rooms;}
    public int getCurrentRoomIndex(){return currentRoomIndex;}
    public boolean moveToNextRoom(){
        boolean move = false;

    }
}
