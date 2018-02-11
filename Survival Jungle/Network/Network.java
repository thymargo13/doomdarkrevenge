package Network;

import java.net.InetAddress;
import java.util.ArrayList;

import Network.Client.*;
import Network.Server.*;
import Multiplayer.*;

public class Network {
	Server Server = null;
	ClientDiscover ClientDiscover = null;
	ClientConnect ClientConnect = null;
	MultiplayerGameState MultiplayerGameState = null;
	private boolean isConnected = false;
	
	public void startServer(String name) {
		Server = new Server();
		Server.setName(name);
		Server.startServer();
		isConnected = true;
	}
	
	public void stopServer() {
		Server.stopServer();
		isConnected = false;
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
	
	public void connectServer(String address) {
		ClientConnect = new ClientConnect(address,MultiplayerGameState);
		isConnected = ClientConnect.connectToServer();
	}
	
	public void disconnectServer() {
		ClientConnect.disconnectFromServer();
		isConnected = false;
	}
	
	public void sendToServer(String message) {
		ClientConnect.sendMessage(message);
	}
	
	public void setGameState(MultiplayerGameState MultiplayerGameState) {
		this.MultiplayerGameState = MultiplayerGameState;
	}
	
	public boolean getIsConnected() {
		return isConnected;
	}
}
