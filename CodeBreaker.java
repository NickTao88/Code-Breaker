/*
 * Name: Nicholas Tao and Rohan Ravindran
 * Date: March 4, 2019
 * Assignment Name: Code Breaker
 */
import java.util.*;
public class CodeBreaker6 {
  
  //declare global variables
  static final int SIZE = 4;
  static final int TRIES = 10;
  static String clues [][] = new String [TRIES][SIZE]; 
  static String validGuesses [][] = new String [TRIES][SIZE];
  static int countClues = 0;
  static Scanner sc = new Scanner (System.in);
  static String [] remCode;
  static int countCurrTurn = 0;
  
  public static void main(String [] args) {
    final String VALID_CHARS = "GRBYOP";
    boolean userWins = false; 
    
    //welcome user and introduction
    System.out.println("Welcome to Code Breaker! Enter your name");
    //String name = sc.nextLine();
    // System.out.println("Hey " +name +"! Let's get started!");
    
    //generate code and store in array
    String [] code = createCode(VALID_CHARS, SIZE);
    
    //main loop for game runs up to TRIES times
    for (int currTurn = 0; currTurn < TRIES; currTurn++) {
      
      boolean isValid;
      
      //prompt user for input
      System.out.println("Please enter your guess of length " +SIZE +" using the letters [G,R,B,Y,O,P]");
      String guessStr = sc.nextLine();
      
      //declare array with length the length of the guess
      String[] guess = new String[guessStr.length()];
      
      //store guess String in an array
      for (int i = 0; i < guessStr.length(); i++) {
        guess[i] = Character.toString(guessStr.charAt(i));
      }
      
      isValid = valid (guess, VALID_CHARS, SIZE); //check if input is valid
      
      //if input is invalid, run the loop again 
      if (isValid==false) {
        System.err.println("Invalid Input! Try Again!"); //print error message
        currTurn--;
        continue;
      } 
      
      //store valid guesses in the 2D array (needed for display method)
      for (int i = 0; i < guess.length;i++) {
        validGuesses[currTurn][i] = guess[i];
      }
      
      findFullyCorrect (code, guess);//call method to find fully correct colours
      
      //check if the clues are all "b", meaning guess is fully correct
      for (int a = 0; a < SIZE; a++) {
        if (clues[currTurn][a] == "b") {
          userWins = true; 
        } else {
          userWins = false;
          break; //if one clue is not "b", stop checking (break)
        }
      }
      
      //if user wins, print congratulations method and number of guesses
      if (userWins==true){
        System.out.println("Congratulations! It took you " +(currTurn+1) +" guess to find the code");
        break; //exit loop
      }
      
      String [] remFuCor = removeFullyCorrect (code, guess); //store the guess with the fully correct colours removed as an array
      findColourCorrect(remCode, remFuCor); //call method to find colour correct
      
      System.out.println(displayGame(validGuesses, clues)); //call method to display current game results
      
      countCurrTurn++; //add one to counter which counts the current turn
    }
    
    //if user did not win, print losing message and the correct code
    if (userWins==false) {
      System.out.print("I'm sorry, you lose. The correct code was ");
      for (int i = 0; i < code.length;i++) {
        System.out.print(code[i]);
      }
      System.out.println("");
    }
  }
  /**
   * Returns an array of randomly generated characters (representing colours), 
   * from a String of colours.
   *
   * @param colours possible colours to generate a code from
   * @param size length of code
   * @return an array of randomly 
   * generated characters (representing colours)
   */
  
  public static String[] createCode (String colours, int size) {
    Random ran = new Random ();
    String [] code = new String [SIZE];
    for (int i = 0; i < size; i++) {
      int num = ran.nextInt(colours.length()-1); //generate a random number from 0 to one less the number of colours
      char c = colours.charAt(num); //find the character in the colours string at the randomly generated position
      String s = String.valueOf(c); //convert the character to a String
      code [i] = s; //store String in code array
    }
    System.out.print("Code: "); //temporary
    for (int i = 0; i < code.length; i++) {
      System.out.print(code[i]); //temporary
    }
    System.out.println(""); //temporary
    return code; 
  }
  
  /**
   * Returns true if guess is valid or
   * false if guess is invalid.
   *
   * @param userInput the guess from the user
   * @param colour possible colours
   * @param size length of code
   * @return true if guess is valid (correct characters and size)
   * or false if guess is invalid (incorrect characters and size)
   */
  
  public static boolean valid(String [] userInput, String colour, int size) {
    boolean lengthValid = false; 
    
    //if user's guess is the correct length, the length is valid
    if (userInput.length == size) {
      lengthValid = true;
    }
    
    boolean colourValid = true;
    for (int i=0; i<userInput.length; i++) {
      //check if the valid colours contain the user's guess and if the user's input is blank
      if (!colour.contains(userInput[i]) || userInput[i].equals("")) {
        colourValid = false;
        break; //if one element is invalid, the whole guess is invalid, so program stops checking
      }
    }
    
    if (lengthValid == true && colourValid == true) {
      return true; //returns true if length and colours are valid
    } else {
      return false; //return false if both criteria are not met
    }
  }
  /**
   * Returns an array containing a "b" for every correctly
   * positioned colour in the guess.
   *
   * @param code the randomly generated code
   * @param guess the user's guess
   * @return a list containing a "b" for every correctly
   * positioned colour in the guess
   */
  public static String[][] findFullyCorrect (String [] code, String [] guess) {  
    
    for (int j = 0; j < code.length; j++) {
      if (code[j].equals(guess[j])) { //if the guess and the code have the same element at the same position
        clues[countCurrTurn][countClues]="b"; //add a "b" to the clues array
        countClues++; //add one to the counter counting how many clues there are
      }
    }
    return clues;
  }
  /**
   * Returns an array that is the result of removing
   * from the first array all chars that are the same 
   * and in the same position in the second array.
   *
   * @param code the randomly generated code
   * @param guess the user's guess
   * @return an array that is the result of removing
   * from the first array all chars that are the same 
   * and in the same position in the second array
   */
  public static String[] removeFullyCorrect (String [] code, String [] guess) { 
    
    int count = 0;
    String [] remFullyCorr = new String [code.length - countClues]; //declare array with length equal to number of non-fully correct elements
    remCode = new String [remFullyCorr.length]; //initialize array with length equal to number of non-fully correct elements
    for (int i = 0; i < code.length; i++) {
      if (!code[i].equals(guess[i])) { //if elements do not match 
        remFullyCorr[count] = guess[i];//store the guess in array
        remCode[count] = code[i]; //store the code in array
        count++; //add one to counter
      }
    }
    
    return remFullyCorr;
  }
  /**
   * Returns an array containing a "w" for every String in the
   * second array that has the same value as the String in the
   * first array but different position.
   * 
   * @param code the randomly generated code after fully 
   * correct elements are removed
   * @param userInput the user's guess after fully correct 
   * elements are removed
   * @return an array that is the result of removing
   * from the first array all chars that are the same 
   * and in the same position in the second array
   */ 
  
  public static String[][] findColourCorrect(String[] code, String[] userInput) {
    ArrayList<String> input = new ArrayList<String>(Arrays.asList(userInput)); //arraylist with input after fully correct elements are removed
    ArrayList<String> codeList = new ArrayList<String>(Arrays.asList(code));//arraylist with code after fully correct elements are removed
    for (int i=0; i < codeList.size(); i++) {
      if (input.contains(codeList.get(i))) { //if input contains an element from the code
        clues[countCurrTurn][countClues] = "w"; //add a 'w' to clues
        countClues++; //add one to counter to count number of clues
      }
    }
    countClues = 0; //reset counter
    return clues;
  }
  /**
   * Returns a string beginning with headers, followed by 16 '*',
   * followed by the guess, followed by the clues up to the turn
   * in which the user is on
   * 
   * @param guess the user's guesses
   * @param clues the clues 
   * @return a string beginning with headers, followed by 16 '*',
   * followed by the guess, followed by the clues up to the turn
   * in which the user is on
   */   
  
  public static String displayGame(String[][] guess, String[][] clues) {
    String results = "Guess\t\tClues\n****************\n"; //initialize String with header and 16 '*'
    
    //loop runs up to one more than the user's current turn (since they started at 0)
    for (int i=0; i<(countCurrTurn+1); i++) {
      for (int j=0; j<guess[i].length; j++) {
        results+=(guess[i][j] + " "); //add one guess to results
      }
      results+=("\t"); //add a tab space
      for (int k=0; k<clues[i].length; k++) {
        if (clues[i][k] != null) {
          results+=(clues[i][k] + " ");//add clues to results
        }   
      }
      results+="\n"; //add a new line
    }
    return results;
  }
}
