/*
 * Name: Nicholas Tao and Rohan Ravindran
 * Date: March 4, 2019
 * Assignment Name: Code Breaker
 */
import java.util.*;
public class CodeBreaker {
  
  public static void main (String [] args) {
    final int SIZE = 4;
    final String VALID_CHARS = "GRBYOP";
    String [ ] code = new String [SIZE];
    String [] guess = {"R","G", "B", "Y"};
    
    
    createCode(VALID_CHARS, SIZE, code);
    //userInput();
    //valid(); (use do-while to keep calling if invalid input)
    findFullyCorrect (code, guess);
    removeFullyCorrect (code, guess);
    //findColourCorrect (code, guess);
    //displayGame();
    
  }
  
  public static String[] createCode (String colours, int size, String[] code) {
    Random ran = new Random ();
    
    for (int i = 0; i < size; i++) {
      int temp = ran.nextInt(size-1);
      char c = colours.charAt(temp);
      String s = String.valueOf(c);
      code [i] = s;
    }
    return code; 
  }
  
  public static ArrayList <String> findFullyCorrect (String [] code, String [] guess) {
    ArrayList <String> countFullyCorr = new ArrayList <String> ();
    countFullyCorr.clear();
    for (int i = 0; i < code.length; i++) {
      if (code[i].equals(guess[i])) {
        countFullyCorr.add("b");
      }
    }
    return countFullyCorr;
  }
  
  public static ArrayList <String> removeFullyCorrect (String [] code, String [] guess) {
    ArrayList <String> fullyCorrect = new ArrayList <String> ();
    fullyCorrect.clear();
    for (int i = 0; i < code.length; i++) {
      if (code[i].equals(guess[i])) {
        fullyCorrect.add(guess[i]);
      }
    }
    return fullyCorrect;
  }
}
