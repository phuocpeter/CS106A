/*
 * File: Hailstone.java
 * --------------------
 * This program is a stub for the Hailstone problem, which computes
 * Hailstone sequence described in Assignment #2.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	public void run() {
		println("This program computes Hailstone sequences.");
		askForInput();
		runThroughTheSequence();
		displayResult();
	}

	/* Method: displayResult() */
	/**
	 * Print on screen the steps.
	 */
	private void displayResult() {
		println("The process took " + steps + " steps to reach 1.");
	}

	/* Method: runThroughTheSequence() */
	/**
	 * Executes the Hailstone sequence and
	 * assigns the number of steps to 'steps'.
	 * Requires 'n' to be initialized.
	 */
	private void runThroughTheSequence() {
		while (n > 1) {
			nTemp = n;
			if (nIsEven()) {
				n = n / 2;
				println(nTemp + " is even, so I take half = " + n);
			} else {
				n = 3 * n + 1;
				println(nTemp + " is odd, so I make 3n+1 = " + n);
			}
			steps++;
		}
	}

	/* Method: nIsEven() */
	/**
	 * @return true if n is even.
	 */
	private boolean nIsEven() {
		if (n % 2 == 0) {
			return true;
		}
		return false;
	}

	/* Method: askForInput() */
	/**
	 * Assigns the integer to n.
	 */
	private void askForInput() {
		n = readInt("Enter a number: ");
	}
	
	private int n, nTemp, steps = 0;

}
