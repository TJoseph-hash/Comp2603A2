package org.A2;

public class Boss extends Enemy {

    public Boss(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public String getType() {
        return "Boss";
    }

    @Override
    public String toString(){
        return "[" + this.getType() +"] "  + this.getName() +  " (HP: " +this.getHealth() +", ATK: "+ this.getAttackPower() + ")";
    }

    @Override
    public String attack(Entity target) {
        int damage = getAttackPower() * 2;
        target.takeDamage(damage);
        return this.getName() + " attacks " + target.getName() + " for " + damage + " damage.";
    }
}