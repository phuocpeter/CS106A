/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    setupInteractors();
	    setupDatabase();
	    graph = new NameSurferGraph();
	    add(graph);
	}

	/* Method: setupDatabase() */
	/**
	 * Setups database.
	 */
	private void setupDatabase() {
		try {
			database = new NameSurferDataBase(NAMES_DATA_FILE);
		} catch (IOException e) {
			//println("Error: " + e.getMessage());
		}
	}
	
	/* Method: setupInteractors() */
	/**
	 * Adds label, text field and buttons to the SOUTH of the window.
	 */
	private void setupInteractors() {
		nameLabel = new JLabel("Name");
		add(nameLabel, SOUTH);
		nameTextField = new JTextField(12);
		nameTextField.addActionListener(this);
		add(nameTextField, SOUTH);
		graphBtn = new JButton("Graph");
		add(graphBtn, SOUTH);
		clearBtn = new JButton("Clear");
		add(clearBtn, SOUTH);
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * Responds to the interactions of the user.
 */
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == nameTextField) || (e.getSource() == graphBtn)) {
			String name = nameTextField.getText();
			NameSurferEntry entry = database.findEntry(name);
			if (entry == null) {
				//println("Name not found in the database.");
				return;
			}
			//println("Graph: " + entry.toString());
			return;
		}
		if (e.getSource() == clearBtn) {
			//println("Clear screen");
		}
	}
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton graphBtn, clearBtn;
	private NameSurferDataBase database;
	private NameSurferGraph graph;
	
}
