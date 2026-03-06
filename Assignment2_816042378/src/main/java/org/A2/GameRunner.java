package org.A2;

import java.util.ArrayList;
import java.util.Scanner;

public class GameRunner {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println(" DUNGEON CRAWLER - TERMINAL EDITION ");
        System.out.println("========================================");
        System.out.println();
        // --- Character creation ---
        System.out.print("Enter your hero's name: ");
        String heroName = scanner.nextLine().trim();
        if (heroName.isEmpty()) heroName = "Adventurer";
        Player player = new Player(heroName, 100, 15);
        Dungeon dungeon = new Dungeon(player);
        // --- Build the dungeon ---
        Room room1 = new Room("The Dark Entrance");
        room1.addEnemy(new Goblin("Sneaky Goblin"));
        room1.addEnemy(new Goblin("Grumpy Goblin"));
        room1.addLoot(new HealthPotion("Small Potion",
                "A vial of red liquid", 15));
        Room room2 = new Room("The Armoury");
        room2.addEnemy(new Enemy("Skeleton Warrior", 40, 8));
        room2.addLoot(new Sword("Steel Sword", "A sharp, gleaming blade", 5));
        room2.addLoot(new HealthPotion("Medium Potion",
                "A flask of healing brew", 25));
        Room room3 = new Room("The Goblin Den");
        room3.addEnemy(new Goblin("Goblin Archer"));
        room3.addEnemy(new Goblin("Goblin Brute"));
        room3.addEnemy(new Goblin("Goblin Shaman"));
        room3.addLoot(new HealthPotion("Large Potion", "A full bottle of red elixir", 40));
        Room room4 = new Room("The Throne of Shadows");
        room4.addEnemy(new Boss("Shadow Dragon", 100, 12));
        room4.addLoot(new Sword("Legendary Blade", "Pulsing with ancient power", 10));
        dungeon.addRoom(room1);
        dungeon.addRoom(room2);
        dungeon.addRoom(room3);
        dungeon.addRoom(room4);
        System.out.println("\n" + player.getName()
                + " enters the dungeon...\n");
        // --- Game loop ---
        while (!dungeon.isGameOver()
                && !dungeon.isGameWon()) {
            Room current = dungeon.getCurrentRoom();
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println(" " + current.getName());
            System.out.println(player);
            System.out.println("Enemies alive: "
                    + current.getAliveEnemies().size());
            System.out.println("Loot available: "
                    + current.getLoot().size());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            if (!current.isCleared()) {
                System.out.println("\nEnemies block your path!");
                for (Enemy e : current.getAliveEnemies()) {
                    System.out.println(" ⚔ " + e);
                }
                System.out.print(
                        "\nPress ENTER to fight...");
                scanner.nextLine();
                // Combat
                while (!current.isCleared()
                        && !dungeon.isGameOver()) {
                    ArrayList<String> log =
                            dungeon.combatRound();
                    System.out.println();
                    for (String msg : log) {
                        System.out.println(" " + msg);
                    }
                }
                if (dungeon.isGameOver()) break;
                System.out.println(
                        "\n Room cleared!\n");
            }
            // Loot
            ArrayList<Item> loot = dungeon.lootRoom();
            if (!loot.isEmpty()) {
                System.out.println(" Loot collected:");
                for (Item item : loot) {
                    System.out.println(" + " + item);
                }
                System.out.println("\n" + player);
            }
            // Move
            if (!dungeon.isGameWon()) {
                System.out.print(
                        "\nPress ENTER to move to the "
                                + "next room...");
                scanner.nextLine();
                dungeon.moveToNextRoom();
                System.out.println();
            }
        }
        // --- End screen ---
        System.out.println();
        System.out.println(
                "========================================");
        if (dungeon.isGameWon()) {
            System.out.println(" VICTORY! "
                    + player.getName()
                    + " conquered the dungeon!");
            System.out.println(" Final stats: " + player);
            System.out.println(" Items collected: "
                    + player.getInventorySize());
        } else {
            System.out.println(" GAME OVER. "
                    + player.getName()
                    + " has fallen...");
            System.out.println(" You made it through "
                    + dungeon.getRooms().indexOf(
                    dungeon.getCurrentRoom())
                    + " rooms.");
        }
        System.out.println(
                "========================================");
        scanner.close();
    }
}
