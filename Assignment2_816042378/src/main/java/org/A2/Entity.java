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

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "["+getType()+"] " + getName() +" (HP : " + getHealth() +", ATK: "+ getAttackPower() +")";
    }


}
