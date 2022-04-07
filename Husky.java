//Brooke Dietmeier
//12/5/2021
//CSE 142 AS
//TA: Eric Latham
//Assignment #10: Critters
//
//Client program classes that implement a graphical simulation of a 2D
//world of animals and other critters. Critters can infect other species 

import java.util.*;
import java.awt.*;

public class Husky extends Critter {
   private Random r;
   private boolean isHusky;
   
   //constructor method to initialize objects
   //initializes Random r and isHusky variables
   public Husky() {
      r = new Random(); //creates random variable 
      isHusky = true; //initializes boolean variable to true 
   }
   
   //client calls color method to set color of Husky 
   public Color getColor() {
      isHusky = !isHusky;
      //if critter is a husky, make it gray - else make it green 
      if (isHusky == true) {
         return(Color.GRAY);
      } else {
         return(Color.GREEN);
      }
   }
   
   //client calls Action method to make critter take certain 
   //action depending on criteria for that critter
   public Action getMove(CritterInfo info) {
     //if theres a enemy in front, turn left. If theres an enemy 
     //behind the critter, turn right. Otherwise hop 
      if (info.getFront() == Neighbor.OTHER) {
         return(Action.LEFT);
      } else if (info.getBack() == Neighbor.OTHER)  {
         return(Action.RIGHT);
      } else  {
         return(Action.HOP);
      }
   }
   
   //method that determines what text is displayed: 
   //Sko utes if random int is 3 else returns "H" to represent husky critter
   public String toString() {
      if (r.nextInt(5) == 3) {
         return("Sko Utes");
      } else {
         return("H");
      }
   }
}