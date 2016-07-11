/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		while (round > 0) {
			for (int i = 1; i <= nPlayers; i++) {
				display.waitForPlayerToClickRoll(i);
				processPlayerTurn(i);
			}
		}
	}
		
	/* Method: processPlayerTurn() */
	/**
	 * Processes the turn of a player.
	 * @param player player's number
	 */
	private void processPlayerTurn(int player) {
		rollDices();
		int choice = display.waitForPlayerToSelectCategory();
		boolean valid = false;
		while (!valid) {
			valid = checkCategory(choice, player);
		}
	}

	/* Method: checkCategory() */
	/**
	 * Check if the category chosen is valid
	 * @param choice category chosen
	 * @param player player number
	 * @return false if the category doesn't valid
	 */
	private boolean checkCategory(int choice, int player) {
		if (chosenCategories.contains(choice)) return false;
		switch(choice) {
		case ONES:
		case TWOS:
		case THREES:
		case FOURS:
		case FIVES:
		case SIXES:
			int score = 0;
			for (int dice = 0; dice < N_DICE; dice++) {
				if (dices[dice] == choice) {
					score += choice;
				}
			}
			display.updateScorecard(choice, player, score);
			break;
		case THREE_OF_A_KIND:
		case FOUR_OF_A_KIND:
		case FULL_HOUSE:
		case SMALL_STRAIGHT:
		case LARGE_STRAIGHT:
		case YAHTZEE:
		case CHANCE:
			break;
		case UPPER_SCORE:
		case UPPER_BONUS:
		case LOWER_SCORE:
		case TOTAL:
			break;
		}
		return true;
	}

	/* Method: rollDices() */
	/**
	 * Rolls dices.
	 */
	private void rollDices() {
		for (int roll = 1; roll <= 3; roll++) {
			if (roll != 1 && noDiceSelected()) break;
			for (int dice = 0; dice < N_DICE; dice++) {
				if ((display.isDieSelected(dice)) || (dices[dice] == 0)) {
					dices[dice] = rgen.nextInt(1, 6);
				}
			}
			display.displayDice(dices);
			if (roll != 3) display.waitForPlayerToSelectDice();
		}
	}

	/* Method: noDiceSelected() */
	/**
	 * Checks if the user has selected any dice.
	 * @return true if none of the dice is selected
	 */
	private boolean noDiceSelected() {
		for (int dice = 0; dice < N_DICE; dice++) {
			if (display.isDieSelected(dice)) return false;
		}
		return true;
	}

	/* Private instance variables */
	private int nPlayers;
	private int round = N_SCORING_CATEGORIES;
	private int[] dices = new int[N_DICE];
	private ArrayList<Integer> chosenCategories = new ArrayList<Integer>();
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
}
