package org.A2;

import java.util.ArrayList;



    public class DungeonTestDriver {
        static int passed = 0;
        static int failed = 0;
        static void test(String desc, boolean condition) {
            if (condition) {
                System.out.println("PASS: " + desc);
                passed++;
            } else {
                System.out.println("FAIL: " + desc);
                failed++;
            }
        }
        public static void main(String[] args) {
            System.out.println("=== TEST SUITE: Dungeon Crawler ===\n");
            // --- Entity / Player Tests ---
            Player p = new Player("Hero", 100, 15);
            test("1. Player name", p.getName().equals("Hero"));
            test("2. Player health", p.getHealth() == 100);
            test("3. Player attackPower", p.getAttackPower() == 15);
            test("4. Player getType", p.getType().equals("Player"));
            test("5. Player isAlive", p.isAlive() == true);
            test("6. Player toString", p.toString().equals("[Player] Hero (HP: 100, ATK: 15)"));
            test("7. Player inventory starts empty", p.getInventorySize() == 0);
            // --- Enemy / Goblin / Boss Tests ---
            Goblin g1 = new Goblin("Grik");
            test("8. Goblin health is 30", g1.getHealth() == 30);
            test("9. Goblin attackPower is 5", g1.getAttackPower() == 5);
            test("10. Goblin getType", g1.getType().equals("Goblin"));
            Boss boss = new Boss("Dragon", 80, 10);
            test("11. Boss getType", boss.getType().equals("Boss"));
            test("12. Boss toString", boss.toString().equals("[Boss] Dragon (HP: 80, ATK: 10)"));
            // --- Combat Tests ---
            Enemy dummy = new Enemy("Dummy", 20, 0);
            String atkMsg = p.attack(dummy);
            test("13. Player attack reduces enemy HP",
                    dummy.getHealth() == 5);
            test("14. Player attack message",
                    atkMsg.equals("Hero attacks Dummy for 15 damage."));
            // Boss double damage
            Player target = new Player("Target", 100, 10);
            String bossMsg = boss.attack(target);
            test("15. Boss deals double damage",
                    target.getHealth() == 80);
            test("16. Boss attack message",
                    bossMsg.equals("Dragon attacks Target for 20 damage."));
            // --- takeDamage floor at 0 ---
            Enemy weak = new Enemy("Weak", 5, 1);
            weak.takeDamage(999);
            test("17. Health does not go below 0",
                    weak.getHealth() == 0);
            test("18. Dead entity isAlive false", weak.isAlive() == false);
            // --- Item Tests ---
            HealthPotion hp = new HealthPotion(
                    "Small Potion", "Restores 20 HP", 20);
            test("19. HealthPotion name",
                    hp.getName().equals("Small Potion"));
            test("20. HealthPotion toString",
                    hp.toString().equals("Small Potion: Restores 20 HP"));
            Sword sw = new Sword("Iron Sword", "A sturdy blade", 5);
            test("21. Sword getBonusDamage", sw.getBonusDamage() == 5);
            // --- Lootable / addItem Tests ---
            Player p2 = new Player("Tester", 50, 10);
            p2.addItem(hp);
            test("22. HealthPotion heals on pickup",
                    p2.getHealth() == 70);
            test("23. Item added to inventory",
                    p2.getInventorySize() == 1);
            p2.addItem(sw);
            test("24. Sword boosts ATK on pickup",
                    p2.getAttackPower() == 15);
            test("25. Inventory size after sword",
                    p2.getInventorySize() == 2);
            // --- Room Tests ---
            Room r1 = new Room("Dark Cave");
            Goblin g2 = new Goblin("Snark");
            Goblin g3 = new Goblin("Blip");
            r1.addEnemy(g2);
            r1.addEnemy(g3);
            r1.addLoot(new HealthPotion("Potion", "Heals 10", 10));
            test("26. Room toString", r1.toString().equals(
                    "Room: Dark Cave [Enemies: 2, Loot: 1]"));
            test("27. Room not cleared initially", r1.isCleared() == false);
            test("28. getAliveEnemies count", r1.getAliveEnemies().size() == 2);
            // Kill all enemies manually
            g2.takeDamage(100);
            g3.takeDamage(100);
            test("29. Room cleared after enemies defeated", r1.isCleared() == true);
            test("30. getAliveEnemies empty after clear", r1.getAliveEnemies().size() == 0);
            // --- Dungeon Tests ---
            Player hero = new Player("Adventurer", 100, 20);
            Dungeon dun = new Dungeon(hero);
            Room room1 = new Room("Entry Hall");
            Goblin gr1 = new Goblin("Grunt");
            room1.addEnemy(gr1);
            room1.addLoot(new Sword("Bronze Sword", "+3 ATK", 3));
            Room room2 = new Room("Throne Room");
            Boss finalBoss = new Boss("Demon King", 50, 8);
            room2.addEnemy(finalBoss);
            dun.addRoom(room1);
            dun.addRoom(room2);
            test("31. Current room is first room", dun.getCurrentRoom().getName().equals("Entry Hall"));
            test("32. Cannot move before clearing room", dun.moveToNextRoom() == false);
            // Combat round 1: hero(ATK20) vs Grunt(HP30,ATK5)
            ArrayList<String> log1 = dun.combatRound();
            test("33. Combat log has messages", log1.size() >= 1);
            test("34. Goblin took damage", gr1.getHealth() == 10);
            test("35. Player took damage from goblin",
                    hero.getHealth() == 95);
            // Combat round 2: finish goblin
            ArrayList<String> log2 = dun.combatRound();
            test("36. Goblin defeated",
                    gr1.isAlive() == false);
            test("37. Room 1 cleared",
                    room1.isCleared() == true);
            // Loot room
            ArrayList<Item> lootedItems = dun.lootRoom();
            test("38. Looted 1 item", lootedItems.size() == 1);
            test("39. Player ATK boosted by sword",
                    hero.getAttackPower() == 23);
            // Move to next room
            test("40. Move to next room succeeds",
                    dun.moveToNextRoom() == true);

            test("41. Current room is Throne Room", dun.getCurrentRoom().getName().equals("Throne Room"));

            // Cannot move past last room
            test("42. Cannot move past last room", dun.moveToNextRoom() == false);
            // Boss fight
            dun.combatRound();
            dun.combatRound();
            if (finalBoss.isAlive()) dun.combatRound();
            if (finalBoss.isAlive()) dun.combatRound();
            test("43. Boss defeated", finalBoss.isAlive() == false);
            test("44. Game is won", dun.isGameWon() == true);
            test("45. Game is not over (player alive)", dun.isGameOver() == false);
            // --- Edge: loot before clear ---
            Room locked = new Room("Locked Room");
            locked.addEnemy(new Goblin("Guard"));
            locked.addLoot(new HealthPotion("Big Potion", "Heals 50", 50));
            Dungeon dun2 = new Dungeon(new Player("Test", 100, 10));
            dun2.addRoom(locked);
            ArrayList<Item> earlyLoot = dun2.lootRoom();
            test("46. Cannot loot uncleared room",
                    earlyLoot.size() == 0);
            // --- Edge: combat in empty room ---
            Room empty = new Room("Empty Room");
            Dungeon dun3 = new Dungeon(
                    new Player("Test2", 100, 10));
            dun3.addRoom(empty);
            ArrayList<String> emptyLog = dun3.combatRound();
            test("47. Combat in empty room returns empty log",
                    emptyLog.size() == 0);
            // --- Edge: no rooms ---
            Dungeon dun4 = new Dungeon(
                    new Player("Test3", 100, 10));
            test("48. getCurrentRoom null when no rooms",
                    dun4.getCurrentRoom() == null);
            // --- Polymorphism checks ---
            Entity entityRef = new Goblin("Poly");
            test("49. Polymorphic getType via Entity ref",
                    entityRef.getType().equals("Goblin"));
            Lootable lootRef = new Sword("Poly Sword", "Test", 2);
            Player polyPlayer = new Player("PolyP", 50, 10);
            lootRef.use(polyPlayer);
            test("50. Lootable interface ref applies effect",
                    polyPlayer.getAttackPower() == 12);
            // --- Summary ---
            System.out.println("\n=== RESULTS: " + passed
                    + " passed, " + failed + " failed out of "
                    + (passed + failed) + " tests ===");
        }

    }

