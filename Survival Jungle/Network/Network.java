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
	ClientGameState MultiplayerGameState = null;
	ServerGameState ServerGameState = null;

	boolean isConnected = false;
	ArrayList<Client> Clients;
	
	public void setGameState(ClientGameState MultiplayerGameState) {
		this.MultiplayerGameState = MultiplayerGameState;
	}
	
	public void setServerGameState(ServerGameState ServerGameState) {
		this.ServerGameState = ServerGameState;
	}
	
	public void setClients(ArrayList<Client> Clients) {
		this.Clients = Clients;
	}
	
	public boolean getIsConnected() {
		return isConnected;
	}	
	
	public void startServer(String name) {
		Server = new Server(ServerGameState.getServerClients(), ServerGameState);
//		Server.setName(name);
		Server.startServer();
		ServerGameState.setRunning(true);
//		isConnected = true;
	}
	
	public void stopServer() {
		Server.stopServer();
//		isConnected = false;
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
	
	public void connectServer(Client Client, String address) {
		ClientConnect = new ClientConnect(Client, address, MultiplayerGameState);
		isConnected = ClientConnect.connectToServer();
	}
	
	public void disconnectServer() {
		ClientConnect.disconnectFromServer();
		isConnected = false;
	}
	
	public void sendToServer(String message) {
		ClientConnect.sendMessage(message);
	}
	
	public void sendAsServer(String message) {
		Server.sendAsServer(message);
	}
	

	

}
