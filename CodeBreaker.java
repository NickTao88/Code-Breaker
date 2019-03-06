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
    ArrayList <String> done = new ArrayList <String>();
    Collections.addAll(done,"b","b","b","b");
    
    System.out.println("Welcome to Code Breaker!");
    
    createCode(VALID_CHARS, SIZE, code);
    for (int i = 0; i < TRIES; i++) {
      String [][] guess = userInput(SIZE, TRIES, i, VALID_CHARS); 
      ArrayList <String> countFullyCorr = findFullyCorrect (code, guess[i]);
      if (countFullyCorr.equals(done)){
        userWins =true;
        System.out.println("Congratulations! It took you " +(i+1) +" guess to find the code");
        break;
      }
      
      System.out.println(countFullyCorr);
      removeFullyCorrect (code, guess[i]);
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
  public static String[][] userInput (int size, int TRIES, int count, String colour) {
    Scanner sc = new Scanner (System.in);
    String guess [][] = new String [TRIES][size];
    boolean isValid;
    
    for (int j = 0; j < guess[count].length; j++) {
      System.out.println("Please enter your guess of length " +size +" using the letters [G,R,B,Y,O,P]");
      //String temp = sc.nextLine
      guess[count][j] = sc.nextLine();
    }
    isValid = valid (guess[count], colour, size );
    
    if (isValid==false) {
      System.err.println("Invalid Input! Try Again!");
      userInput (size, TRIES, count, colour);
    }
    
    //valid(guess);
    return guess;
  }
  
  public static boolean valid(String[] userInput, String colour, int size) {
    boolean lengthValid = false;
    if (userInput.length == size) {
      lengthValid = true;
    }
    boolean colourValid = true;
    for (int i=0; i<userInput.length; i++) {
      if (!colour.contains(userInput[i])) {
        colourValid = false;
      }
    }
    if (lengthValid == true && colourValid == true) {
      return true;
    } else {
      return false;
    }
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
      if (!code[i].equals(guess[i])) {
        fullyCorrect.add(guess[i]);
      }
    }
    return fullyCorrect;
  }
  
  
  
  public static boolean valid(ArrayList<String> userInput, String colour, int size) {
    boolean lengthValid = false;
    if (userInput.size() == size) {
      lengthValid = true;
    }
    boolean colourValid = true;
    for (int i=0; i<userInput.size(); i++) {
      if (!colour.contains(userInput.get(i))) {
        colourValid = false;
      }
    }
    if (lengthValid == true && colourValid == true) {
      return true;
    } else {
      return false;
    }
  }
  
  public static ArrayList<String> duplicates(ArrayList<String> userInput) {
    for (int i=0; i<userInput.size(); i++) {
      for (int j=0; j<userInput.size(); j++) {
        if (userInput.get(i).equals(userInput.get(j))) {
          userInput.remove(i);
        }
      }
    }
    return userInput; 
  } 
}
