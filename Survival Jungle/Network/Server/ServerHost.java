package Network.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Network.Client.Client;

public class ServerHost implements Runnable {
	private ServerSocket ServerSocket;
	private ArrayList<Client> Clients;
	private final JPanel errorPanel = new JPanel();

	ServerHost(ServerSocket ServerSocket, ArrayList<Client> Clients){
		this.ServerSocket = ServerSocket;
		this.Clients = Clients;
	}
	
	public void run() {
		int i = 0;
		while (true) {
			Socket Socket;
			try {
				Socket = ServerSocket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
				DataOutputStream output = new DataOutputStream(Socket.getOutputStream());

				Client Client = new Client(i, Socket.getInetAddress());
				Clients.add(Client);
				System.out.println("Client : " + Client.getUserID() + " connected from IP " + Client.getIP() + " .");
				Client.sendMessage("ClientID:" + i);
				// Server send client id to client

				ServerSender ServerSender = new ServerSender(output,Client);
				Thread SenderThread = new Thread(ServerSender);
				SenderThread.start();
				System.out.println("Output stream for client " + Client.getUserID() + " established.");

				
//				ServerSender ServerSender = new ServerSender(output,Client);
//				Thread SenderThread = new Thread(ServerSender);
//				SenderThread.start();
//				System.out.println("Output stream for client " + Client.getUserID() + " established.");

				i++;
			} catch (Exception ex) {
				System.out.println("Server host error : " +ex.getMessage());
				break;
			}
		}
	}
}
