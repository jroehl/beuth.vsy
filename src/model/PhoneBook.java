package model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Abstraction of PhoneServer as a storage for the entries
 *
 * @className PhoneServer
 * @author jroehl
 * @date 2016-05-07
 */
public class PhoneBook {

	// Thread-safe variant of ArrayList
	private CopyOnWriteArrayList<String[]> server;

	public PhoneBook() {
		server = new CopyOnWriteArrayList<>();

		server.add(new String[] { "Meier", "4711" });
		server.add(new String[] { "Schmitt", "0815" });
		server.add(new String[] { "Müller", "4711" });
		server.add(new String[] { "Meier", "0816" });
		server.add(new String[] { "von Schulze", "0816" });
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return size
	 */
	public int size() {
		return server.size();
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param column
	 * @return entry
	 */
	public String[] getEntry(int column) {
		return server.get(column);
	}
}
