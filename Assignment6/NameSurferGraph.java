/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		this.removeAll();
		drawGrid();
		drawLabels();
	}
	
	/* Method: drawLabels() */
	/**
	 * Draws the decade labels.
	 */
	private void drawLabels() {
		int height = this.getHeight();
		int width = this.getWidth();
		double spacePerDecade = width / NDECADES;
		
		int coefficient = 0;
		for (int year = 1900; year <= 2000; year += 10) {
			GLabel label = new GLabel("" + year);
			double x = (coefficient * spacePerDecade) + (spacePerDecade / 2) - (label.getWidth() / 2);
			double y = (height - (GRAPH_MARGIN_SIZE / 2)) + (label.getAscent() / 2);
			label.setLocation(x, y);
			add(label);
			coefficient++;
		}
	}

	/* Method: drawGrid() */
	/**
	 * Draws the grids.
	 */
	private void drawGrid() {
		int height = this.getHeight();
		int width = this.getWidth();
		double spacePerDecade = width / NDECADES;
		
		// Draw horizontal borders
		GLine topBorder = new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
		GLine bottomBorder = new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE);
		add(topBorder);
		add(bottomBorder);
		
		// Draw decade separators
		for (int i = 1; i < NDECADES; i++) {
			GLine separator = new GLine(i * spacePerDecade, 0, i * spacePerDecade, height);
			add(separator);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

}
