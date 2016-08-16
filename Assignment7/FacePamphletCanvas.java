/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel msgLabel = new GLabel(msg);
		msgLabel.setFont(MESSAGE_FONT);
		msgLabel.setLocation(getWidth() / 2 - msgLabel.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		add(msgLabel);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		this.removeAll();
		if (profile == null) return;
		// Name
		GLabel nameLabel = new GLabel("");
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setLabel(profile.getName());
		nameLabel.setColor(Color.BLUE);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent());
		// Image
		GImage image = profile.getImage();
		if (image == null) {
			GRect placeholder = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			placeholder.setLocation(LEFT_MARGIN, IMAGE_MARGIN + nameLabel.getY());
			add(placeholder);
			// No Image Text
			GLabel text = new GLabel("No Image");
			text.setFont(PROFILE_IMAGE_FONT);
			text.setLocation(placeholder.getX() + placeholder.getWidth() / 2 - text.getWidth() / 2,
								placeholder.getY() + placeholder.getHeight() / 2);
			add(text);
		} else {
			image.setLocation(LEFT_MARGIN, IMAGE_MARGIN + nameLabel.getY());
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image);
		}
		// Status
		GLabel status = new GLabel("No current status");
		status.setFont(PROFILE_STATUS_FONT);
		if (!profile.getStatus().equals("")) {
			status.setLabel(profile.getName() + " is " + profile.getStatus());
		}
		status.setLocation(LEFT_MARGIN, IMAGE_MARGIN + nameLabel.getY() + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		add(status);
		// Friends
		GLabel friendHeader = new GLabel("Friends:");
		friendHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendHeader.setLocation(this.getWidth() / 2, IMAGE_MARGIN + nameLabel.getY());
		add(friendHeader);
		// Friends List
		int coefficient = 1;
		Iterator<String> friendsList = profile.getFriends();
		while (friendsList.hasNext()) {
			GLabel entry = new GLabel(friendsList.next());
			entry.setFont(PROFILE_FRIEND_FONT);
			entry.setLocation(this.getWidth() / 2, friendHeader.getY() + coefficient * entry.getHeight());
			add(entry);
			coefficient++;
		}
	}
	
}
