package org.A2;

public abstract class  Entity {
    private final String name;
    private int health;
    private int attackPower;


    public Entity(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }


    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    boolean isAlive() {
        return getHealth() > 0;
    }

    public int takeDamage(int damage) {
        if (isAlive()) {
            System.out.println("player's health: "+ getHealth());
            System.out.println(damage);
            setHealth(getHealth() - damage);
        }
        return damage;
    }

    public abstract String getType();

    public String toString() {
        return "[ " + getType() +" ]" + getName() +"(HP : " + getHealth() +", ATK: "+ getAttackPower() +")";
    }

    public abstract String attack(Entity target);
}
