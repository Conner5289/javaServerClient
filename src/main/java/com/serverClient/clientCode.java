package com.serverClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientCode {

	public void clientStart() {

		String serverAddy = "192.168.1.3";
		int port = 55555;

		try (Socket socket = new Socket(serverAddy, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

			String userInput;
			System.out.println("Connected to server, Type messages to send.:");

			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("Server respnes: " + in.readLine());
			}

		} catch (Exception e) {
			e.printStackTrace();
			;
		}

	}

}
