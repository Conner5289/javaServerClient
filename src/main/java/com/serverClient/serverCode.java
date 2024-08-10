package com.serverClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverCode extends Thread {

	private Socket clientSocket;

	public serverCode(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

			String message;

			while ((message = in.readLine()) != null) {
				System.out.println("Recevied: " + message);
				out.println("Echo: " + message);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
