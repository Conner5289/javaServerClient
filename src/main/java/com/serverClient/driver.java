package com.serverClient;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class driver {

	public static void main(String[] args) {

		Scanner scrn = new Scanner(System.in);
		System.out.println("1 for Server or 2 for client");
		int serverClient = scrn.nextInt();

		switch (serverClient) {
			case 1:

				int port = 55555;
				try (ServerSocket serverSocket = new ServerSocket(port)) {
					System.out.println("Server is listining on port " + port);

					while (true) {

						Socket clientSocket = serverSocket.accept();
						System.out.println("New client connected");

						serverCode server = new serverCode(clientSocket);
						server.start();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 2:
				clientCode client = new clientCode();
				client.clientStart();

				break;
			default:
				break;
		}

		scrn.close();
	}

}
