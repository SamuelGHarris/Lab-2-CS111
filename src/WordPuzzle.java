/**
* CS 111 Section No. 002
* Lab Assignment 2
* @author Samuel Harris 
**/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordPuzzle {
	public static void main(String args[]) {

		char[][] puzzle = null;
		String word = null;
		Scanner input = new Scanner(System.in);

		try {

			puzzle = WordPuzzle.fill(puzzle);
			WordPuzzle.printPuzzle(puzzle);
			System.out.println("\nWhat word would you like to search for?");
			word = input.nextLine();
			WordPuzzle.play(word, puzzle);

		} catch (Exception e) {

			System.out.println("The file was not found or an invalid entry was made.");

		}
	}

	/**
	 * Fills an array with characters from a chosen file
	 * 
	 * @param puzzle A 2D character array
	 * @return An 2D array filled with the characters from a given file
	 * @throws FileNotFoundException
	 */
	public static char[][] fill(char[][] puzzle) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);

		String fileName;
		System.out.println("Enter the file name");
		fileName = input.nextLine();
		Scanner fileReader = new Scanner(new File(fileName));

		//Collects the numbers at the beginning of the file to determine the dimensions of the array.
		int rows = fileReader.nextInt();
		int cols = fileReader.nextInt();

		puzzle = new char[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				puzzle[i][j] = fileReader.next().charAt(0);
			}
		}
		return puzzle;
	}

	/**
	 * Prints a chosen 2D character array
	 * 
	 * @param puzzle A 2D character array that holds the word search
	 */
	public static void printPuzzle(char[][] puzzle) {

		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Determines if a given word exists in the 2D character array provided
	 * 
	 * @param word The string that will be searched for in the 2D character array
	 * @param puzzle A 2D character array that holds the word search
	 */
	public static void play(String word, char[][] puzzle) {

		Boolean result = false;

		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				if (word.charAt(0) == puzzle[i][j]) {
					if (WordPuzzle.checkDown(puzzle, word, i, j) == true) {
						System.out.println("\"" + word + "\" was found by the method checkDown beginning in cell [" + i + "][" + j + "]");
						System.out.println("Position: Down");
						result = true;
					} else if (WordPuzzle.checkRight(puzzle, word, i, j) == true) {
						System.out.println("\"" + word + "\" was found by the method checkRight beginning in cell [" + i + "][" + j + "]");
						System.out.println("Position: Right");
						result = true;
					}
				}
			}
		}
		if (result != true) {
			System.out.println("\"" + word + "\" was not found in the puzzle.");
		}
	}

	/**
	 * Checks to see if the given word is present in the down position in the 2D array
	 * 
	 * @param puzzle A 2D character array that holds the word search
	 * @param word The string that will be searched for in the 2D character array
	 * @param row The row index where the first letter of the word has been found
	 * @param col The column index where the first letter of the word has been found
	 * @return True if the whole word was found in the down position, otherwise false
	 */
	public static boolean checkDown(char[][] puzzle, String word, int row, int col) {
		
		boolean truthValue = false;
		
		//The for loop iterates through the imported word.
		
		for (int i = 1; i < word.length(); i++) {
			
			/*Row is incremented in order to move down the array, and puzzle.length is used to compare the row position to the number of rows in the array.
			If the character from the word matches with the character from the array, the boolean variable is assigned true.
			If the condition is false, the method will return false. This prevents the code from reporting an incorrect word match.*/
			
			if (((row + i) < puzzle.length) && (word.charAt(i) == puzzle[row + i][col])) {
				truthValue = true;
			} else {
				return false;
			}
		}
		return truthValue;
	}

	/**
	 * Checks to see if the given word is present in the right position in the 2D array
	 * 
	 * @param puzzle A 2D character array that holds the word search
	 * @param word The string that will be searched for in the 2D character array
	 * @param row The row index where the first letter of the word has been found
	 * @param col The column index where the first letter of the word has been found          
	 * @return True if the whole word was found in the right position, otherwise false
	 */
	public static boolean checkRight(char[][] puzzle, String word, int row, int col) {
		
		boolean truthValue = false;
		
		for (int i = 1; i < word.length(); i++) {
			
			/*Col is incremented in order to move to the right, and puzzle[row].length is used to compare the col position to the number of columns in the array.
			If the character from the word matches with the character from the array, the boolean variable is assigned true.
			If the condition is false, the method will return false. This prevents the code from reporting an incorrect word match.*/
			
			if (((col + i) < puzzle[row].length) && (word.charAt(i) == puzzle[row][col + i])) {
				truthValue = true;
			} else {
				return false;
			}
		}
		return truthValue;
	}
}
