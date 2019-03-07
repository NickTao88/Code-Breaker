public class main {
	public static void main(String args[]) {
		
	}
	
	public static String[] findColourCorrect(String[] code, String[] userInput) { //change input for only 
		String[] codeCopy = code.clone();
		String[] colourCorrect = new String[code.length]; //har
		for (int i=0; i<userInput.length; i++) {
			for (int j=0; j<code.length; j++) {
				if (userInput[i].equals(codeCopy[j])) {
					colourCorrect[i] = "w";
					codeCopy[j] = null;
					break;
				}
			}
		}
		return colourCorrect;
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
