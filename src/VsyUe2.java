import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.Search;

/**
 * 
 * @author jroehl
 */
public class VsyUe2 {

	static String resultHtml = "";

	public static void main(String[] args) throws Exception {

		URL url = null;
		// TODO: Get args from port
		String host = InetAddress.getLocalHost().getHostName();
		int port = 9865;
		ServerSocket serverSocket = new ServerSocket(port);
		System.err.println("Server listening on: " + host + ":" + port);

		// Infinite loop waits for connection
		while (true) {

			Socket clientSocket = serverSocket.accept();
			System.err.print("Client connected: ");

			// Open conversation
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			/*
			 * Read input streams, get optional references
			 */
			String s = in.readLine();
			System.out.println(s);			
		}

	}

	/**
	 * Reads file index.html from view directory and passes it as a string
	 *
	 * @return
	 * @throws IOException
	 */
	private static void createIndexHtml(BufferedWriter out) throws IOException {
		out.write("HTTP/1.0 200 OK\r\n");
		out.write("Content-Type: text/html\r\n");
		out.write("\r\n");

	}
}
