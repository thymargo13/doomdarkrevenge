package Network.Server;

import java.net.ServerSocket;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Network.Client.Client;

public class Server {
	private static Thread ConnectionThread;
	private static ServerSocket ServerSocket;
	private final static HashMap<Integer, Client> Clients= new HashMap<Integer, Client>();
	
	Server(){
		try {
			startServer();
		} catch (Exception ex){
			
		}
	}
	
	private void startServer() {
		
	}
	
	private void stopServer() {
		
	}
	
}
