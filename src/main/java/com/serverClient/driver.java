package com.serverClient;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class driver {

	public static void main(String[] args) {

		Scanner scrn = new Scanner(System.in);
		System.out.println("What is the ip address you are tying to reach?");

		// addy can be anything thinking port is 55555 for 1st up then 55556 for secned
		String serverAddy = scrn.next();
		int port = 55555;

		// trying to connection to an already opened connection
		try (Socket socket = new Socket(serverAddy, port);

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

			String userInput;
			System.out.println("Connected to server, Type messages to send.:");

			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
			}

			// if there is no open connection it will try to open one
		} catch (Exception noConnection) {
			System.out.println("No connection was open, attempting to open a connetion");

			try (ServerSocket serverSocket = new ServerSocket(port);) {

				System.out.println("Server is linsting on port " + port);

				while (true) {
					Socket clientSocket = serverSocket.accept();
					System.out.println("New clinet connected");

					receiveCode receiving = new receiveCode(clientSocket);
					receiving.start();

				}

			} catch (Exception cantMakeConnection) {
				System.out.println("There was no open connection and cound not make any new connection");
				noConnection.printStackTrace();
				cantMakeConnection.printStackTrace();
			}
		}
		scrn.close();
	}
}
