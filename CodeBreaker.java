/*
 * Name: Nicholas Tao and Rohan Ravindran
 * Date: March 4, 2019
 * Assignment Name: Code Breaker
 * QUESTION FOR MR A: If invalid input, they re-enter ALL input? ie input:"BBBA" --> need to reenter all, not just the A?
 * TO DO:
 * need 2 arraylists (one is code after removing fullyCorr and one is guess after removing fullyCorr), then compare the two lists!
 * to do: take input as a one line string and store in a String array
 */
import java.util.*;
public class CodeBreaker5 {
  static final int SIZE = 4;
  static final int TRIES = 10;
  static String clues [][] = new String [TRIES][SIZE]; 
  static String validGuesses [][] = new String [TRIES][SIZE];
  static int countClues=0;
  static Scanner sc = new Scanner (System.in);
  static String [] remCode;
  static int countCurrTurn = 0;
  
  public static void main(String [] args) {
    final String VALID_CHARS = "GRBYOP";
    boolean userWins = false;
    
    System.out.println("Welcome to Code Breaker! Enter your name");
    //String name = sc.nextLine();
    //System.out.println("Hey " +name +"! Let's get started!");
    
    String [] code = createCode(VALID_CHARS, SIZE);
    
    for (int currTurn = 0; currTurn < TRIES; currTurn++) {
      
      boolean isValid;
      System.out.println("Please enter your guess of length " +SIZE +" using the letters [G,R,B,Y,O,P]");
      String guessStr = sc.nextLine();
      
      String[] guess = new String[guessStr.length()];
      
      for (int i = 0; i < guessStr.length(); i++) {
        guess[i] = Character.toString(guessStr.charAt(i));
      }
      
      isValid = valid (guess, VALID_CHARS, SIZE);
      if (isValid==false) {
        System.err.println("Invalid Input! Try Again!");
        currTurn--;
        continue;
      } 
      for (int i = 0; i < guess.length;i++) {
        validGuesses[currTurn][i] = guess[i];
      }
      
      findFullyCorrect (code, guess);
      for (int a = 0; a < SIZE; a++) {
        if (clues[currTurn][a] == "b") {
          userWins = true;
        } else {
          userWins = false;
          break;
        }
      }
      if (userWins==true){
        System.out.println("Congratulations! It took you " +(currTurn+1) +" guess to find the code");
        break;
      }
      String [] remFuCor = removeFullyCorrect (code, guess);
      
      
      findColourCorrect(remCode, remFuCor);
      
      displayGame(validGuesses, clues);
      
      countCurrTurn++;
    }
    if (userWins==false) {
      System.out.print("I'm sorry, you lose. The correct code was ");
      for (int i = 0; i < code.length;i++) {
        System.out.print(code[i]);
      }
      System.out.println("");
    }
  }
  
  public static String[] createCode (String colours, int size) {
    Random ran = new Random ();
    String [] code = new String [SIZE];
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
  
  public static boolean valid(String []userInput, String colour, int size) {
    boolean lengthValid = false;
    if (userInput.length == size) {
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
  
  
  public static String[][] findFullyCorrect (String [] code, String [] guess) {   
    for (int j = 0; j < code.length; j++) {
      if (code[j].equals(guess[j])) {
        clues[countCurrTurn][countClues]="b";
        countClues++;
      }
    }
    return clues;
  }
  
  public static String[] removeFullyCorrect (String [] code, String [] guess) { 
    int temp = 0;
    String [] remFullyCorr = new String [code.length - countClues];
    remCode = new String [remFullyCorr.length];
    for (int i = 0; i < code.length; i++) {
      if (!code[i].equals(guess[i])) {
        remFullyCorr[temp] = guess[i];
        remCode[temp] = code[i]; 
        temp++;
      }
    }
    
    return remFullyCorr;
  }
  
  //THIS METHOD BROKEN
  public static String[][] findColourCorrect(String[] code, String[] userInput) {
    ArrayList<String> input = new ArrayList<String>(Arrays.asList(userInput));
    ArrayList<String> codeList = new ArrayList<String>(Arrays.asList(code));
    for (int i=0; i < codeList.size(); i++) {
      if (input.contains(codeList.get(i))) {
        clues[countCurrTurn][countClues] = "w";
        countClues++;
        codeList.remove(i);
      }
    }
    countClues = 0;
    return clues;
  }
  
  public static void displayGame(String[][] board, String[][] piecesCorrect) {
    System.out.println("Guess        Clues");
    
    System.out.println("****************");
    
    for (int i=0; i<(countCurrTurn+1); i++) {
      for (int j=0; j<board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.print("\t");
      for (int k=0; k<piecesCorrect[i].length; k++) {
        if (piecesCorrect[i][k] != null) {
          System.out.print(piecesCorrect[i][k] + " ");
        }
        
      }
      System.out.println();
    }
  }
}
