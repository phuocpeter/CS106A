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

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		setupInteractors();
		this.addActionListeners();
		
		canvas = new FacePamphletCanvas();
		add(canvas);
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
			canvas.displayProfile(currentProfile);
			canvas.showMessage(name + " does not exist");
			return;
		}
		if (friendExist(name, currentProfile)) {
			canvas.displayProfile(currentProfile);
			canvas.showMessage(currentProfile.getName() + " already has " + name + " as a friend");
			return;
		}
		// Adds friend reciprocally
		currentProfile.addFriend(name);
		db.addProfile(currentProfile);
		FacePamphletProfile friendProfile = db.getProfile(name);
		friendProfile.addFriend(currentProfile.getName());
		db.addProfile(friendProfile);
		canvas.displayProfile(currentProfile);
		canvas.showMessage(name + " added as a friend");
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
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Picture Updated");
		} catch (ErrorException ex) {
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Error with image file: " + ex.getMessage());
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
		canvas.displayProfile(currentProfile);
		canvas.showMessage("Status updated to " + currentProfile.getStatus());
	}

    /* Method: promptNoProfile() */
    /**
     * Prompts the user that there is no profile selected.
     */
	private void promptNoProfile() {
		canvas.displayProfile(currentProfile);
		canvas.showMessage("Please select a profile");
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
    		currentProfile = profile;
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Displaying " + name);
    		return;
    	}
    	canvas.displayProfile(currentProfile);
    	canvas.showMessage("A profile with name " + name + " does not exist");
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
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Profile of " + name + " deleted");
    		return;
    	}
    	canvas.displayProfile(currentProfile);
    	canvas.showMessage("A profile with name " + name + " does not exist");
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
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with name " + name + " already exists");
    		return;
		}
    	profile = new FacePamphletProfile(name);
    	db.addProfile(profile);
    	currentProfile = profile;
    	canvas.displayProfile(currentProfile);
    	canvas.showMessage("New profile created");
	}

    private FacePamphletCanvas canvas;
	private JTextField nameTextField, sttTextField, friendTextField, picTextField;
    private FacePamphletDatabase db;
    private FacePamphletProfile currentProfile = null;
}
