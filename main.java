import java.util.Scanner;
import java.util.ArrayList;

public class main {
	public static void main(String args[]) {
		
		String[] code = {"Y", "P", "G", "G"};
		ArrayList<String> userInput = new ArrayList<String>();
		userInput.add("G");
		userInput.add("P");
		userInput.add("O");
		userInput.add("R"); 
		final String colour = "GRBYOP";
		
		System.out.println(findColourCorrect(code, userInput));
		System.out.println(valid(userInput, colour, 4));
		
	}
	
	public static ArrayList<String> findColourCorrect(String[] code, ArrayList<String> userInput) { //change input for only 
		ArrayList<String> colourCorrect = new ArrayList<String>();
		boolean tempTrue = false;
		for (int i=0; i<userInput.size(); i++) {
			for (int j=0; j<code.length; j++) {
				if (code[j].equals(userInput.get(i))) {
					tempTrue = true;
				}
			}
			if (tempTrue) {
				colourCorrect.add("w");
			}
			tempTrue = false;
		}
		return colourCorrect;
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
	
	//public static String displayGame(int[] userInput, )
	
}