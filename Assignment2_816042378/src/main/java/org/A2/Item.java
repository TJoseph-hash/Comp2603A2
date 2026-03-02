package org.A2;

public abstract class Item {
     private  final String name;
     private final String  description;

     public Item(String name, String description, int healAmount){
         this.name = name;
         this.description= description;
     }
     public String getName(){return name;}
     public String description(){return description;}

    interface Lootable{
         void use(Player player);
    }

    public static class HealthPotions extends Item{
         private int healAmount;

        public HealthPotions(String name, String description,int healAmount) {
            super(name, description,healAmount);
        }
        public int getHealAmount(){return healAmount;}

        public void use(Player player){
            player.setHealth(player.getHealth() + getHealAmount());
        }
    }

    public static class swords extends Item {
        private int bonusDamage;

        public swords(String name, String description, int bonusDamage) {
            super(name, description, bonusDamage);
        }

        public int getBonusDamage() {
            return bonusDamage;
        }

        public void use(Player player) {
            player.setAttackPower(player.getAttackPower() + getBonusDamage());
        }

        public boolean moveToNextRoom() {
            int currentRoomIndex = 0;
             if()
        }
    }
}
