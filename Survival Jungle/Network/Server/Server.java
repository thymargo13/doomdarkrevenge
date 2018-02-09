package Network.Server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Network.Client.Client;

public class Server {
	private static Thread HostThread, DiscoveryThread;
	private static ServerSocket ServerSocket;
	private static DatagramSocket DatagramSocket;
	private final static ArrayList<Client> Clients= new ArrayList<Client>();
	private final int ServerPort = 8888;
	private final int DiscoveryPort = 8889;
	private final JPanel errorPanel = new JPanel();

	
	public void startServer() {
		try {
			ServerSocket = new ServerSocket(ServerPort);
			ServerHost Host = new ServerHost(ServerSocket, Clients);
			HostThread = new Thread(Host);
			HostThread.start();
			System.out.println("Server started.");
			
			DatagramSocket = new DatagramSocket(DiscoveryPort, InetAddress.getByName("0.0.0.0"));
			ServerDiscovery ServerDiscovery = new ServerDiscovery(DatagramSocket);
			DiscoveryThread = new Thread(ServerDiscovery);
			DiscoveryThread.start();
			System.out.println("");
		} catch (Exception ex){
		    JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	public void stopServer() {
		try {
            ServerSocket.close();
            DatagramSocket.close();
            HostThread.interrupt();
            DiscoveryThread.interrupt();
            System.out.println("Server stopped.");
		} catch (Exception ex){
		    JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
