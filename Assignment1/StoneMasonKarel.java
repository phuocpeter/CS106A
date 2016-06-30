/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	private boolean done = false;
	
	public void run() {
		while (!done) {
			turnLeft();
			rebuildTheColumn();
			turnRight();
			if (frontIsBlocked()) {
				break;
			}
			moveToTheNextColumn();
			turnRight();
			rebuildTheColumn();
			turnLeft();
			if (frontIsBlocked()) {
				break;
			}
			moveToTheNextColumn();
		}
	}

	private void moveToTheNextColumn() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

	private void rebuildTheColumn() {
		while (frontIsClear()) {
			if (!beepersPresent()) {
				putBeeper();
			}
			move();
		}
	}

}
