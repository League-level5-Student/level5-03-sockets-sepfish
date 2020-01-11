package _02_Chat_Application;

import java.io.*;
import java.net.Socket;

import javax.swing.*;

import _00_Click_Chat.networking.Server;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	DataOutputStream dO;
	DataInputStream dI;
	
	JFrame frame;
	JPanel panel;
	JLabel label;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	private void setup() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Client");
		label.setText("Awaiting confirmation...");
		panel.add(label);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void run() {
		try {
			connection = new Socket (ip, port);
			setup();
			DataOutputStream dO = new DataOutputStream(connection.getOutputStream());
			String s = JOptionPane.showInputDialog("Send a message to the server: ");
			dO.writeUTF(s);
			dO.flush();
			DataInputStream dI = new DataInputStream(connection.getInputStream());
			label.setText("Message: " + dI.readUTF());
			frame.pack();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
