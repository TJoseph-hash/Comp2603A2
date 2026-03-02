package org.A2;

import java.util.ArrayList;

public class Room {
     private String name;
     private ArrayList<Enemy> enemies;
     private ArrayList<Item> loot;

     public Room(String name, ArrayList<Enemy> enemies, ArrayList<Item> loot){
         this.name = name;
         this.enemies = new ArrayList<>();
         this.loot= new ArrayList<>();
     }
     public String getName(){return name;}
     public void addEnemy(Enemy enemy){enemies.add(enemy);}
     public void addloot(Item item){loot.add(item);}
     public ArrayList<Enemy> getEnemies(){return enemies;}
     public ArrayList<Item> getLoot(){return loot;}
     public ArrayList<Enemy> getAliveEnemies(){
         ArrayList<Enemy> AliveEnemies = new ArrayList<>();
          for(Enemy enemy : enemies){
              if(enemy.isAlive()){
                  AliveEnemies.add(enemy);
              }
          }
          return AliveEnemies;
     }
     public boolean isCleared(){
         for(Enemy enemy : enemies){
             if(enemy.isAlive())
                 return false;
         }
          return true;
     }
     public String toString(){
         return  "Room: " + getName() + "[ Enemies: "+ getEnemies() +" Loot: " + getLoot()+ "]";
     }


     
}
