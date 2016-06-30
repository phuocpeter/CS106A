/*
 * File: Rainbow.java
 * ------------------
 * This program is a stub for the Rainbow problem, which displays
 * a rainbow by adding consecutively smaller circles to the canvas.
 */

import java.awt.*;

import acm.program.*;
import acm.graphics.*;

public class Rainbow extends GraphicsProgram {

	public void run() {
		setBackground(Color.CYAN);
		drawRedCircle();
		drawOrangeCircle();
		drawYellowCircle();
		drawGreenCircle();
		drawBlueCircle();
		drawMagentaCircle();
		drawCyanCircle();
	}

	/* Method: drawRedCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawRedCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5);
		fillColor(Color.RED, cir);
		add(cir);
	}
	
	/* Method: drawOrangeCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawOrangeCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + SPACE);
		fillColor(Color.ORANGE, cir);
		add(cir);
	}
	
	/* Method: drawYellowCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawYellowCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + 2* SPACE);
		fillColor(Color.YELLOW, cir);
		add(cir);
	}
	
	/* Method: drawGreenCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawGreenCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + 3 * SPACE);
		fillColor(Color.GREEN, cir);
		add(cir);
	}
	
	/* Method: drawBlueCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawBlueCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + 4 * SPACE);
		fillColor(Color.BLUE, cir);
		add(cir);
	}
	
	/* Method: drawMagentaCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawMagentaCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + 5 * SPACE);
		fillColor(Color.MAGENTA, cir);
		add(cir);
	}
	
	/* Method: drawCyanCircle() */
	/**
	 * Add a circle with filled color
	 */
	private void drawCyanCircle() {
		GOval cir = new GOval(getWidth() * 1.25, getHeight());
		cir.setLocation(-(cir.getWidth() / 2 - getWidth() / 2), getHeight() * 1 / 5 + 6 * SPACE);
		fillColor(Color.CYAN, cir);
		add(cir);
	}

	/* Method: fillColor */
	/**
	 * Fill the circle with color
	 * @param color the color to fill
	 * @param cir the circle to be filled
	 */
	private void fillColor(Color color, GOval cir) {
		cir.setColor(color);
		cir.setFilled(true);
		cir.setFillColor(color);
	}
	
	// The space between circles
	private final int SPACE = 25;

}
