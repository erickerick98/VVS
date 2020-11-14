package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {
	protected Socket clientSocket;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
		    serverSocket = new ServerSocket(55555); 
		} catch (IOException e) {
		    System.err.println("Could not listen on port: 55555.");
		    System.exit(1);
		}

		Socket clientSocket = null; 
		try {
		    clientSocket = serverSocket.accept();

		    if(clientSocket != null) {           
		        System.out.println("Connected");
		    }
		} catch (IOException e) {
		    System.err.println("Accept failed.");
		    System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: text/html");
		out.println("\r\n");
		out.println("<p> Hello world </p>");
		out.flush();

		out.close();
		clientSocket.close();
		serverSocket.close();
	}
	private WebServer(Socket clientSoc) {
		clientSocket = clientSoc;
		start();
	}

	/*public void run() {
		System.out.println("New Communication Thread Started");

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println("Server: " + inputLine);
				out.println(inputLine);

				if (inputLine.trim().equals(""))
					break;
			}

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}*/
}