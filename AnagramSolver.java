//Brooke Dietmeier 
//TA: Omar Ibrahim 
//2.24.22
//CSE 143: Assessment 6 -- Anagrams
//description: Uses a dictionary to determine all of the possible
//combinations of a word that forms an anagram given a word or phrase. 

import java.util.*; 

public class AnagramSolver {
   private List<String> wordsOrdered; //client input
   private Map<String, LetterInventory> letterBank; //word to corresponding letter

   //constructs AnagramSolver object for a given list of words that are the 
   //dictionary for determining the potential combinations of anagrams
   public AnagramSolver(List<String> dictionary) {
      wordsOrdered = dictionary; 
      letterBank = new HashMap<>(); 
      for(String word : dictionary) {
         letterBank.put(word, new LetterInventory(word)); 
      }
   }

   //Searches and prints out the combination of words from dictionary 
   //that have same letters as anagrams including at most "max" words. 
   //throws illegalArgumentException is max amount of words is < 0 
   public void print(String text, int max) {
      if(max < 0) {
         throw new IllegalArgumentException(); 
      }
      List<String> letterList = new ArrayList<String>(); 
      LetterInventory sorted = new LetterInventory(text); 
      
      LetterInventory inventory = new LetterInventory(); 
      List<String> list = new ArrayList<String>(); 
      for(String word: wordsOrdered) {
         if(inventory.subtract(letterBank.get(word)) != null) {
            list.add(word);
         }
      }    
      Stack<String> wordStack = new Stack<String>(); 
      printAnagram(sorted, max, letterList, wordStack);  
   }

   //Prints out the combinations of words from a given list in the inventory to produce
   //an anagram. Parameters: 
   //max - max # of words per anagram
   //inventory - supply of letters we are trying to determine from the anagram
   //wordList - list of words to form an anagram w/ given section of text
   //-potentialStack - list of words being considered when forming anagram 
   private void printAnagram(LetterInventory inventory, int max, List<String> wordList, Stack<String> potentialStack) {
      if(inventory.isEmpty()) {
         System.out.println(potentialStack);
      }
      if(max == 0 || max != wordList.size()) {
         for(String word : wordList) {
            LetterInventory updatedInventory = inventory.subtract(letterBank.get(word));
            if(updatedInventory != null) {
              potentialStack.push(word); 
              printAnagram(updatedInventory, max, wordList, potentialStack);
              potentialStack.pop();
              //potentialStack.remove(wordList.size() -1); 
            }
         }
      }
   }
   
}



