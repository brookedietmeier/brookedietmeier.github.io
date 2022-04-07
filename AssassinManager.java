//Brooke Dietmeier
//1/28/2022
//CSE 143E
//TA: Omar Ibrahim
//Assessment 3: AssassinManager
//Manages the "Assassin" game. Takes in list of players that become part of the Kill Ring
//Each player is stalking another player (after them on a list)
//Once player is killed by player behind them, they are moved to the graveyard
//game is over when there is only one player remaining in the kill ring
//last person in kill ring is the winner

import java.util.*;

public class AssassinManager {
   //stores kill ring and graveyard linkedLists
   private AssassinNode killRing; //people still in kill Ring
   private AssassinNode graveYard; //people who have died
   
   
   //Constructor-
   //pre: list of names in linkedList
   //post: builds a kill ring of connected assassin nodes
   //takes list of strings as parameter
   //If list is empty, IllegalArgumentException is thrown
   public AssassinManager(List<String> names) {
      if (names == null || names.size() == 0) {
         throw new IllegalArgumentException();
      }
      
      for (int i = names.size() - 1; i >= 0;i--) {
         killRing = new AssassinNode(names.get(i),killRing);
      }     
      graveYard = new AssassinNode("");
   }
   
   //prints names of people in kill ring and who they are stalking
   //<name1> is stalking <name2>
   //if only one person is left in kill ring: <name1> is stalking <name1>
   public void printKillRing() {
      AssassinNode curr = killRing;
      while(curr != null) {
         if(curr.next == null) {
            System.out.println("    " + curr.name + " is stalking " + killRing.name);
         } else {
            System.out.println("    " + curr.name + " is stalking " + curr.next.name);
         }
      curr = curr.next;       
      }     
   }
   
   //prints names of people in the graveyard who have been assassinated
   //"X was killed by Y" -- prints names in opposite order in which they
   //were assassinated
   //produces no output if graveyard is empty
   public void printGraveyard() {
      AssassinNode grave = graveYard;
      while (grave != null && grave.next != null) {
         System.out.println("    " + grave.name + " was killed by " + grave.killer);
         grave = grave.next;
      }
   }
   
   //checks the kill ring to see if it contains given name
   //returns true/false if name is/isn't in Kill ring
   //takes string Name as parameter to check for given name in Kill Ring
   public boolean killRingContains(String name) {
      AssassinNode curr = killRing;
      while (curr != null) {
         if(curr.name.equalsIgnoreCase(name)) {
            return true;
         }
         curr = curr.next;
      }
      return false;
   }
   
   //checks graveyard to see if it contains given name
   //returns true/false if name is/isnt' in graveyard
   //takes string Name as parameter to check for given name in graveyard
   public boolean graveyardContains(String name) {
      AssassinNode grave = graveYard;
      while (grave != null) {
         if(grave.name.equalsIgnoreCase(name)) {
            return true;
         }
         grave = grave.next;
      }
      return false;
   }
   
   //if one person is left in kill ring, game is over
   //returns true/false if there is only one person left
   public boolean gameOver() {
      if(killRing.next == null) {
         return true;
      } else {
         return false;
      }
   }
   
   //last name in kill ring is the winner
   //name of winner is returned
   public String winner() {
      if(!gameOver()) {
         return null;
      }
      return killRing.name;
   }
   
   //pre: if game isn't over, checks to see if kill ring contains given name
   //post: removes assassinated player from kill ring and records name of their killer
   //moves the assassinated player to the front of the graveyard
   //throws illegalArgumentException if given name isn't in kill ring
   //throws IllegalStateException if game is over
   //if both conditions are true, IllegalStateException is thrown
   //takes String as parameter to check for given name in kill ring
   public void kill(String name) {
      if(!killRingContains(name)) {
         throw new IllegalArgumentException();
      }else if(gameOver()){
         throw new IllegalStateException();
      }
      
      AssassinNode curr = killRing;
      AssassinNode previousNode = null;
      if (curr.name.equalsIgnoreCase(name)) {
         previousNode = curr;
         killRing = killRing.next;
         while(previousNode.next != null) {
            previousNode = previousNode.next;
         }
      } else {
         while(!curr.name.equalsIgnoreCase(name)) {
            previousNode = curr;
            curr = curr.next;
         }
         previousNode.next = curr.next;
      }
      
      curr.killer = previousNode.name;
      curr.next = graveYard;
      graveYard = curr; 
   }   
}