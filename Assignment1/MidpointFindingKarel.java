/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		putOrCleanBeepers();
		turnRight();
		moveStraightTillWall();
		turnAround();
		moveLShape();
		findEmptySpace();
		turnRight();
		moveStraightTillWall();
		putBeeper();
		turnRight();
		moveStraightTillWall();
		turnAround();
		putOrCleanBeepers();
		turnRight();
		moveTillBeeper();
	}

	/* Method: moveTillBeeper */
	/**
	 * Moves until there're beepers.
	 * Precondition: No in the starting point
	 * Postcondition: Beeper(s) present at that location
	 */
	private void moveTillBeeper() {
		while (!beepersPresent()) {
			move();
		}
	}

	/* Method: findEmptySpace() */
	/**
	 * Moves L shape as long as there are beepers.
	 * Precondition: Beeper(s) present at that location
	 * Postcondition: No in the starting point
	 */
	private void findEmptySpace() {
		while (beepersPresent()) {
			moveLShape();
		}
	}
	
	/* Method: putOrCleanBeepers() */
	/**
	 * Picks up beeper or puts beeper if there's none.
	 */
	private void putOrCleanBeepers() {
		putBeepersTowardUpRight();
		turnAround();
		moveStraightTillWall();
		turnAround();
		putBeepersTowardDownRight();
		turnRight();
	}

	/* Method: putBeepersTowardDownRight() */
	/**
	 * Moves L Shape toward downright, picks or puts beepers
	 * along the way.
	 */
	private void putBeepersTowardDownRight() {
		while (frontIsClear()) {
			moveLShapeDown();
			if (beepersPresent()) {
				pickBeeper();
			} else {
				putBeeper();
			}
		}
		
	}

	/* Method: moveLShapeDown() */
	/**
	 * Moves L Shape.
	 */
	private void moveLShapeDown() {
		move();
		turnRight();
		move();
		turnLeft();
	}

	/* Method: putBeepersTowardUpRight() */
	/**
	 * Moves L Shape toward upright, picks or puts beepers
	 * along the way.
	 */
	private void putBeepersTowardUpRight() {
		while (frontIsClear()) {
			moveLShape();
			if (beepersPresent()) {
				pickBeeper();
			} else {
				putBeeper();
			}
		}
	}

	/* Method: moveLShape() */
	/**
	 * Moves L Shape.
	 */
	private void moveLShape() {
		turnLeft();
		move();
		turnRight();
		move();
	}

	/* Method: moveStraightTillWall() */
	/**
	 * Moves as long as the front is clear.
	 */
	private void moveStraightTillWall() {
		while (frontIsClear()) {
			move();
		}
	}
}
