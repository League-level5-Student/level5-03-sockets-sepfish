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
	
	//this is not actually great look on ButtonClicker --------------------------------------------
	
	public void start() {
		int r = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?");
		if (r == JOptionPane.YES_OPTION) {
			Server s = new Server(8080);
		} else {
			System.out.println("nee");
		}
	}
}
