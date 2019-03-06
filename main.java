import java.util.Scanner;
import java.util.ArrayList;

public class main {
	public static void main(String args[]) {
		
		String[] code = {"G", "O", "R"};
		ArrayList<String> userInput = new ArrayList<String>();
		userInput.add("Y");
		userInput.add("G");
		userInput.add("G"); 
		final String colour = "GRBYOP";
		
		System.out.println(findColourCorrect(code, userInput));
		//System.out.println(valid(userInput, colour, 4));
		
	}
	
	public static ArrayList<String> findColourCorrect(String[] code, ArrayList<String> userInput) { //change input for only 
		userInput = duplicates(userInput);
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
