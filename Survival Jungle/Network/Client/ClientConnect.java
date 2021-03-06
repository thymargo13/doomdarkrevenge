package Network.Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Multiplayer.MultiplayerGameState;
import Network.Server.Client;

/**
 * @author Khang Sheong Foong
 * This is a class for the client to connect to a server.
 * The client will create a thread to send message to the server and another thread to receive message from the server.
 */
public class ClientConnect {
	BlockingQueue<String> queue;
	private static Client Client;
	private static Socket socket;
	private static BufferedReader input;
	private static DataOutputStream output;
	private String address;
	private final int Port = 8888;
	private final JPanel errorPanel = new JPanel();
	Thread ReceiverThread, SenderThread;
	private MultiplayerGameState MultiplayerGameState = null;
	boolean isHost = false;
	
	public ClientConnect(Client Client, String address,MultiplayerGameState MultiplayerGameState){
		this.Client = Client;
		this.queue = Client.getQueue();
		this.address = address;
		this.MultiplayerGameState = MultiplayerGameState;
	}
	
	public boolean connectToServer() {
		try {
			socket = new Socket(address, Port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());

			ClientReceiver ClientReceiver = new ClientReceiver(input, MultiplayerGameState);
			ReceiverThread = new Thread(ClientReceiver);
			ReceiverThread.start();
			System.out.println("Client Receiver thread created.");
			
			ClientSender ClientSender = new ClientSender(queue, output);
			SenderThread = new Thread(ClientSender);
			SenderThread.start();
			System.out.println("Client Sender thread created.");
			
		}
		catch (Exception ex) {
//		    JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    try {
				socket.close();
				ReceiverThread.interrupt();
				SenderThread.interrupt();
			}
		    catch (Exception exc) {
			}
			return false;
		}
		return true;
	}
	
	public void disconnectFromServer() {
		try {
			socket.close();
			ReceiverThread.interrupt();
			SenderThread.interrupt();
			System.out.println("Disconnected from server.");
		}
		catch (Exception ex) {
		}
	}
	
	public void sendMessage(String message) {
		queue.add(message+"\n");
	}
}
