package _02_Chat_Application;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	DataOutputStream dO;
	DataInputStream dI;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

}
