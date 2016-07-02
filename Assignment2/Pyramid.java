/*
 * File: Pyramid.java
 * ------------------
 * This program is a stub for the Pyramid problem, which draws
 * a brick pyramid.
 */

import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram {
	
	public void run() {
		while (row > 0) {
			if (row % 2 == 0) {
				drawEvenRow();
			} else {
				drawOddRow();
			}
			row--;
		}
	}
	
	/* Method: drawOddRow() */
	/**
	 * Draws bricks of odd row.
	 */
	private void drawOddRow() {
		GRect firstBrick = makeFirstBrick();
		double firstBrickX = firstBrick.getX();
		double firstBrickY = firstBrick.getY();
		for (int i = 0; i <= (row / 2); i++) {
			GRect leftBrick = new GRect(firstBrickX - (i * BRICK_WIDTH), firstBrickY, BRICK_WIDTH, BRICK_HEIGHT);
			add(leftBrick);
			GRect rightBrick = new GRect(firstBrickX + (i * BRICK_WIDTH), firstBrickY, BRICK_WIDTH, BRICK_HEIGHT);
			add(rightBrick);
		}
	}

	/* Method: makeFirstBrick() */
	/**
	 * Creates the first brick of the odd row.
	 * @return the brick created
	 */
	private GRect makeFirstBrick() {
		double brickX = (getWidth() / 2) - (BRICK_WIDTH / 2);
		double brickY = getHeight() - ((BRICKS_IN_BASE - row + 1) * BRICK_HEIGHT);
		GRect brick = new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
		add(brick);
		return brick;
	}

	/* Method: drawEvenRow() */
	/**
	 * Draws bricks of even row.
	 * Inspired from
	 * https://github.com/simyseng/CS106A/blob/master/Assignment%202/Pyramid.java
	 */
	private void drawEvenRow() {
		double brickX, brickY;
		for (int i = 0; i < row; i++) {
			brickX = (getWidth() / 2) - (row / 2 * BRICK_WIDTH) + (i * BRICK_WIDTH);
			brickY = getHeight() - ((BRICKS_IN_BASE - row + 1) * BRICK_HEIGHT);
			GRect brick = new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
			add(brick);
		}
	}

	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 12;
	private int row = BRICKS_IN_BASE;
	
}
