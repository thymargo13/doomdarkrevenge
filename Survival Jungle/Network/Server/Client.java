package Network.Server;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Khang Sheong Foong
 * This is a class for the identification of every client connected.
 */
public class Client {
	private int userID;
	private String username;
	private InetAddress IP;
	private BlockingQueue<String> blockQueue;
	
	public Client(int userID, String name, InetAddress IP) {
		setUserID(userID);
		setUsername(name);
		setIP(IP);
		
		BlockingQueue<String> blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
	}
	
	public Client(int userID, String name) {
		setUserID(userID);
		setUsername(name);
		
		BlockingQueue<String> blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
	}
	
	public Client(int userID, InetAddress IP){
		setUserID(userID);
		setUsername("");
		setIP(IP);
		
		BlockingQueue<String> blockQueue = new LinkedBlockingQueue<String>();
		setBlockQueue(blockQueue);
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
	
	public void sendMessage(String message) {
		this.blockQueue.add(message+"\n");
	}
}

