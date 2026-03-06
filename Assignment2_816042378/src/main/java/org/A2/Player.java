package org.A2;

import java.util.ArrayList;

public class Player extends Entity {

    private final ArrayList<Item> inventory;

    // Constructor
    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
        inventory = new ArrayList<>();
    }

    // getType
    @Override
    public String getType() {
        return "Player";
    }

    // getInventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // addItem
    public void addItem(Item item) {
        inventory.add(item);

        if (item instanceof Lootable) {
            ((Lootable) item).use(this);
        }
    }

    // getInventorySize
    public int getInventorySize() {
        return inventory.size();
    }
    @Override
   public String toString(){
        return "[" + this.getType() +"] "  + this.getName() +  " (HP: " +this.getHealth() +", ATK: "+ this.getAttackPower() + ")";
   }
    // attack
    public String attack(Entity target) {
        target.takeDamage(this.getAttackPower());

        return this.getName() + " attacks " + target.getName() + " for " + this.getAttackPower() + " damage.";
    }
}