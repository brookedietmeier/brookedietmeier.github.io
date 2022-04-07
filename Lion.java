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

public class Lion extends Critter {
   int counter; //creates variables 
   Color currentColor;
   private Random randomColor; // creates random object
   
   //constructor method to initialize objects
   //initializes counter and currentColor variables
   public Lion () {
      randomColor = new Random(); //initializes random object 
      counter = 1;
      int i = (int)(randomColor.nextInt(3)); //randomly chooses color 
      //randomColor.nextInt(3) --> randomly selects between the three different color options
      if (i == 0) {
         currentColor = Color.RED;
      } else if (i == 1) {
         currentColor = Color.GREEN;
      } else {
         currentColor = Color.BLUE;
      }
   }
   
   //client calls color method to set color of Lion 
   //prints out the color determined by Action method 
   public Color getColor() {
      return(currentColor);
   }
   
   //client calls Action method to make critter take certain 
   //action depending on criteria for that critter
   public Action getMove(CritterInfo info) {
     //randomly picks red, green or blue color and uses color for 3 moves
      if (counter < 3) {
         counter++;
      } else {
         int i = (int)(Math.random()*3);
         if (i == 0) {
            currentColor = Color.RED;
         } else if (i == 1) {
            currentColor = Color.GREEN;
         } else {
            currentColor = Color.BLUE;
         }
         counter = 1;
      }
      
      //infects enememy when enemy is in front, if theres a wall it turns left
      //if fellow lion is in front, turns right: otherwise hops 
      if (info.getFront() == Neighbor.OTHER) {
         return(Action.INFECT);
      } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
         return(Action.LEFT);
      } else if (info.getFront() == Neighbor.SAME) {
         return(Action.RIGHT);
      } else {
         return(Action.HOP);
      }
   }
   
   //returns variable response once it runs through Action method
   //determines that an "L" is displayed 
   public String toString() {
      return("L");
   }
}