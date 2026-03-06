package org.A2;

public class Sword extends Item implements Lootable {

    private int bonusDamage;

    // Constructor
    public Sword(String name, String description, int bonusDamage) {
        super(name, description);
        this.bonusDamage = bonusDamage;
    }

    // getBonusDamage
    public int getBonusDamage() {
        return bonusDamage;
    }

    // use method from Lootable
    @Override
    public void use(Player player) {
        player.setAttackPower(player.getAttackPower() + bonusDamage);
    }
}
