package org.A2;

public class Boss extends Enemy {
    public Boss(String name, int health, int attackPower){
        super(name,health,attackPower);
    }

    @Override
    public String getType(){
        return "Boss";
    }
    public String attack(Entity target){
        int damage = 2 * getAttackPower();
        target.setHealth(target.getHealth() - damage);
        return  getType() + " attacks " + target.getName() + " for " + damage + " damage";
    }
}
