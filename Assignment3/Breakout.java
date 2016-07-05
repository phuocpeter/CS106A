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
	private static final int NBRICK_ROWS = 5;

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
	
	private static final int ANIME_DELAY = 10;
	
	private GRect paddle;
	private GOval ball;
	private double vx, vy = 3.0;
	private RandomGenerator rgen = RandomGenerator.getInstance();

/** Runs the Breakout program. */
	public void run() {
		setup();
		waitForClick();
		startPlay();
	}
	
	/* Method: startPlay() */
	/** Controls the play phase of the game. */
	private void startPlay() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		while (true) {
			moveBall();
			checkForCollision();
		}
	}

	/* Method: checkForCollision() */
	/** Checks if the ball collides with anything. */
	private void checkForCollision() {
		collideWithWalls();
		collideWithOthers();
	}

	/* Method: collideWithOthers() */
	/** 
	 * Checks if the ball hits the paddle or bricks.
	 * Removes the bricks if the ball hit it.
	 */
	private void collideWithOthers() {
		GObject collider = getCollidingObject();
		if (collider != null) {
			vy = -vy;
			if (!collider.equals(paddle)) {
				remove(collider);
			}
		}
	}

	/* Method: getCollidingObject() */
	/**
	 * Checks what objects is at the bounding box of
	 * the ball.
	 * @return the object collides with the ball
	 */
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) return getElementAt(ball.getX(), ball.getY());
		if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		return null;
	}

	/* Method: collideWithWalls() */
	/** Checks if the ball hits the walls. */
	private void collideWithWalls() {
		if (ball.getX() <= 0 || (ball.getX() + 2 * BALL_RADIUS) >= WIDTH) {
			vx = -vx;
		}
		if (ball.getY() <= 0) {
			vy = -vy;
		}
		if (ball.getY() >= HEIGHT) {
			remove(ball);
		}
	}

	/* Method: moveBall() */
	/** Moves the ball. */
	private void moveBall() {
		ball.move(vx, vy);
		pause(ANIME_DELAY);
	}

	/* Method: setup() */
	/** Setups the game play. */
	private void setup() {
		setupBricks();
		setupPaddle();
		addMouseListeners();
		setupBall();
	}

	/* Method: setupBall() */
	/** Draws the ball. */
	private void setupBall() {
		ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball, WIDTH / 2 - ball.getX() / 2, HEIGHT / 2 - ball.getHeight() / 2);
	}

	/* Method: setupPaddle() */
	/** Draws the paddle. */
	private void setupPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
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

	/* Method: mouseMoved() */
	/** Moves the paddle accordingly to the mouse X point. */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= (PADDLE_WIDTH / 2) && e.getX() <= (WIDTH - PADDLE_WIDTH / 2)) {
			paddle.setLocation(e.getX() - (PADDLE_WIDTH / 2), paddle.getY());
		}
	}
	
}
