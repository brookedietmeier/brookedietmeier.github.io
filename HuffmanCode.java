//Brooke Dietmeier
//TA: omar Ibrahim
//CSE 143E
//03.10.22
//Assessment 8: HuffmanCode
//HuffmanCode class is a data compression and decompression algorithm
//that utilizes David A. Huffman's encoding algorithm. Class reduces a file
//size by minimizing number of bits used per ASCII character stored in the
//file based on how often that character appears in the file.

import java.util.*; 
import java.io.*; 

public class HuffmanCode {
   private HuffmanNode huffmanTree; 
   
   //constructs a huffmanCode object using a given array of frequencies
   //stores the count of each ASCII character with the ASCII value represented
   //by the index in array. Stores binary tree representing the Huffman code
   //for use in compression and decompression
   //parameter frequencies - array that stores value at each index to represent 
   //count of each ASCII 
   public HuffmanCode(int[] frequencies) {
      PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(); 
      for(int i = 0; i < frequencies.length; i++) {
         if(frequencies[i] > 0) {
            pq.add(new HuffmanNode(i, frequencies[i])); 
         }
      }
      while (pq.size() > 1) {
         HuffmanNode first = pq.remove(); 
         HuffmanNode second = pq.remove(); 
         int sumFrequency = first.frequency + second.frequency; 
         HuffmanNode merged = new HuffmanNode(-1, sumFrequency, first, second); 
         pq.add(merged); 
      }
      huffmanTree = pq.remove(); 
   }
   
   //constructs a HuffmanCode object by reading in a previously constructed Huffman
   //code from a given code file written in standard format. 
   //paramter input - Scanner that holds the file of Huffman Code
   public HuffmanCode(Scanner input) {
      while(input.hasNextLine()) {
         int symbol = Integer.parseInt(input.nextLine()); 
         String code = input.nextLine(); 
         huffmanTree = treeBuilder(huffmanTree, symbol, code); 
      }
   }
   
   //recursively builds binary tree representing Huffman Code
   //reads in from a .code file and returns the HuffmanNode storing the entire Huffman code
   //from given file as a binary tree
   //parameter tracker: root HuffmanNode of binary tree being built to store Huffman Code
   //parameter symbol: current ASCII value being stored in binary tree
   //parameter code: Huffman code for symbol to determine location of symbol in tree
   private HuffmanNode treeBuilder(HuffmanNode tracker, int symbol, String code) {
      if (tracker == null) {
         tracker = new HuffmanNode(-1); 
      }
      if (code.length() == 1) {
         if (code.equals("0")) {
            tracker.left = new HuffmanNode(symbol); 
         } else {
            tracker.right = new HuffmanNode(symbol); 
         }
      } else {
         char value = code.charAt(0); 
         code = code.substring(1); 
         if (value == '0') {
            tracker.left = treeBuilder(tracker.left, symbol, code); 
         } else {
            tracker.right = treeBuilder(tracker.right, symbol, code);
         }
      }
      return tracker; 
   }
   
   //stores current Huffman code to an output stream in standard format
   //standard format has the ASCII value on the first line, Huffman code on
   //second line and repeats format to rest of output
   //parameter output: printStream where current Huffman code is stored
   public void save(PrintStream output) {
      if(huffmanTree != null) {
         save(huffmanTree, "", output); 
      }
   }
   
   //helper method that uses pre-order traversal to traverse through binary tree
   //storing the Huffman code.Stores ASCII value and Huffman code corresponding
   //to it in output stream for every ASCII value in binary tree
   //parameter tracker: huffmanNode of binary tree being look at to build Huffman code
   //for an ASCII value stored in a HuffmanNode within tree
   //parameter code: stores huffman code being built for ASCII value in binary tree
   private void save(HuffmanNode tracker, String code, PrintStream output) {
      if(tracker.left == null && tracker.right == null) {
         output.println(tracker.symbol); 
         output.println(code); 
      } else {
         save(tracker.left, code + "0", output); 
         save(tracker.right, code + "1", output); 
      }
   }
   
   //reads in individual bits from given bit stream and writes corresponding
   //decompressed character to given output Stream
   //Paramter input: given input bit stream to read individual bits until
   //there are no more bits to read
   //parameter output: PrintStream where decompressed message determined from input
   //stream is written to
   public void translate(BitInputStream input, PrintStream output) {
      HuffmanNode tracker = huffmanTree; 
      while(input.hasNextBit()) {
         while(tracker.left != null && tracker.right != null) {
            if(input.nextBit() == 0) {
               tracker = tracker.left; 
            } else {
               tracker = tracker.right; 
            }
         }
         output.write(tracker.symbol); 
         tracker = huffmanTree; 
      }
   }  
   
   //class that inherits Comparable class and represents single node withing Huffman Code binary tree.
   //Each node contains a symbol and frequency of that character in ASCII
   private static class HuffmanNode implements Comparable<HuffmanNode> {
      public int symbol; 
      public int frequency; 
      public HuffmanNode left;
      public HuffmanNode right; 
      
      //constructs HuffmanNode object representing single node in binary tree
      //parameter symbol: ASCII value representing character for node
      //parameter frequency: count of symbol
      //parameter left: HuffmanNode object representing next left node in tree
      //parameter right: HuffmanNode object representing next right node in tree
      public HuffmanNode(int symbol, int frequency, HuffmanNode left, HuffmanNode right) {
         this.symbol = symbol; 
         this.frequency = frequency; 
         this.left = left; 
         this.right = right; 
      } 
      
      //Constructs huffmanNode object by calling first constructor and setting left and 
      //right nodes to null 
      public HuffmanNode(int symbol, int frequency) {
         this(symbol, frequency, null, null); 
      }
      
      //constructs HuffmanNode object by calling first constructor and setting frequency to 0,
      //and left and right nodes to null. 
      public HuffmanNode(int symbol) {
         this(symbol, 0, null, null); 
      }
      
      //returns difference between frequency of HuffmanNode and frequency of given "other: HuffmanNode
      public int compareTo(HuffmanNode other) {
         return this.frequency - other.frequency; 
      }
   }
}
        
       