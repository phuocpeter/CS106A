/*
 * File: Quadratic.java
 * --------------------
 * This program is a stub for the Quadratic problem, which finds the
 * roots of the quadratic equation.
 */

import acm.program.*;

public class Quadratic extends ConsoleProgram {

	public void run() {
		askForCoefficients();
		calculateSolutions();
		displayResults();
	}
	
	/* Method: askForCoefficients() */
	/**
	 * Assigns the three coefficients (a, b and c) to
	 * the three instance variables.
	*/
	private void askForCoefficients() {
		println("Enter coefficients for the quadratic equation:");
		a = readDouble("a: ");
		b = readDouble("b: ");
		c = readDouble("c: ");
	}
	
	/* Method: calculateSolution() */
	/**
	 * Calculates and assigns the two solutions to
	 * instance variables r1 and r2
	 */
	private void calculateSolutions() {
		double delta = getDelta();
		r1 = (-b + Math.sqrt(delta)) / (2 * a);
		r2 = (-b - Math.sqrt(delta)) / (2 * a);
	}
	
	/* Method: getDelta() */
	/**
	 * Calculates delta by using b^2 - 4ac
	 * @return double delta
	 */
	private double getDelta() {
		return Math.pow(b, 2) - 4 * a * c;
	}

	/* Method: displayResults() */
	/**
	 * Print the solutions r1 and r2 on screen
	 */
	private void displayResults() {
		println("The first solution is " + r1);
		println("The second solution is " + r2);
	}
	
	// r1 and r2 are the two solutions after
	// calculation
	private double a, b, c, r1, r2;

}

