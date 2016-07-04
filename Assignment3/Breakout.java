/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Runs the Breakout program. */
	public void run() {
		setup();
	}
	
	/* Method: setup() */
	/** Setups the game play. */
	private void setup() {
		setupBricks();
		setupPaddle();
	}
	
	/* Method: setupPaddle() */
	/** Draws the paddle. */
	private void setupPaddle() {
		GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle, WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET);
	}

	/* Method: setupBricks() */
	/** Draws the bricks. */
	private void setupBricks() {
		for (int i = 1; i <= NBRICK_ROWS; i++) {
			setupOneRow(i);
		}
	}

	/* Method: setupOneRow() */
	/** Draws one row of bricks.
	 * @param row the current row
	 */
	private void setupOneRow(int row) {
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			double x = (WIDTH / 2) - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) / 2) + (i * (BRICK_WIDTH + BRICK_SEP));
			double y = BRICK_Y_OFFSET + (row * (BRICK_HEIGHT + BRICK_SEP));
			Color color = getRowColor(row);
			GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
			brick.setColor(color);
			brick.setFilled(true);
			brick.setFillColor(color);
			add(brick);
		}
	}

	/* Method: getRowColor() */
	/**
	 * Chooses the correct color of the row.
	 * @param row the current row index
	 * @return the color of the current row
	 */
	private Color getRowColor(int row) {
		switch (row) {
		case 1:
		case 2:
			return Color.RED;
		case 3:
		case 4:
			return Color.ORANGE;
		case 5:
		case 6:
			return Color.YELLOW;
		case 7:
		case 8:
			return Color.GREEN;
		case 9:
		case 10:
			return Color.CYAN;
		default:
			return null;
		}
	}

}
