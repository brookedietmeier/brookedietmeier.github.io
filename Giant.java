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

public class Giant extends Critter {
   private int counter; //creates private variables
   private String response;
   
   //constructor method to initialize objects
   //initializes response and counter variables
   public Giant () {
      counter = 1;
      response  = "fee";
   }
   
   //client calls color method to set color of Giant 
   public Color getColor() {
      return(Color.GRAY);
   }
   
   //client calls Action method to make critter take certain 
   //action depending on criteria for that critter
   public Action getMove(CritterInfo info) {
     //prints out fee for 6 moves, fie for 6 movies, foe for 6 moves
     //fum for 6 moves and then repeats 
      if (counter < 6) {
         counter++;
      } else {
         if (response.equalsIgnoreCase("fee")) {
            response = "fie";
            counter = 1;
         } else if (response.equalsIgnoreCase("fie")) {
            response = "foe";
            counter = 1;
         } else if (response.equalsIgnoreCase("foe")) {
            response = "fum";
            counter = 1;
         } else if (response.equalsIgnoreCase("fum")) {
            response = "fee";
            counter = 1;
         }
      }
      //infects an enemy when enemy is in front of critter
      //hops if space in front is empty, turns right otherwise
      if (info.getFront() == Neighbor.OTHER) {
         return(Action.INFECT);
      } else if (info.getFront() == Neighbor.EMPTY) {
         return(Action.HOP);
      } else {
         return(Action.RIGHT);
      }
   }
   
   //returns variable response once it runs through Action method
   //determines what text is displayed
   public String toString() {
      return(response);
   }
}