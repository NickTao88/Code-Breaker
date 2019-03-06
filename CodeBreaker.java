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
    final int TRIES = 10;
    String [ ] code = new String [SIZE];
    boolean userWins = false;
    
    System.out.println("Welcome to Code Breaker!");
    
    createCode(VALID_CHARS, SIZE, code);
    for (int i = 0; i < TRIES; i++) {
      String [] guess = userInput(SIZE);
      //valid(); (use do-while to keep calling if invalid input)
      ArrayList <String> countFullyCorr = findFullyCorrect (code, guess);
      /*if (user guesses code){
       * userWins =true;
       * System.out.println("Congratulations! It took you" +(i+1) +" guess to find the code");
       * break;
       * }
       */ 
      System.out.println(countFullyCorr);
      removeFullyCorrect (code, guess);
      //findColourCorrect (code, guess);
      //displayGame();
    }
    if (userWins = false) {
      System.out.print("I'm sorry, you lose. The correct code was ");
      for (int i = 0; i < code.length;i++) {
        System.out.print(code[i]);
      }
    }
    
  }
  
  public static String[] userInput (int size) {
    Scanner sc = new Scanner (System.in);
    String [] guess = new String [size];
    for (int i = 0; i < size; i++) {
      System.out.println("Enter your guess [G,R,B,Y,O,P]");
      guess[i] = sc.nextLine();
    }
    //valid(guess);
    return guess;
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
