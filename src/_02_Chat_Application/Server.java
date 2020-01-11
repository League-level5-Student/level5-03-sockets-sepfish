package _02_Chat_Application;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.swing.*;

public class Server {
	private int port;

	private ServerSocket server;
	private Socket connection;

	DataOutputStream dO;
	DataInputStream dI;

	JFrame frame;
	JPanel panel;
	JLabel label;
	JLabel info;

	public Server(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR :(";
		}
	}

	private void setup() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		info = new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Server");
		label.setText("Awaiting client...");
		info.setText("IP Address: " + getIPAddress() + " Port: " + port);
		panel.add(label);
		panel.add(info);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void ugh() {
		try {
			server = new ServerSocket(port);
			setup();
			JOptionPane.showMessageDialog(null, "Server is waiting for client to connect...");
			connection = server.accept();
		} catch (IOException e) {
			System.out.println("You're bad");
		}
	}

	public void run() {
		boolean b = true;
		ugh();
		while (b) {
			try {
				dI = new DataInputStream(connection.getInputStream());
				label.setText("Message: " + dI.readUTF());
				frame.pack();
				dO = new DataOutputStream(connection.getOutputStream());
				String s = JOptionPane.showInputDialog("Send a message to the client: ");
				dO.writeUTF(s);
				dO.flush();
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
