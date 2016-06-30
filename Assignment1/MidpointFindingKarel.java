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

	private void moveTillBeeper() {
		while (!beepersPresent()) {
			move();
		}
	}

	private void findEmptySpace() {
		while (beepersPresent()) {
			moveLShape();
		}
	}
	
	private void putOrCleanBeepers() {
		putBeepersTowardUpRight();
		turnAround();
		moveStraightTillWall();
		turnAround();
		putBeepersTowardDownRight();
		turnRight();
	}

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

	private void moveLShapeDown() {
		move();
		turnRight();
		move();
		turnLeft();
	}

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

	private void moveLShape() {
		turnLeft();
		move();
		turnRight();
		move();
	}

	private void moveStraightTillWall() {
		while (frontIsClear()) {
			move();
		}
	}
}
