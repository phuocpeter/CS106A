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
import java.util.*;

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
				changeStatusCommand();
			}
			break;
		case "Change Picture":
			if (!picTextField.getText().equals("")) {
				changePicCommand();
			}
			break;
		case "Add Friend":
			if (!friendTextField.getText().equals("")) {
				addFriendCommand();
			}
			break;
		default:
			break;
		}
		if (currentProfile != null) {
			println("---> Current Profile: " + currentProfile.toString());
		} else {
			println("---> No Current Profile");
		}
	}
    
    /* Method: addFriendCommand() */
    /**
     * Handles add friend command.
     */
    private void addFriendCommand() {
		if (currentProfile == null) {
			promptNoProfile();
			return;
		}
		String name = friendTextField.getText();
		if (!db.containsProfile(name)) {
			println("That friend does not exist");
			return;
		}
		if (friendExist(name, currentProfile)) {
			println("That friend is already in the list");
			return;
		}
		// Adds friend reciprocally
		currentProfile.addFriend(name);
		db.addProfile(currentProfile);
		FacePamphletProfile friendProfile = db.getProfile(name);
		friendProfile.addFriend(currentProfile.getName());
		db.addProfile(friendProfile);
		println("Friend Added");
	}
    
    /* Method: friendExist() */
    /**
     * Checks if the friend is already in the profile's friend list.
     * @param name the name of the friend
     * @param profile the profile to check
     * @return true if the name of the friend already existed.
     */
    private boolean friendExist(String name, FacePamphletProfile profile) {
    	Iterator<String> friendList = profile.getFriends();
		while (friendList.hasNext()) {
			if (friendList.next().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/* Method: changePicCommand() */
    /**
     * Handles change picture command.
     */
    private void changePicCommand() {
		if (currentProfile == null) {
			promptNoProfile();
			return;
		}
		GImage image = null;
		try {
			image = new GImage(picTextField.getText());
			currentProfile.setImage(image);
			db.addProfile(currentProfile);
			println("Picture Updated");
		} catch (ErrorException ex) {
			println("Error with image file: " + ex.getMessage());
		}
	}
    
    /* Method: changeStatusCommand() */
    /**
     * Handles change status command.
     */
    private void changeStatusCommand() {
		if (currentProfile == null) {
			promptNoProfile();
			return;
		}
		currentProfile.setStatus(sttTextField.getText());
		db.addProfile(currentProfile);
		println("Status Updated");
	}

    /* Method: promptNoProfile() */
    /**
     * Prompts the user that there is no profile selected.
     */
	private void promptNoProfile() {
		println("Please select a profile");
	}

	/* Method: lookupCommand() */
    /**
     * Handles lookup command.
     */
    private void lookupCommand() {
    	currentProfile = null;
    	String name = nameTextField.getText();
    	if (db.containsProfile(name)) {
    		FacePamphletProfile profile = db.getProfile(name);
    		println("Lookup: " + profile.toString());
    		currentProfile = profile;
    		return;
    	}
    	println("Lookup: profile with name " + name + " does not exist");
	}

	/* Method: deleteCommand() */
    /**
     * Handles delete command.
     */
    private void deleteCommand() {
    	currentProfile = null;
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
    	currentProfile = null;
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
    	currentProfile = profile;
	}

	private JTextField nameTextField, sttTextField, friendTextField, picTextField;
    private FacePamphletDatabase db;
    private FacePamphletProfile currentProfile = null;
}
