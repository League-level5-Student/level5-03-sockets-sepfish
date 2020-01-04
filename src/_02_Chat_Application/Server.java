package _02_Chat_Application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

public class Server {
	private int port;

	private ServerSocket server;
	private Socket connection;

	DataOutputStream dO;
	DataInputStream dI;

	public Server(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void run() {
		boolean b = true;
			while(b) {
				try {
					server = new ServerSocket(port);
					JOptionPane.showMessageDialog(null, "Server is waiting for client to connect...");
					connection = server.accept();
					JOptionPane.showMessageDialog(null, "Client has connected.");
					dI = new DataInputStream(connection.getInputStream());
					System.out.println(dI.readUTF());
					dO = new DataOutputStream(connection.getOutputStream());
					String s = JOptionPane.showInputDialog("Send a message to the client: ");
					dO.writeUTF(s);
				} catch (SocketTimeoutException ste) {
					JOptionPane.showMessageDialog(null, "SocketTimeoutException found: " + ste.getMessage());
					b = false;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "IOException found: " + e.getMessage());
					b = false;
				}
			}
			try {
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
