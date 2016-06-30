/*
 * File: GraphicsHierarchy.java
 * ----------------------------
 * This program is a stub for the GraphicsHierarchy problem, which
 * draws a partial diagram of the acm.graphics hierarchy.
 */

import acm.program.*;
import acm.graphics.*;

public class GraphicsHierarchy extends GraphicsProgram {
	
	public void run() {
		drawGObject();
		drawGLabel();
		drawGLine();
		drawGOval();
		drawGRect();
	}
	
	/* Method: drawLineTo() */
	/**
	 * Draw a line between GRects.
	 * @param Xf the X destination
	 * @param Yf the Y destination
	 */
	private void drawLineTo(double Xf, double Yf) {
		GLine line = new GLine(Xi, Yi, Xf, Yf);
		add(line);
	}
	
	/* Method: drawGRect() */
	/**
	 * Draws GRect and labels it.
	 */
	private void drawGRect() {
		GRect box = drawBox();
		box.move(this.getWidth() - BOX_WIDTH - BOX_WIDTH / 3, this.getHeight() * 3 / 4);
		drawLabel("GLabel", box);
		double Xf = box.getX() + box.getWidth() / 2;
		double Yf = box.getY();
		drawLineTo(Xf, Yf);
	}

	/* Method: drawGOval() */
	/**
	 * Draws GOval and labels it.
	 */
	private void drawGOval() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 + 9, this.getHeight() * 3 / 4);
		drawLabel("GOval", box);
		double Xf = box.getX() + box.getWidth() / 2;
		double Yf = box.getY();
		drawLineTo(Xf, Yf);
	}

	/* Method: drawGLine() */
	/**
	 * Draws GLine and labels it.
	 */
	private void drawGLine() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 - BOX_WIDTH - 9, this.getHeight() * 3 / 4);
		drawLabel("GLine", box);
		double Xf = box.getX() + box.getWidth() / 2;
		double Yf = box.getY();
		drawLineTo(Xf, Yf);
	}

	/* Method: drawGLabel() */
	/**
	 * Draws GLabel and labels it.
	 */
	private void drawGLabel() {
		GRect box = drawBox();
		box.move(BOX_WIDTH / 3, this.getHeight() * 3 / 4);
		drawLabel("GLabel", box);
		double Xf = box.getX() + box.getWidth() / 2;
		double Yf = box.getY();
		drawLineTo(Xf, Yf);
	}

	/* Method: drawGObject() */
	/**
	 * Draws GObject and labels it.
	 */
	private void drawGObject() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 - BOX_WIDTH / 2, this.getHeight() / 4 - BOX_HEIGHT);
		drawLabel("GObject", box);
		Xi = box.getX() + BOX_WIDTH / 2;
		Yi = box.getY() + BOX_HEIGHT;
	}
	
	/* Method: drawLabel() */
	/**
	 * Draws and adds a GLabel.
	 * @param title the title of the label
	 * @param box the box where the label will be positioned
	 */
	private void drawLabel(String title, GRect box) {
		GLabel label = new GLabel(title);
		label.setFont("Monaco-bold-24");
		double labelX = box.getX() + box.getWidth() / 2 - label.getWidth() / 2;
		double labelY = box.getY() + box.getHeight() / 2 + label.getAscent() / 2;
		label.move(labelX, labelY);
		add(label);
	}

	/* Method: drawBox() */
	/**
	 * Draws and returns a GRect.
	 * @return a GRect
	 */
	private GRect drawBox() {
		GRect box = new GRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		return box;
	}
	
	private final int BOX_WIDTH = 150;
	private final int BOX_HEIGHT = 50;
	
	double Xi, Yi;

}
