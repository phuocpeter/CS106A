/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawScaffold();
		setGuessLabel();
		setIncorrectLabel();
	}

	/* Method: setGuessLabel() */
	/** Sets the position of the Label */;
	private void setGuessLabel() {
		guessLabel.setFont("Helvetica-20");
		add(guessLabel, getWidth() / 2 - BEAM_LENGTH, 
				getHeight() / 2 - BODY_LENGTH - 2 * HEAD_RADIUS - 
				ROPE_LENGTH + SCAFFOLD_HEIGHT + 2 * guessLabel.getAscent());
	}

	/* Method: setIncorrectLabel() */
	/** Sets the position of the Label */;
	private void setIncorrectLabel() {
		add(incorrectLabel, getWidth() / 2 - BEAM_LENGTH,
				getHeight() / 2 - BODY_LENGTH - 2 * HEAD_RADIUS - 
				ROPE_LENGTH + SCAFFOLD_HEIGHT + 2 * guessLabel.getAscent() + 
				2 * incorrectLabel.getAscent());
	}
	
	/* Method: drawScaffold() */
	/** Draws a scaffold on canvas. */
	private void drawScaffold() {
		double scaffoldX = getWidth() / 2 - BEAM_LENGTH;
		double scaffoldY0 = getHeight() / 2 - BODY_LENGTH - 2 * HEAD_RADIUS - ROPE_LENGTH;
		double scaffoldY1 = scaffoldY0 + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(scaffoldX, scaffoldY0, scaffoldX, scaffoldY1);
		add(scaffold);
		// Draws beam.
		double beamY = getHeight() / 2 - BODY_LENGTH - 2 * HEAD_RADIUS - ROPE_LENGTH;
		double beamX0 = scaffold.getX();
		double beamX1 = beamX0 + BEAM_LENGTH;
		GLine beam = new GLine(beamX0, beamY, beamX1, beamY);
		add(beam);
		// Draws rope.
		double ropeX = beamX1;
		double ropeY0 = beamY;
		double ropeY1 = ropeY0 + ROPE_LENGTH;
		GLine rope = new GLine(ropeX, ropeY0, ropeX, ropeY1);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		guessLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectString += letter;
		incorrectLabel.setLabel(incorrectString);
	}
	
	/* Method: hang() */
	/**
	 * Draws a a body parts based on number of guesses left.
	 * @param guesses the number of guesses left
	 */
	public void hang(int guesses) {
		switch(guesses) {
		case 7:
			drawHead();
			break;
		case 6:
			drawBody();
			break;
		case 5:
			drawLeftArm(true);
			break;
		case 4:
			drawLeftArm(false);
			break;
		case 3:
			drawHip();
			break;
		case 2:
			drawLeftLeg(true);
			break;
		case 1:
			drawLeftLeg(false);
			break;
		case 0:
			drawFeet();
			break;
		}
	}

	/* Method: drawHead() */
	/** Draws a head. */
	private void drawHead() {
		GOval head = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		double headX = getWidth() / 2 - HEAD_RADIUS;
		double headY = getHeight() / 2 - BODY_LENGTH - HEAD_RADIUS * 2;
		add(head, headX, headY);
	}
	
	/* Method: drawBody() */
	/** Draws a body. */
	private void drawBody() {
		double x = getWidth() / 2;
		double y0 = getHeight() / 2;
		double y1 = y0 - BODY_LENGTH;
		GLine body = new GLine(x, y0, x, y1);
		add(body);
	}
	
	/* Method: drawLeftArm() */
	/** 
	 * Draws an arm.
	 * @param left if false draws on right
	 */
	private void drawLeftArm(boolean left) {
		double upperX0 = getWidth() / 2;
		double upperY = getHeight() / 2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		double lowerY0 = upperY;
		double lowerY1 = lowerY0 + LOWER_ARM_LENGTH;
		double upperX1, lowerX;
		if (left) {
			upperX1 = upperX0 - UPPER_ARM_LENGTH; 
			lowerX = getWidth() / 2 - UPPER_ARM_LENGTH;
		} else {
			upperX1 = upperX0 + UPPER_ARM_LENGTH;
			lowerX = getWidth() / 2 + UPPER_ARM_LENGTH;
		}
		
		GLine upperArm = new GLine(upperX0, upperY, upperX1, upperY);
		GLine lowerArm = new GLine(lowerX, lowerY0, lowerX, lowerY1);
		add(upperArm);
		add(lowerArm);
	}
	
	/* Method: drawHip() */
	/** Draws a hip. */
	private void drawHip() {
		double x0 = getWidth() / 2 - HIP_WIDTH;
		double x1 = getWidth() / 2 + HIP_WIDTH;
		double y = getHeight() / 2;
		GLine hip = new GLine(x0, y, x1, y);
		add(hip);
	}
	
	/* Method: drawLeftLeg() */
	/**
	 * Draws leg.
	 * @param left if false draws on right
	 */
	private void drawLeftLeg(boolean left) {
		double x;
		double y0 = getHeight() / 2;
		double y1 = y0 + LEG_LENGTH;
		if (left) {
			x = getWidth() / 2 - HIP_WIDTH;
		} else {
			x = getWidth() / 2 + HIP_WIDTH;
		}
		GLine leg = new GLine(x, y0, x, y1);
		add(leg);
	}
	
	/* Method: drawFeet() */
	/** Draws feet. */
	private void drawFeet() {
		double leftX0 = getWidth() / 2 - HIP_WIDTH;
		double leftX1 = leftX0 - FOOT_LENGTH;
		double rightX0 = getWidth() / 2 + HIP_WIDTH;
		double rightX1 = rightX0 + FOOT_LENGTH;
		double y = getHeight() / 2 + LEG_LENGTH;
		add(new GLine(leftX0, y, leftX1, y));
		add(new GLine(rightX0, y, rightX1, y));
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private GLabel guessLabel = new GLabel("");
	private GLabel incorrectLabel = new GLabel("");
	private String incorrectString = "";

}
