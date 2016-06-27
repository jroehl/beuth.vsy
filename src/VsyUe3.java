import controller.Search;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jroehl
 */
public class VsyUe3 {

    static String resultHtml = "";

    /**
     * Main - creates the html and processes the input
     *
     * @return
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 9865;
        String host = InetAddress.getLocalHost().getHostName();
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            host = args[1];
        }
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server listening on: " + host + ":" + port);
        boolean serverExit = false;
        // Infinite loop awaits connection
        while (true) {

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: ");

            // Open conversation
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                                                                /*
																 * Read input streams, get optional references
																 */
            String s = in.readLine();
            System.out.println("Incoming: " + s);
            HashMap<String, String> map = new HashMap<>();
            if (s.startsWith("GET /favicon")) {
                System.out.println("Skipping: Fav-Request");
                in.close();
                continue;
            }
            if (s.contains("?")) {
                System.out.println("Incoming: Http-Request");
                String[] get = s.split(" ");
                String[] l = get[1].substring(2).split("&");
                for (String str : l) {
                    String[] k = str.split("=");
                    if (k.length > 1) {
                        map.put(k[0], URLDecoder.decode(k[1], "UTF-8"));
                    } else {
                        map.put(k[0], "");
                    }
                }
            }
            if (map.containsKey("C")) {
                if (map.get("C").equals("Search")) {
                    CopyOnWriteArrayList<String[]> results = new CopyOnWriteArrayList<String[]>();

                    Thread nameThread = null;
                    Thread phoneThread = null;
                    String searchString = "";

                    if (!map.get("A").isEmpty()) {
                        searchString = map.get("A");
                        if ((map.get("A").matches("^[a-zA-ZäöüÄÖÜ]+[a-zA-ZäöüÄÖÜ\\s]*"))) {
                            nameThread = new Thread(new Search(searchString, 0, results), "search-name");
                            nameThread.start();
                        } else {
                            results.add(new String[]{"\"" + searchString + "\"", "is not a valid string!"});
                        }
                    }

                    if (!map.get("B").isEmpty()) {
                        searchString = map.get("B");
                        if ((map.get("B").matches("\\d+"))) {
                            phoneThread = new Thread(new Search(searchString, 1, results), "search-number");
                            phoneThread.start();
                        } else {
                            results.add(new String[]{"\"" + searchString + "\"", "is not a valid number!"});
                        }
                    }

                    // Join search threads
                    try {
                        if (nameThread != null) {
                            nameThread.join();
                        }
                        if (phoneThread != null) {
                            phoneThread.join();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (results.size() == 0) {
                        results.add(new String[]{"No result found for", "\"" + searchString + "\""});
                    }

                    // Output of the result as html table
                    resultHtml = "<div style=\" background-color: rgba(88, 88, 88, .4); padding: 10px; border-radius: 8px; width: 300px; margin: 30px auto;\"><table><tr><th style=\"text-align: left;\">Name</th><th style=\"text-align: left;\">Number</th></tr>";
                    for (String[] row : results) {
                        resultHtml += "<tr><td>" + row[0] + "</td>";
                        if (row.length > 1) {
                            resultHtml += "<td>" + row[1] + "</td></tr>";
                        }
                    }
                    resultHtml += "</table></div>";

                    // Reinitialise result list
                    results = new CopyOnWriteArrayList<String[]>();
                } else if (map.get("C").equals("Close server")) {
                    System.err.println("Server was closed");
                    resultHtml = "</br><h1 style=\"text-align: center\">Good bye</h1><h2 style=\"text-align: center\">Server was closed</h2>";
                    serverExit = true;
                } else if (map.get("C").equals("Reset")) {
                    resultHtml = "";
                }

            }
            System.out.println("Request is processed");
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Phonebook VSY</title>");
            out.println("</head>");
            out.println("<html>");
            out.println("<body>");
            if (!serverExit) {
                createForm(out, port, host);
            }
            out.println(resultHtml);
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
            in.close();
            if (serverExit) {
                break;
            }
        }

    }

    /**
     * Creates form for processing inputs
     *
     * @return
     * @throws IOException
     */
    private static void createForm(PrintWriter out, int port, String host) throws IOException {
        out.println("<form method=get action=\"http://" + host + ":" + port + "\">");
        out.println("<div style=\"background-color: rgba(88, 88, 88, .4); padding: 10px; border-radius: 8px; width: 300px; height: 70px; margin: 30px auto;\">");
        out.println("<table style=\"width: 100%;\">");
        out.println("<tr>");
        out.println("<td style=\"font-weight: bold;\" valign=top>Name</td>");
        out.println("<td><input style=\"width: 232px;\" name=A></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td style=\"font-weight: bold;\" valign=top>Number</td>");
        out.println("<td><input style=\"width: 232px;\" name=B></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<table style=\"width: 100%;\">");
        out.println("<tr>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td valign=top><input style=\"width: 100%;\" type=submit name=C value=Search></td>");
        out.println("<td><input style=\"width: 100%;\" type=submit name=C value=Reset></td>");
        out.println("<td><input style=\"width: 100%;\" type=submit name=C value=\"Close server\"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</div>");
        out.println("</form>");
    }
}
