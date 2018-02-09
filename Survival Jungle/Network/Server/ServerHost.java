package Network.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Network.Client.Client;

public class ServerHost implements Runnable {
	private ServerSocket ServerSocket;
	private ArrayList<Client> Clients;

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

				ServerSender ServerSender = new ServerSender(output,Clients,i);
				Thread SenderThread = new Thread(ServerSender);
				SenderThread.start();
				System.out.println("Output stream for client " + Client.getUserID() + " established.");

				ServerReceiver ServerReceiver = new ServerReceiver(input,Clients,i);
				Thread ReceiverThread = new Thread(ServerReceiver);
				ReceiverThread.start();
				System.out.println("Input stream for client " + Client.getUserID() + " established.");

				i++;
			} catch (Exception ex) {
				System.out.println("Server host error : " +ex.getMessage());
				break;
			}
		}
	}
}
