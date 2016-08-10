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
		entriesList = new ArrayList<NameSurferEntry>();
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entriesList.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entriesList.add(entry);
		update();
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
		drawList();
	}
	
	/* Method: drawList() */
	/**
	 * Draws all entries in the list.
	 */
	private void drawList() {
		Iterator<NameSurferEntry> entriesIterator = entriesList.iterator();
		while (entriesIterator.hasNext()) {
			graphEntry(entriesIterator.next());
		}
	}

	/* Method: increaseColourIndex() */
	/**
	 * Determines the next colour to use.
	 * @param index the index of the entry in entriesList
	 * @return index of the colour to use
	 */
	private int getColourIndex(int index) {
		index++;
		int newIndex = index % 4;
		if (newIndex == 0) {
			return 3;
		}
		return newIndex - 1;
	}

	/* Method: graphEntry() */
	/**
	 * Graphs the entry on the canvas.
	 * @param entry the entry to graph
	 */
	private void graphEntry(NameSurferEntry entry) {
		int coefficient = 0;
		for (int decade = 1900; decade < 2000; decade += 10) {
			int value = entry.getRank(decade);
			int nextValue = entry.getRank(decade + 10);
			double x1 = coefficient * spacePerDecade;
			double x2 = (coefficient + 1) * spacePerDecade;
			double y1 = (value == 0)? (height - GRAPH_MARGIN_SIZE) : getPointOnGraph(value);
			double y2 = (nextValue == 0)? (height - GRAPH_MARGIN_SIZE) : getPointOnGraph(nextValue);
			GLine line = new GLine(x1, y1, x2, y2);
			
			int currentColourIndex = getColourIndex(entriesList.indexOf(entry));
			
			line.setColor(colourCycle[currentColourIndex]);
			add(line);
			
			// Add label
			String labelText;
			if (value == 0) {
				labelText = entry.getName() + " *";
			} else {
				labelText = entry.getName() + " " + value;
			}
			GLabel label = new GLabel(labelText);
			label.setColor(colourCycle[currentColourIndex]);
			add(label, x1 + 2, y1 - 2);
			coefficient++;
		}
	}

	/* Method: getPointOnGraph() */
	/**
	 * Converts double value into percentage to display on graph.
	 * @param value the value to convert
	 * @return the point to display;
	 */
	private double getPointOnGraph(int value) {
		double graphLineHeight = height - 2 * GRAPH_MARGIN_SIZE;
		double percentage = value * 100.0 / MAX_RANK;
		double valueToGraph = graphLineHeight * percentage / 100;
		double point = GRAPH_MARGIN_SIZE + valueToGraph;
		return point;
	}

	/* Method: drawLabels() */
	/**
	 * Draws the decade labels.
	 */
	private void drawLabels() {
		int coefficient = 0;
		for (int decade = 1900; decade <= 2000; decade += 10) {
			GLabel label = new GLabel("" + decade);
			double x = (coefficient * spacePerDecade) + 2;
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
	public void componentResized(ComponentEvent e) {
		height = this.getHeight();
		width = this.getWidth();
		spacePerDecade = width / NDECADES;
		update(); 
	}
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> entriesList;
	private int height, width;
	private double spacePerDecade;
	private Color[] colourCycle = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
}
