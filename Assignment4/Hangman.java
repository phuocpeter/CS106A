/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	/** 
	 * Initialize the canvas to display
	 * alongside the console program.
	 */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
    	canvas.reset();
		println("Welcome to Hangman!");
		try {
			lexicon = new HangmanLexicon();
			setupWord();
			gamePlay();
		} catch (ErrorException ex) {
			println("Error: " + ex.getMessage());
			println("Game Ended.");
		}
	}
    
    /* Method: gamePlay() */
    /** Controls the flow of the game. */
    private void gamePlay() {
		while (!gameOver) {
			canvas.displayWord(guessString);
			printInfoMsg();
			askForInput();
			checkAnswer();
			checkEndGameCondition();
		}
	}
    
    /* Method: checkEndGameCondition() */
    /**
     * Checks if the guesses left is 0 or the word is done
     * guessing to end the game.
     */
    private void checkEndGameCondition() {
		if (guesses == 0) {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
			gameOver = true;
			return;
		}
		if (guessString.equals(word)) {
			println("You guessed the word: " + word);
			println("You win.");
			gameOver = true;
			return;
		}
	}

	/* Method: checkAnswer() */
    /** Checks user's guess with the guessString. */
    private void checkAnswer() {
    	boolean strHasWord = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == currentGuess) {
				replaceStringAtIndex(i, currentGuess);
				strHasWord = true;
			}
		}
		if (!strHasWord) {
			guesses--;
			canvas.noteIncorrectGuess(currentGuess);
			canvas.hang(guesses);
			println("There are no " + currentGuess + "'s in the word.");
		} else {
			println("That guess is correct.");
		}
		canvas.displayWord(guessString);
	}

    /* Method: replaceStringAtIndex() */
    /**
     * Replaces the character at index of string with
     * the character in the parameter.
     * @param index will be replace
     * @param ch character to be replace with
     */
	private void replaceStringAtIndex(int index, char ch) {
		String str = guessString.substring(0, index);
		String str1 = guessString.substring(index + 1);
		guessString = str + ch + str1;
	}

	/* Method: askForInput() */
    /**
     * Gets a char from user. The character
     * must be 1 char only.
     */
    private void askForInput() {
    	String str;
		do {
			str = readLine("Your guess: ");
			if (str.length() > 1 || str.isEmpty()) {
				println("Illegal input!");
			}
		} while(str.length() > 1 || str.isEmpty());
		currentGuess = str.charAt(0);
		currentGuess = Character.toUpperCase(currentGuess);
	}

	/* Method: printInfoMsg() */
    /**
     * Prints the current word guess process and
     * the number of guesses left.
     */
	private void printInfoMsg() {
		println("The word now looks like this: " + guessString);
		println("You have " + guesses + " guesses left.");
	}

	/* Method: setupWord() */
    /** Gets random word from file. */
    private void setupWord(){
		RandomGenerator rgen = RandomGenerator.getInstance();
		int index = rgen.nextInt(0, lexicon.getWordCount() - 1);
		word = lexicon.getWord(index);
		for (int i = 0; i < word.length(); i++) {
			guessString += "-";
		}
	}

    private HangmanLexicon lexicon;
    private HangmanCanvas canvas;
	private int guesses = 8;
	private char currentGuess;
    private String word;
    private String guessString = "";
    private boolean gameOver = false;
}
