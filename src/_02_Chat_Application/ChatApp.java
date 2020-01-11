package _02_Chat_Application;

import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	public static void main(String[] args) {
		ChatApp c = new ChatApp();
		c.start();
	}
	
	public void start() {
		int r = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "ChatApp", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			Server s = new Server(8080);
			s.run();
		} else {
			String iP = JOptionPane.showInputDialog("Enter IP address:");
			String prt = JOptionPane.showInputDialog("Enter port number: ");
			int port = Integer.parseInt(prt);
			Client c = new Client(iP, port);
			c.run();
		}
	}
}
