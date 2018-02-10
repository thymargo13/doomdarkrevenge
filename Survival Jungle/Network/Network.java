package Network;

import java.net.InetAddress;
import java.util.ArrayList;

import Network.Client.*;
import Network.Server.*;

public class Network {
	Server Server = null;
	ClientDiscover ClientDiscover = null;
	ClientConnect ClientConnect = null;
	
	public void startServer() {
		Server = new Server();
		Server.startServer();
	}
	
	public void stopServer() {
		Server.stopServer();
	}
	
	public void ClientStartDiscovery() {
		ClientDiscover = new ClientDiscover();
		ClientDiscover.startDiscover();
	}
	
	public void clientStopDiscover() {
		ClientDiscover.stopDiscover();
	}
	
	public ArrayList<InetAddress> getDiscoveredServer() {
		return ClientDiscover.getDiscoveredServer();
	}
	
	public boolean connectServer(String address) {
		ClientConnect = new ClientConnect(address);
		return ClientConnect.connectToServer();
	}
	
	public void disconnectServer() {
		ClientConnect.disconnectFromServer();
	}
	
	public void sendToServer(String message) {
		ClientConnect.sendMessage(message);
	}		
}
