/*
 * File: FindRange.java
 * --------------------
 * This program is a stub for the FindRange problem, which finds the
 * smallest and largest values in a list of integers.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	public void run() {
		print("This program finds the smallest and largest integers in a list. Enter values, one per line, using a");
		print(" " + SENTINEL + " ");
		println("to signal the end of a list.");
		askForInput();
		displayResult();
	}
	
	/* Method: displayResult() */
	/**
	 * Print out the result of max and min numbers.
	 */
	private void displayResult() {
		if (max == min) {
			println("The smallest and the largest value is both " + max);
			return;
		}
		println("The smallest value is " + min);
		println("The largest value is " + max);
	}

	/* Method: askForInput() */
	/**
	 * Get the input numbers until the sentinel
	 * is inputed.
	 */
	private void askForInput() {
		int value = readInt(" ? ");
		if (value == SENTINEL) {
			println("There is no value to find range.");
		}
		max = value;
		min = value;
		do {
			value = readInt(" ? ");
			compareWithMaxMin(value);
		} while (value != SENTINEL);
	}

	/* Method: compareWithMaxMin(int value) */
	/**
	 * Compare the value with the current max and min
	 * numbers.
	 * @param value the value to compare
	 */
	private void compareWithMaxMin(int value) {
		if (value == SENTINEL) return;
		if (value > max) {
			max = value;
		} 
		if (value < min) {
			min = value;
		}
	}

	private final int SENTINEL = 0;
	private int max, min;

}
