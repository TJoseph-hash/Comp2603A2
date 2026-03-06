package org.A2;

import java.util.ArrayList;

public class Dungeon {
    private ArrayList<Room> rooms;
    private Player player;
    private int currentRoomIndex;

    // Constructor
    public Dungeon(Player player) {
        this.player = player;
        rooms = new ArrayList<>();
        currentRoomIndex = 0;
    }

    // addRoom
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // getPlayer
    public Player getPlayer() {
        return player;
    }

    // getRooms
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // getCurrentRoom
    public Room getCurrentRoom() {
        if (rooms.size() == 0) {
            return null;
        }
        return rooms.get(currentRoomIndex);
    }

    // moveToNextRoom
    public boolean moveToNextRoom() {
        Room current = getCurrentRoom();

        if (current != null && current.isCleared() && currentRoomIndex < rooms.size() - 1) {
            currentRoomIndex++;
            return true;
        }

        return false;
    }

    // combatRound
    public ArrayList<String> combatRound() {

        ArrayList<String> log = new ArrayList<>();
        Room room = getCurrentRoom();

        if (room == null) {
            return log;
        }

        ArrayList<Enemy> aliveEnemies = room.getAliveEnemies();

        if (aliveEnemies.size() == 0) {
            return log;
        }

        // Player attacks first alive enemy
        Enemy target = aliveEnemies.get(0);
        String playerAttack = player.attack(target);
        log.add(playerAttack);

        // Enemies attack player
        for (Enemy enemy : room.getAliveEnemies()) {

            String enemyAttack = enemy.attack(player);
            log.add(enemyAttack);

            if (!player.isAlive()) {
                log.add(player.getName() + " has been defeated!");
                break;
            }
        }

        return log;
    }

    // lootRoom
    public ArrayList<Item> lootRoom() {

        ArrayList<Item> lootedItems = new ArrayList<>();
        Room room = getCurrentRoom();

        if (room == null || !room.isCleared()) {
            return lootedItems;
        }

        for (Item item : room.getLoot()) {
            player.addItem(item);
            lootedItems.add(item);
        }

        return lootedItems;
    }

    // isGameOver
    public boolean isGameOver() {
        return !player.isAlive();
    }

    // isGameWon
    public boolean isGameWon() {

        if (!player.isAlive()) {
            return false;
        }

        if (rooms.size() == 0) {
            return false;
        }

        Room lastRoom = rooms.get(rooms.size() - 1);

        return currentRoomIndex == rooms.size() - 1 && lastRoom.isCleared();
    }
}
