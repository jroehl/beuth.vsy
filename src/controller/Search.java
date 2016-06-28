package controller;

import model.PhoneBook;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class is called by a thread and searches a list - passed as an argument
 *
 * @author jroehl
 * @className SearchTelefonserver
 * @date 2016-05-07
 */
public class Search implements Runnable {

    private String str;
    private int column;
    private CopyOnWriteArrayList<String[]> results;
    private PhoneBook phoneBook;

    /**
     * Configures the search through a constructor with a searchstring and the column to be searched
     *
     * @param str
     * @param column
     * @param results
     */
    public Search(String str, int column, CopyOnWriteArrayList<String[]> results, PhoneBook phoneBook) {
        this.str = str;
        this.column = column;
        this.results = results;
        this.phoneBook = phoneBook;
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

        for (int i = 0; i < this.phoneBook.size(); i++) {
            if (this.phoneBook.getEntry(i)[column].equals(str)) {
                results.add(this.phoneBook.getEntry(i));
            }
        }

        if (results.size() == 0) {
            results.add(new String[]{"The search for \"" + str + "\" returned no result"});
        }
    }
}
