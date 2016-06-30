/*
 * File: Pyramid.java
 * ------------------
 * This program is a stub for the Pyramid problem, which draws
 * a brick pyramid.
 */

import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram {

	final int BRICK_WIDTH = 30;
	final int BRICK_HEIGHT = 12;
	final int BRICKS_IN_BASE = 12;
	
	public void run() {
		drawBrick();
	}
	
	private void drawBrick() {
		GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
		brick.move(this.getWidth() / 2, this.getHeight() / 2);
		add(brick);
	}

}
