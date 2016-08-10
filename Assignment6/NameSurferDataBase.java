/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 * @throws IOException 
 */
	public NameSurferDataBase(String filename) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String line;
		while (true) {
			line = file.readLine();
			if (line == null) break;
			fileContent.add(line);
		}
		file.close();
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		String line = getLineFromName(name);
		if (line == null) {
			return null;
		}
		NameSurferEntry entry = new NameSurferEntry(line);
		return entry;
	}
	
	/* Method: getLineFromName() */
	/**
	 * Looks for data associated with the specified name.
	 * @param name the name of data to look for
	 * @return a line of data with name
	 */
	private String getLineFromName(String name) {
		Iterator<String> fileIterator = fileContent.iterator();
		while (fileIterator.hasNext()) {
			String line = fileIterator.next();
			String tName = getNameFromLine(line);
			if (tName.equals(name)) {
				return line;
			}
		}
		return null;
	}

	/* Method: getNameFromLine(String) */
	/**
	 * Parses the name associated with the data line.
	 * @param line a line of data with name
	 * @return name associated with the data line
	 */
	private String getNameFromLine(String line) {
		Scanner reader = new Scanner(line);
		if (reader.hasNext()) {
			String name = reader.next();
			reader.close();
			return name;
		} else {
			reader.close();
			return null;
		}
	}

	private ArrayList<String> fileContent = new ArrayList<String>();
}

