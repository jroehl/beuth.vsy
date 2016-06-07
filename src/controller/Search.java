package controller;

import java.util.concurrent.CopyOnWriteArrayList;

import model.PhoneServer;

/**
 * Class is called by a thread and searches a list - passed as an argument
 * 
 * @className SearchTelefonserver
 * @author jroehl
 * @date 2016-05-07
 */
public class Search implements Runnable {

	private PhoneServer server = new PhoneServer();

	private String str;
	private int column;
	private CopyOnWriteArrayList<String[]> results;

	/**
	 * Configures the search through a constructor with a searchstring and the column to be searched
	 * 
	 * @param str
	 * @param column
	 * @param results
	 */
	public Search(String str, int column, CopyOnWriteArrayList<String[]> results) {
		this.str = str;
		this.column = column;
		this.results = results;
	}

	@Override
	public void run() {
		lookingFor();
	}

	/**
	 * Iterates through the PhoneServer and matches the entries to the defined searchstring. Matches are returned as as string.
	 * 
	 * @return
	 */
	private void lookingFor() {

		for (int i = 0; i < server.size(); i++) {
			if (server.getEntry(i)[column].equals(str)) {
				results.add(server.getEntry(i));
			}
		}

		if (results.size() == 0) {
			results.add(new String[] { "The search for \"" + str + "\" returned no result" });
		}
	}
}
