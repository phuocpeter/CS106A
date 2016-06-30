/*
 * File: GraphicsHierarchy.java
 * ----------------------------
 * This program is a stub for the GraphicsHierarchy problem, which
 * draws a partial diagram of the acm.graphics hierarchy.
 */

import acm.program.*;
import acm.graphics.*;

public class GraphicsHierarchy extends GraphicsProgram {
	
	final int BOX_WIDTH = 150;
	final int BOX_HEIGHT = 50;
	
	double lineXi, lineYi;
	
	public void run() {
		drawGObject();
		drawGLabel();
		drawGLine();
		drawGOval();
		drawGRect();
	}
	
	private void drawLineTo() {
		
	}
	
	private void drawGRect() {
		GRect box = drawBox();
		box.move(this.getWidth() - BOX_WIDTH - BOX_WIDTH / 3, this.getHeight() * 3 / 4);
		drawLabel("GLabel", box);
		drawLineTo();
	}

	private void drawGOval() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 + 9, this.getHeight() * 3 / 4);
		drawLabel("GOval", box);
	}

	private void drawGLine() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 - BOX_WIDTH - 9, this.getHeight() * 3 / 4);
		drawLabel("GLine", box);
	}

	private void drawGLabel() {
		GRect box = drawBox();
		box.move(BOX_WIDTH / 3, this.getHeight() * 3 / 4);
		drawLabel("GLabel", box);
	}

	private void drawGObject() {
		GRect box = drawBox();
		box.move(this.getWidth() / 2 - BOX_WIDTH / 2, this.getHeight() / 4);
		drawLabel("GObject", box);
		lineXi = box.getX() + BOX_WIDTH / 2;
		lineYi = box.getY() + BOX_HEIGHT;
	}
	
	private void drawLabel(String title, GRect box) {
		GLabel label = new GLabel(title);
		label.setFont("Monaco-bold-24");
		double labelX = box.getX() + box.getWidth() / 2 - label.getWidth() / 2;
		double labelY = box.getY() + box.getHeight() / 2 + label.getAscent() / 2;
		label.move(labelX, labelY);
		add(label);
	}

	private GRect drawBox() {
		GRect box = new GRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		return box;
	}

}
