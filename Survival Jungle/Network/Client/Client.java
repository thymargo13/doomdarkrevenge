package Network.Client;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
	private int userID;
	private String username;
	private InetAddress IP;
	private BlockingQueue<String> blockQueue;
	private int port;
	
	public Client(int userID, String username, InetAddress IP, int port){
		setUserID(userID);
		setUsername(username);
		setIP(IP);
		
		BlockingQueue<String> blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
		setPort(port);
	}
	
	public Client(int userID, String username, InetAddress IP, BlockingQueue<String> blockQueue){
		setUserID(userID);
		setUsername(username);
		setIP(IP);
		
		blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
		setPort(8888);
	}
	
	public Client(int userID, String username, InetAddress IP, BlockingQueue<String> blockQueue, int port){
		setUserID(userID);
		setUsername(username);
		setIP(IP);
		
		blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
		setPort(port);
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public InetAddress getIP() {
		return IP;
	}
	
	public int getPort() {
		return port;
	}
	
	public BlockingQueue<String> getQueue(){
		return blockQueue;
	}
	
	public int setUserID(int userID) {
		try {
			this.userID = userID;
			return 1;
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
			return 0;
		}
	}
	
	public int setUsername(String username) {
		try {
			this.username = username;
			return 1;
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
			return 0;
		}
	}
	
	public int setIP(InetAddress IP) {
		try {
			this.IP = IP;
			return 1;
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
			return 0;
		}
	}
	
	public int setBlockQueue(BlockingQueue<String> BlockQueue) {
		try {
			this.blockQueue = BlockQueue;
			return 1;
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
			return 0;
		}
	}
	
	public int setPort(int port) {
		try {
			this.port = port;
			return 1;
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
			return 0;
		}
	}
	
}

