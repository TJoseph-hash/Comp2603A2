package org.A2;

public abstract class Enemy extends Entity {

    public Enemy(String name , int health, int attackPower){
        super(name,health,attackPower);
    }

    @Override
    public String getType(){return "Enemy";}

    @Override
    public String attack (Entity target) {
        target.setHealth(target.getHealth() - getAttackPower());
        return this.getName() + " attacks " + target.getName() +
                " for " + target.getAttackPower() + " damage.";
    }
}

