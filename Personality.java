//Brooke Dietmeier
//11/15/2021
//CSE 142 AS
//TA: Eric Latham
//Assignment #7: Personality Test
//
//This program will process an input file containing the results of a personality test
//for a number of people and determine each person's Keirsey personality type.
//The program will begin by printing a short introduction, and then asking the user
//for an input file to read and an output file to print results to.
//The program will then read the given input file and process the responses to
//the personality test in that file, printing the results to the specified output file.

import java.util.*;
import java.io.*;

public class Personality {
   public static final int PERSONALITY_DIMENSIONS = 4; //sets final variable used below
   //"table of contents" method, calls methods in correct order
   //throws FileNotFoundException so program can run if incorrect file is entered
   public static void main(String[] args) throws FileNotFoundException {
      
      Scanner console = new Scanner(System.in);
      intro();
      
      System.out.print("input file name? ");
      File inFile = new File(console.nextLine());
      Scanner inputName = new Scanner(inFile);
      //creates input file and sets equal to user selected name
      
      System.out.print("output file name? ");
      // output file that user creates
      PrintStream output = new PrintStream(console.nextLine());
      
      //while input file has another line, run through while loop
      //creates arrays and calls methods
      while(inputName.hasNextLine()) {
         String name = inputName.nextLine();
         output.print(name + ": ");
         String userAnswer = inputName.nextLine().toLowerCase();
         int [] answerA = new int[PERSONALITY_DIMENSIONS];
         int [] answerB = new int[PERSONALITY_DIMENSIONS];
         answerAB(userAnswer, answerA, answerB);
         int [] percentB = bPercentageCalculation(output, answerA, answerB);
         printPersonalityType(output, percentB);
      }
   }
   
   //introductory method that tells user what program does
   public static void intro() {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter. It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      System.out.println();
   }
   
   //counts how many A's and B's in the input file per user and calculates values of A's and B'S
   //parameter: calls string variable to go through user answers, and arrays of A and B answers
   //int [] AnswerA and int[] answerB inputs answers back into arrays
   public static void answerAB (String userAnswer, int[] answerA, int[] answerB) {
      //runs through for loop, finds length of string to check if string is empty or not
      for(int i = 0; i < userAnswer.length(); i++) {
         //nested if statement that calculates values of A and B answers
         if(userAnswer.charAt(i) == 'a') {
            int increaseA = (i % 7 + 1);
            answerA[(increaseA/2)]++;
            //adds to variable answerA if userAnswer is 'a'
         } else if (userAnswer.charAt(i) == 'b') {
            int increaseB = (i % 7 + 1);
            answerB[(increaseB/2)]++;
            //adds to variable answerB if userAnswer is 'b'
         }
      }
   }
   
   //method to calculate the percentage of B answers out of total amount of answers
   //Parameters: printstream to print out B percentages into output file,
   //passes int[] answerA and answerB to calculate the total and b percentages
   public static int[] bPercentageCalculation(PrintStream output, int[] answerA, int[] answerB) {
      int[] bPercentArr = new int[PERSONALITY_DIMENSIONS];
      int total = 0; //initializes total variable
      double percent = 0.0; //initializes percent variable
      //finds length of array, calculates total of answers and compares to amount of b answers
      for (int i = 0; i < bPercentArr.length; i++) {
         total = answerA[i] + answerB[i]; //calculates total
         percent = (((double)answerB[i] / total) * 100); //turns into percentage
         bPercentArr[i] = (int) Math.round(percent); //rounds to correct decimal
      }
      
      output.print(Arrays.toString(bPercentArr) + " = "); //prints b perc array to output file
      return bPercentArr; //return the array of B percentages for each personality type
   }
   
   //prints personality type to output file by calling printsteam as parameter
   //parameter: int [] bPercent to determine if value is greater, less than or equal to 50
   //correlates bPercent array to personality type
   public static void printPersonalityType(PrintStream output, int[] percentB) {
      char[] types = {'E', 'S', 'T', 'J', 'I', 'N', 'F', 'P', 'X'};
      //array of personality dimensions or "types"
      String dimensions = ""; //initizializes variable
      //as long as i is less than 4, see if percentB array is greater, less than, or equal to 50
      for(int i = 0; i < PERSONALITY_DIMENSIONS; i++){
         if(percentB[i] < 50) {
            dimensions += types[i];
         } else if(percentB[i] > 50) {
            dimensions += types[i + (types.length/2)];
         } else {
            dimensions += types[types.length - 1];
         }
      }
      output.print(dimensions); //prints out personality type to output file
      output.println();
   }
}