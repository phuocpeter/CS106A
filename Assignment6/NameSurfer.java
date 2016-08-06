/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    setupInteractors();
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
			println("Graph: " + nameTextField.getText());
			return;
		}
		if (e.getSource() == clearBtn) {
			println("Clear screen");
		}
	}
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton graphBtn, clearBtn;
	
}
