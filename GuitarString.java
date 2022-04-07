//Brooke Dietmeier
//01/19/2022
//CSE 143E
//TA: Omar Ibrahim
//Assessment 2: GuitarHero

//GuitarString class models a vibrating guitar string
//at a given frequency by client/user

import java.util.*; //imports java package

public class GuitarString {
   private Queue<Double> ringBuffer; //stores sound data
   int capacityN;
   double frequency;
   public static final double ENERGY_DECAY = 0.996; //Energy decay factor
   
   //constructor
   //pre: frequency <= 0 & result N > 2 (throws IllegalArgumentException)
   //post: creates a ring buffer with N zeros enqueued.
   public GuitarString(double frequency) {
      //size of buffer and casts result to an int
      this.frequency = frequency;
      capacityN = (int) (Math.round(StdAudio.SAMPLE_RATE/ frequency));       
       
      if (frequency <= 0 || capacityN < 2) {
         throw new IllegalArgumentException();
      }
       
      ringBuffer = new LinkedList<Double>(); //create new queue  
      for(int i = 0; i < capacityN; i++) { //represent guitar string at rest
         ringBuffer.add(0.0); //queue element is a double
      }
   }
   
   //Constructor - intializes contents of ring buffer to values in array
   //pre: array of doubles represent string (parameter). Size of array >= 2
   //throws IllegalArgument exception
   //post: creates a ring buffer with initialized values to array
   public GuitarString(double[] init) {
      if (init.length < 2) {  
         throw new IllegalArgumentException();
      }
    
      ringBuffer = new LinkedList<Double>(); 
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
         //adds contents of array to ring Buffer queue 
      }
      
   }
   
   //Replaces the N elements in ring buffer with N random
   //values between -0.5 <= value < 0.5
   public void pluck() {
      for(int i =0; i < capacityN; i++) {
         //rand values: -0.5 inclusive and 0.5 exclusive
         double random = Math.random() - 0.5;
         ringBuffer.add(random);
         ringBuffer.remove();
      }
   }
   
   //Applies Karplus-Strong update once to ring buffer
   public void tic() {
      double sample1 = 0;
      double sample2 = 0;
      //removes front value and calculates a new value to back of ring buffer
      
      sample1 = ringBuffer.peek(); 
      ringBuffer.remove(); 
      sample2 = ringBuffer.peek(); //views but doesn't remove first value in queue
      double karStrong = ENERGY_DECAY * ((sample1 + sample2) / 2.0);
      //add average to end of queue
      ringBuffer.add(karStrong);
   }
   
   //Returns current sample (front value of ring buffer)
   public double sample() {
      return ringBuffer.peek();
   }
}