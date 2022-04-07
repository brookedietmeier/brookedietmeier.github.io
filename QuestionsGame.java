//Brooke Dietmeier 
//3-1-2022
//CSE 143E TA: Omar Ibrahim 
//Assessment 7: 20 questions 
//QuestionsGame class:  
//Guessing game - representing 20 questions game - in which one player 
//chooses a secret object and the other player asks yes/no questions to try to 
//identify chosen object. The user will be the chooser and begin the round by choosing
//an object. Computer will be guesser and attempt to guess object. If 
//computer guesses correctly, computer wins, else user wins. 

import java.util.*;
import java.io.*; 

public class QuestionsGame {
   private QuestionNode overallRoot; 
   private Scanner console; 
   //Initializes new QuestionsGame object 
   //constructs question tree with one answer node 
   public QuestionsGame() {
      overallRoot = new QuestionNode("computer"); 
      console = new Scanner(System.in); 
   }
   
   //replaces current tree by reading another tree
   //passes scanner as parameter to replace current tree w/
   //new tree 
   public void read(Scanner input) {
      overallRoot = readHelp(input); 
   }
   
   //creates new tree for game by reading given file 
   //parameter passes scanner to link file with new tree of questions 
   //returns a QuestionNode based on next line of file based on whether
   //each line is a question or a guess(answer) 
   private QuestionNode readHelp(Scanner input) {
      if(input.nextLine().equals("Q:")) {
         return new QuestionNode(input.nextLine(), readHelp(input), readHelp(input)); 
      } else {
         return new QuestionNode(input.nextLine()); 
      }
   } 
   
   //Stores current question tree to output file 
   //passes PrintStream as parameter to allow use of answer in another game
   public void write(PrintStream output) {
      writeHelp(output, overallRoot); 
   }
   
   //builds output PrintStream file storing question tree in pre-order traversal
   //parameter passes PrintStream to where file will be stored 
   //parameter passes QuestionNode in current tree where writing file begins
   private void writeHelp(PrintStream output, QuestionNode root) {
      if(root.left != null || root.right != null) {
         output.println("Q:\n" + root.question); //prints out question first
         writeHelp(output, root.left); //moves to left side
         writeHelp(output, root.right); //moves to right side
      } else {
         output.println("A:\n" + root.question); //prints out answer if computer guesses yes
      }
   }
   
   //Uses current question tree to play a complete game w/ user
   //asking yes/no questions until reaching correct answer
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot); 
   }
   
   //asks user yes/no question to find user's secret object. If computer guesses correctly,
   //computer wins. If computer guesses incorrectly, computer asks user what secret object was
   //a yes/no question is used to discover the secret object from the computer's guess and what 
   //the answer to yes/no question is. 
   //parameter passes QuestionNode to represent first question asked by computer 
   //returns QuestionNode that is the root Node of the current tree being used
   private QuestionNode askQuestions(QuestionNode root) {
      if(root.left != null || root.right != null) {
         if(yesTo(root.question)) {
            root.left = askQuestions(root.left); 
         } else {
               root.right = askQuestions(root.right); 
         }
      } else {
         if(yesTo("Would your object happen to be " + root.question + "?")) {
            System.out.println("Great, I got it right!"); 
         } else {
            System.out.println("What is the name of your object?"); 
            String object = console.nextLine(); 
            System.out.println("Please give me a yes/no question that"); 
            System.out.println("distinguishes between your object\nand mine"); 
            String question = console.nextLine(); 
            if(yesTo("And what is the answer for your object?")) {
               root = new QuestionNode(question, new QuestionNode(object), root); 
            } else {
               root = new QuestionNode(question, root, new QuestionNode(object)); 
            }
         }
      }
      return root; 
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //returns true if the answer was yes, returns false otherwise
    private boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
   
      
   //class implements binary tree program based yes(left) and no(right) responses
   //from computer. Represents a single node of the binary tree.
   private static class QuestionNode {
      public String question; //data held in node representing question 
      public QuestionNode left; //yes response
      public QuestionNode right; //no response
      
      //constructs leaf node given question 
      //creates node that stores computer answer to question
      public QuestionNode(String question) {
         this.question = question; 
         left = null; 
         right = null;  
      }
      
      //constructs actual branch node in accordance to response to question
      //creates right and left subtrees
      public QuestionNode(String question, QuestionNode left, QuestionNode right) {
         this.left = left; 
         this.right = right; 
         this.question = question;       
      }
   }
}
