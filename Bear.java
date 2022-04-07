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

public class Bear extends Critter {
   private boolean onForward; //creates boolean variables 
   private boolean isPolar;
   
   //constructor method to initialize objects
   //initializes onForward and isPolar variables
   //sets parameter to boolean polar to initialize attribute 
   public Bear(boolean polar) {
      onForward = true; //initializes onForward variable 
      isPolar = polar; //sets boolean variable to polar
   }
   
   //client calls color method to find color of Bear 
   //depending on whether bear is polar or not 
   public Color getColor() {
      if (this.isPolar == true ) {
         return(Color.WHITE); //if polar is true, return white bear
      } else {
         return(Color.BLACK); //if polar is false, return black bear 
      }
   }
   
   //client calls Action method to make critter take certain 
   //action depending on criteria for that critter
   public Action getMove(CritterInfo info) {
      onForward = !onForward;
      //infect an enemy when in front, hop if empty in front, else turn left
      if (info.getFront() == Neighbor.OTHER) {
         return(Action.INFECT);
      } else if (info.getFront() == Neighbor.EMPTY) {
         return(Action.HOP);
      } else {
         return(Action.LEFT);
      }
   }
   
   //method that determines what text is displayed: 
   //backslash and forward slashes represent bear critter 
   public String toString() {
      if (onForward) {
         return("/");
      } else {
         return("\\");
      }
   }  
}