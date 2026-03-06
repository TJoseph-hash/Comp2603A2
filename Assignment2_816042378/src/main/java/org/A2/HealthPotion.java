package org.A2;

public class HealthPotion extends Item implements Lootable {

    private int healAmount;

    public HealthPotion(String name, String description, int healAmount) {
        super(name,description);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public String toString() {
        return  this.getName() + ": " + this.getDescription();
    }
    @Override
    public void use(Player player) {
        player.setHealth(player.getHealth() + healAmount);
    }
}
