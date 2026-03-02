package org.A2;


    public class Goblin extends Enemy{
       public Goblin (String name){
           super(name,30,5);
       }
       @Override
        public String getType(){
           return "Goblin";
       }

    }



}
