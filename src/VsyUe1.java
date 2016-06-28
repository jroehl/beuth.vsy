//import controller.*;
//import java.util.*;
//import java.util.concurrent.*;
//
//
///**
// * Main class for the user interface of the VdyUe1
// *
// * @className SearchTelefonserver
// * @author jroehl
// * @date 2016-05-07
// */
//public class VsyUe1 {
//	private static Scanner scanner = new Scanner(System.in);
//
//	/**
////	 * Entry point of the programm, main functions
//	 *
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		System.out.println("Please choose: \"search\" or \"quit\":");
//		switch (scanner.nextLine()) {
//			case "search":
//				search();
//				break;
//			case "quit":
//				System.exit(0);
//				break;
//		}
//		main(args);
//
//	}
//
//	/**
//	 * Search method starts the query for the used method, starts, waits for threads and outputs the results
//	 *
//	 */
//	private static void search() {
//		CopyOnWriteArrayList<String[]> results = new CopyOnWriteArrayList<String[]>();
//
//		System.out.println("Search for \"name\", \"phone\" or \"both\": ");
//		String name = null;
//		String phone = null;
//		switch (scanner.nextLine()) {
//			case "name":
//				name = checkName();
//				break;
//			case "phone":
//				phone = checkPhone();
//				break;
//			case "both":
//				name = checkName();
//				phone = checkPhone();
//				break;
//			default:
//				search();
//		}
//		Thread phoneThread = null;
//		Thread nameThread = null;
//		if (name != null) {
//			nameThread = new Thread(new Search(name, 0, results),  "search-name");
//			nameThread.start();
//		}
//		if (phone != null) {
//			phoneThread = new Thread(new Search(phone, 1, results),  "search-phone");
//			phoneThread.start();
//		}
//
//		try {
//			if (phoneThread != null) {
//				phoneThread.join();
//			}
//			if (nameThread != null) {
//				nameThread.join();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Results: ");
//		for (String[] row : results) {
//			System.out.print(row[0]);
//			if (row.length > 1) {
//				System.out.print(", " + row[1]);
//			}
//			System.out.println();
//		}
//	}
//
//	/**
//	 * Asks for phonenumber and checks it for valid format
//	 *
//	 * @return number
//	 */
//	private static String checkPhone() {
//		System.out.println("Enter phonenumber: ");
//		String number = scanner.nextLine();
//		if (number.matches("\\d+")) {
//			return number;
//		}
//		System.out.println("\"" + number + "\" is not a valid number (no letters etc.)");
//		checkPhone();
//		return null;
//	}
//
//	/**
//	 * Asks for name and checks it for valid format
//	 *
//	 * @return name
//	 */
//	private static String checkName() {
//		System.out.println("Enter name: ");
//		String name = scanner.nextLine();
//		if (name.matches("^[a-zA-ZäöüÄÖÜ]+[a-zA-ZäöüÄÖÜ\\s]*")) {
//			return name;
//		}
//		System.out.println("\"" + name + "\" is not a valid name (no numbers etc.)");
//		checkName();
//		return null;
//	}
//
//
//}
