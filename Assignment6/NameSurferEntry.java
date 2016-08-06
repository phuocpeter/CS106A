/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		reader = new Scanner(line);
		parseName();
		parseRanks();
	}

	/* Method: parseName() */
	/**
	 * Assigns the first parsed word to name.
	 */
	private void parseName() {
		name = reader.next();
	}
	
	/* Method: parseRanks() */
	/**
	 * Assigns the line's parsed ranks to ranks array.
	 */
	private void parseRanks() {
		int i = 0;
		while (reader.hasNext()) {
			int value = Integer.parseInt(reader.next());
			ranks[i] = value;
			i++;
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int index = decade / 10 - START_DECADE / 10;
		return ranks[index];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String desc = name + " [";
		for (int i = 0; i < 11; i++) {
			desc += ranks[i];
			if (i != 10) desc += " ";
		}
		desc += "] ";
		return desc;
	}
	
	private String name;
	private int[] ranks = new int[11];
	private Scanner reader;
}

