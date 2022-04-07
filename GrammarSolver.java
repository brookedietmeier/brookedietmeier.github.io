//Brooke Dietmeier
//2.7.2022
//CSE 143E
//TA: Omar Ibrahim
//Assessment 5: Grammar Solver
//Description: Generates random sentences from a set of specially-formatted
//rules. These rules are called a grammar and are used to define a language
//grammar is written in Backus-Naur form.

import java.util.*;

public class GrammarSolver {
   private SortedMap<String, List<String>> grammar;
   //SortedMap<Key,value> --> <nonterminals, terminals>
   
   //Initializes a new grammar with consideration of BNF
   //rules where each rule corresponds to one line of text
   //throws new IllegalArgumentException if list is empty or if
   //there are two or more entries in the grammar for the same non-terminal
   public GrammarSolver (List<String> rules) {
      if(rules.isEmpty()) {
         throw new IllegalArgumentException();
      }
      grammar = new TreeMap<String, List<String>>();
      
      for(String stuff : rules) {
         String[] separate = stuff.split("::=");
         String key = separate[0];
         if(grammar.containsKey(key)) {
            throw new IllegalArgumentException();
         }
         
         String[] parts = separate[1].split("\\|");
         List<String> terminals = new ArrayList<>();
         
         for (String part : parts) {
            part = part.trim();
            terminals.add(part);
         }
         grammar.put(key, terminals);
      }
      
   }
   
   //Method states if symbol is a non-terminal in the grammar
   //passes in a String symbol to check if it is a non-terminal
   public boolean grammarContains(String symbol) {
      return grammar.containsKey(symbol);
      
   }
   
   //Method gives sorted list of nonterminal symbols from grammar
   public String getSymbols() {
      return(grammar.keySet().toString());
   }
   
   //Determines the number of times a random occurence of a given
   //symbol is found in the grammar. Returns a sentence using the
   //non-terminal rules assigned within grammar structure.
   //Throws illegalArgumentException if string passed is not a non-terminal
   //throws illegalArgumentException if times is negative
   public String[] generate(String symbol, int times) {
      if(times < 0 || !grammarContains(symbol)) {
         throw new IllegalArgumentException();
      }
      String[] returnStrings = new String[times];
      for (int t = 0; t < times; t++) {
         returnStrings[t] = theGenerate(symbol);
      }
      return(returnStrings);
   }
   
   //creates a list of String arrays within the grammar map
   //to generate the random occurences of a given symbol
   //returns string of non-terminal symbols from grammar
   //uses recursive method call to generate a sentence from passed
   //in symbols and non-terminal rules.
   private String theGenerate(String symbol) {
      List<String> terminal = new ArrayList<String>();
      terminal = grammar.get(symbol);
      Random r = new Random();
      int occurence = r.nextInt(terminal.size());
      String selection = terminal.get(occurence);
      
      if (grammar.containsKey(selection)) {
         return theGenerate(selection).trim();
      } else if (!selection.contains(" ")) {
         return (selection.trim());
      } else {
         String returnString= "";
         String[] pieces = selection.split("\\s+");
         for(String piece : pieces) {
            returnString += theGenerate(piece.trim()) + " ";
         }
         return(returnString);
      }
   }
}