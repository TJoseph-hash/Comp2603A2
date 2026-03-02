package org.A2;

import java.util.ArrayList;

public abstract class Player extends Entity{
    private ArrayList<Entity> inventory;

    public Player(String name, int health, int attackPower){
       super(name,health,attackPower);
       inventory = new ArrayList<>();
    }
 public String getType(){return "Player";}
 public ArrayList<Entity> getInventory(){
        return inventory;
 }
 public void addItem(Item item){

 }
}
