/*
 * Name: Nicholas Tao and Rohan Ravindran
 * Date: March 4, 2019
 * Assignment Name: Code Breaker
 */
import java.util.*;
public class CodeBreaker2 {
  static final int SIZE = 4;
  static final int TRIES = 10;
  static String clues [][] = new String [TRIES][SIZE]; ////NEED TO STORE CLUES HERE --> HOW?
  public static void main (String [] args) {
    final String VALID_CHARS = "GRBYOP";
    String [ ] code = new String [SIZE];
    boolean userWins = false;
    
    System.out.println("Welcome to Code Breaker!");
    
    createCode(VALID_CHARS, SIZE, code);
    for (int i = 0; i < TRIES; i++) {
      String [][] guess = userInput(SIZE, TRIES, i, VALID_CHARS); 
      findFullyCorrect (code, guess[i], i);
      for (int a = 0; a < SIZE; a++) {
        if (clues[i][a] == "b") {
          userWins = true;
        } else {
          userWins = false;
          break;
        }
      }
      if (userWins==true){
        System.out.println("Congratulations! It took you " +(i+1) +" guess to find the code");
        break;
      }
      
      
      removeFullyCorrect (code, guess[i]);
      findColourCorrect (code, guess[i], i);
      displayGame(guess, clues, i);
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
      guess[count][j] = sc.nextLine();
    }
    isValid = valid (guess[count], colour, size );
    
    if (isValid==false) {
      System.err.println("Invalid Input! Try Again!");
      userInput (size, TRIES, count, colour);
    }
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
      int num = ran.nextInt(size-1);
      char c = colours.charAt(num);
      String s = String.valueOf(c);
      code [i] = s;
    }
    for (int i = 0; i < code.length; i++) {
    System.out.println(code[i]); //temporary
    }
    return code; 
  }
  
  public static String[][] findFullyCorrect (String [] code, String [] guess, int i) {
    int countClues=0;
    
    for (int j = 0; j < code.length; j++) {
      if (code[j].equals(guess[j])) {
        countClues++;
        clues[i][countClues]="b";
      }
    }
    return clues;
  }
  
  public static ArrayList<String> removeFullyCorrect (String [] code, String [] guess) {
    ArrayList <String> remFullyCorr = new ArrayList <String> ();
    remFullyCorr.clear();
    for (int i = 0; i < code.length; i++) {
      if (!code[i].equals(guess[i])) {
        remFullyCorr.add(guess[i]);
      }
    }
    return remFullyCorr;
  }
  
  
  public static String[][] findColourCorrect(String[] code, String[] userInput, int count) { //change input for only 
    String[] codeCopy = code.clone(); //test without clone
    String[] colourCorrect = new String[code.length]; //har
    int countClues = 0;
    for (int i=0; i<userInput.length; i++) {
      for (int j=0; j<code.length; j++) {
        if (userInput[i].equals(codeCopy[j])) {
          clues[count][countClues] = "w";
          codeCopy[j] = null;
          countClues++;
          break;
        }
      }
    }
    return clues;
  }
  public static void displayGame(String[][] board, String[][] piecesCorrect, int count) {
    System.out.println("Guess\tClues");
    
    System.out.println("****************");
    
    for (int i=0; i<(count+1); i++) {
      for (int j=0; j<board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.print("    ");
      for (int k=0; k<piecesCorrect[i].length; k++) {
        if (piecesCorrect[i][k] != null) {
          System.out.print(piecesCorrect[i][k] + " ");
        }
        
      }
      System.out.println();
    }
  }
}
