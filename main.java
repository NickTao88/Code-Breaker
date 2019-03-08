/*
 * Name: Nicholas Tao and Rohan Ravindran
 * Date: March 4, 2019
 * Assignment Name: Code Breaker
 * QUESTION FOR MR A: If invalid input, they re-enter ALL input? ie input:"BBBA" --> need to reenter all, not just the A?
 * another question: is user input supposed to all be in one line?
 * TO DO:
 * need 2 arraylists (one is code after removing fullyCorr and one is guess after removing fullyCorr), then compare the two lists!
 */
import java.util.*;
public class main {
  static final int SIZE = 4;
  static final int TRIES = 10;
  static String clues [][] = new String [TRIES][SIZE]; 
  static int countClues=0;
  static int countTries = 0;
  static Scanner sc = new Scanner (System.in);
  
  
  public static void main(String[] args) {
    final String VALID_CHARS = "GRBYOP";
    String [ ] code = new String [SIZE];
    boolean userWins = false;
    
    System.out.println("Welcome to Code Breaker! Enter your name");
    //String name = sc.nextLine();
    //System.out.println("Hey " +name +"! Let's get started!");
    
    createCode(VALID_CHARS, SIZE, code);
    String [][] guess = new String [TRIES][SIZE];
    for (int i = 0; i < TRIES; i++) {
      countTries++;
      userInput(SIZE, TRIES, i, VALID_CHARS, guess); 
      
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
      findColourCorrect(code, removeFullyCorrect (code, guess[i]) , i);
      
      displayGame(guess, clues, i);
    }
    if (userWins = false) {
      System.out.print("I'm sorry, you lose. The correct code was ");
      for (int i = 0; i < code.length;i++) {
        System.out.print(code[i]);
      }
    }
  }
  
  public static String[][] findColourCorrect(String[] code, String[] userInput, int count) { //change input for only 
	  String[] userInputClone = userInput;
		for (int i=0; i<userInputClone.length; i++) {
			if (Arrays.stream(code).anyMatch(userInputClone[i]::equals)) {
				clues[count][countClues] = "w";
				userInputClone[i] = "";
				countClues++;
			}
		}
		countClues = 0;
		return clues;
	  }
  
  public static String[][] userInput (int size, int TRIES, int count, String colour, String [][]guess) {
    Scanner sc = new Scanner (System.in);
    
    boolean isValid;
    for (int j = 0; j < guess[count].length; j++) {
      System.out.println("Please enter your guess of length " +size +" using the letters [G,R,B,Y,O,P]");
      guess[count][j] = sc.nextLine();
    }
    isValid = valid (guess[count], colour, size );
    
    if (isValid==false) {
      System.err.println("Invalid Input! Try Again!");
      userInput (size, TRIES, count, colour, guess);
    }
    return guess;
  }
  
  public static boolean valid(String[] userInput, String colour, int size) {
    boolean lengthValid = false;
    if (userInput.length == size) { //hold up this will always return true no matter what since it'd hardcoded (the size of 2d array)
      lengthValid = true;
    }
    
    boolean colourValid = true;
    for (int i=0; i<userInput.length; i++) {
      if (!colour.contains(userInput[i]) || userInput[i].equals("")) {
        colourValid = false;
        break;
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
    System.out.print("Code: "); //temporary
    for (int i = 0; i < code.length; i++) {
      System.out.print(code[i]); //temporary
    }
    System.out.println(""); //temporary
    return code; 
  }
  
  public static String[][] findFullyCorrect (String [] code, String [] guess, int i) {   
    for (int j = 0; j < code.length; j++) {
      if (code[j].equals(guess[j])) {
        clues[i][countClues]="b";
        countClues++;
      }
    }
    return clues;
  }
  
  public static String[] removeFullyCorrect (String [] code, String [] guess) { //fix
    int temp = 0;
    String [] remFullyCorr = new String [code.length - countClues];
    for (int i = 0; i < code.length; i++) {
      if (!code[i].equals(guess[i])) {
        remFullyCorr[temp] = guess[i];
        temp++;
      }
    }
    
    System.out.println("Remove Fully Correct: " + Arrays.toString(remFullyCorr));
    
    return remFullyCorr;
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
