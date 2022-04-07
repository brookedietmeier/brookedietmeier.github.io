//Models guitar with 37 different strings

public class Guitar37 implements Guitar {
   // keyboard layout
   private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   private static final int STRINGS = 37; //amount of guitar strings
   private GuitarString[] guitarStringArray; //array to keep track of strings
   private int ticTimes; //record amount of times tic is called
   
   //constructs Guitar37 object and creates array of 37 guitar strings
   public Guitar37() {
      int ticTimes = 0;
      double concertA = 440.0;
      guitarStringArray = new GuitarString[STRINGS];
      for (int i = 0; i < STRINGS; i++) {
         guitarStringArray[i] = new GuitarString(concertA * Math.pow(2, (i-24)/12.0));
      }
   }
   
   //plucks the string that has the matching pitch
   //client can specify exactly which note to play
   public void playNote(int pitch) {
      int indexPitch = pitch + 24; //converts pitch value to index in string
      if(indexPitch < STRINGS && indexPitch >= 0) {
         guitarStringArray[indexPitch].pluck();
      }
   }
   
   //returns true if string played is contained
   //returns false if string played isn't contained
   public boolean hasString(char string) {
      if (KEYBOARD.indexOf(string) == -1) {
         return false;
      } else {
         return true;
      }
   }
   
   //client can also specify a character that indicates which note to play
   //according to the index at that string
   //throws illegalArgumentException if press key isn't part of keyboard
   public void pluck(char string) {
      if(!this.hasString(string)) {
         throw new IllegalArgumentException();
      }
      int num = KEYBOARD.indexOf(string);
      playNote(num); //corresponds string with index
   }
   
   
   //returns sum of all strings together by taking the
   //first value in each String played and adding it to the sum
   //loops through array to find sum of all 37 samples
   public double sample() {
      double sum = 0;
      for(int i = 0; i < STRINGS; i++) { //includes all 37 samples
         sum += guitarStringArray[i].sample();
      }
      return sum; //returns sum of strings played
   }
   
   //applies Karplus-Strong algorithm to every string once
   //advanced the time one tic
   public void tic() {
      for(int i = 0; i < STRINGS; i++) {
         guitarStringArray[i].tic();
      }
      ticTimes++; //adds one to tic variable every time
   }
   
   //determines the current time
   //time = number of times tic has been called
   public int time() {
      return ticTimes;
   }
}