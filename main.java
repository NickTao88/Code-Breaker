import java.util.Scanner;
import java.util.Random;

public class main {
	
	static final int SIZE = 4;
	  static final int TRIES = 10;
	  static String clues [][] = new String [TRIES][SIZE];
	
	public static void main(String args[]) {
		
	}
	
	public static String[][] findColourCorrect(String[] code, String[] userInput, int global) { //change input for only 
		String[] codeCopy = code.clone(); //test without clone
		String[] colourCorrect = new String[code.length]; //har
		int countClues = 0;
		for (int i=0; i<userInput.length; i++) {
			for (int j=0; j<code.length; j++) {
				if (userInput[i].equals(codeCopy[j])) {
					clues[global][countClues] = "w";
					codeCopy[j] = null;
					countClues++;
					break;
				}
			}
		}
		return clues;
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
	
	
	public static String[] createCode (String colours, int size, String[] code) {
	    Random ran = new Random ();
	    
	    for (int i = 0; i < size; i++) {
	      int num = ran.nextInt(size-1);
	      char c = colours.charAt(num);
	      String s = String.valueOf(c);
	      code [i] = s;
	    }
	    return code; 
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
		boolean colourValid = true;
		for (int i=0; i<userInput.length; i++) {
			if (colour.indexOf(userInput[i]) < 0) {
				colourValid = false;
				break;
			}
		}
		if (userInput.length == size && colourValid == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void displayGame(String[][] board, String[][] piecesCorrect) {
		System.out.println("Guess	   Clues");
		for (int i=0; i<16; i++) {
			System.out.print("*");
		}
		System.out.println();
		
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("	   ");
			for (int k=0; k<piecesCorrect[i].length; k++) {
				if (piecesCorrect[i][k] != null) {
					System.out.print(piecesCorrect[i][k] + " ");
				}
				
			}
			System.out.println();
		}
	}
		
}
