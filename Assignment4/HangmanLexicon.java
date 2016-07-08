/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.*;
import acm.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		try {
			BufferedReader file = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String word = file.readLine();
				if (word == null) break;
				wordList.add(word);
			}
			file.close();
		} catch (IOException ex) {
			throw new ErrorException("Failed to read file.");
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	};
	
	private ArrayList<String> wordList = new ArrayList<String>();;
}
