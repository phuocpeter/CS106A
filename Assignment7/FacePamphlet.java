/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;

import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		setupInteractors();
		this.addActionListeners();
		
		db = new FacePamphletDatabase();
    }
  
	/* Method: setupInteractors() */
	/**
	 * Setups the interactors.
	 */
    private void setupInteractors() {
		// NORTH
    	JLabel nameLabel = new JLabel("Name");
    	add(nameLabel, NORTH);
    	nameTextField = new JTextField(TEXT_FIELD_SIZE);
    	nameTextField.addActionListener(this);
    	add(nameTextField, NORTH);
    	JButton addButton = new JButton("Add");
    	add(addButton, NORTH);
    	JButton delButton = new JButton("Delete");
    	add(delButton, NORTH);
    	JButton lookupButton = new JButton("Lookup");
    	add(lookupButton, NORTH);
    	
    	// WEST
    	sttTextField = new JTextField(TEXT_FIELD_SIZE);
    	sttTextField.setActionCommand("Change Status");
    	sttTextField.addActionListener(this);
    	add(sttTextField, WEST);
    	JButton sttButton = new JButton("Change Status");
    	add(sttButton, WEST);
    	JLabel emptyLabel = new JLabel(EMPTY_LABEL_TEXT);
    	add(emptyLabel, WEST);
    	
    	picTextField = new JTextField(TEXT_FIELD_SIZE);
    	picTextField.setActionCommand("Change Picture");
    	picTextField.addActionListener(this);
    	add(picTextField, WEST);
    	JButton picButton = new JButton("Change Picture");
    	add(picButton, WEST);
    	JLabel emptyLabel2 = new JLabel(EMPTY_LABEL_TEXT);
    	add(emptyLabel2, WEST);
    	
    	friendTextField = new JTextField(TEXT_FIELD_SIZE);
    	friendTextField.setActionCommand("Add Friend");
    	friendTextField.addActionListener(this);
    	add(friendTextField, WEST);
    	JButton friendButton = new JButton("Add Friend");
    	add(friendButton, WEST);
    	JLabel emptyLabel3 = new JLabel(EMPTY_LABEL_TEXT);
    	add(emptyLabel3, WEST);
	}


	/**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "Add":
			if (!nameTextField.getText().equals("")) {
				addCommand();
			}
			break;
		case "Delete":
			if (!nameTextField.getText().equals("")) {
				deleteCommand();
			}
			break;
		case "Lookup":
			if (!nameTextField.getText().equals("")) {
				lookupCommand();
			}
			break;	
		case "Change Status":
			if (!sttTextField.getText().equals("")) {
				println("Change Status: " + sttTextField.getText());
			}
			break;
		case "Change Picture":
			if (!picTextField.getText().equals("")) {
				println("Change Picture: " + picTextField.getText());
			}
			break;
		case "Add Friend":
			if (!friendTextField.getText().equals("")) {
				println("Add Friend: " + friendTextField.getText());
			}
			break;
		default:
			break;
		}
	}
    
    /* Method: lookupCommand() */
    /**
     * Handles lookup command.
     */
    private void lookupCommand() {
    	String name = nameTextField.getText();
    	if (db.containsProfile(name)) {
    		FacePamphletProfile profile = db.getProfile(name);
    		println("Lookup: " + profile.toString());
    		return;
    	}
    	println("Lookup: profile with name " + name + " does not exist");
	}

	/* Method: deleteCommand() */
    /**
     * Handles delete command.
     */
    private void deleteCommand() {
    	String name = nameTextField.getText();
    	if (db.containsProfile(name)) {
    		db.deleteProfile(name);
    		println("Delete: profile of " + name + " deleted");
    		return;
    	}
    	println("Delete: profile with name " + name + " does not exist");
	}

	/* Method: addCommand() */
    /**
     * Handles add command.
     */
    private void addCommand() {
    	FacePamphletProfile profile;
    	String name = nameTextField.getText();
    	if (db.containsProfile(name)) {
    		profile = db.getProfile(name);
    		println("Add: profile for " + name + " already exists: " + profile.toString());
    		return;
		}
    	profile = new FacePamphletProfile(name);
    	db.addProfile(profile);
    	println("Add: new profile: " + profile.toString());
	}

	private JTextField nameTextField, sttTextField, friendTextField, picTextField;
    private FacePamphletDatabase db;
}
