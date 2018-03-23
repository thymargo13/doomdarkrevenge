package Network.Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * @author Khang Sheong Foong
 * This is the thread running when performing Client Discovery
 */
public class ClientDiscoveryReceive implements Runnable {
	DatagramSocket DatagramSocket;
	ArrayList<InetAddress> HostAddress;
	
	ClientDiscoveryReceive(DatagramSocket DatagramSocket, ArrayList<InetAddress> HostAddress){
		this.DatagramSocket = DatagramSocket;
		this.HostAddress = HostAddress;
	}
	
	public void run() {
		try {			
			while(true) {
				byte[] recvBuf = new byte[15000];
				DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
				DatagramSocket.receive(receivePacket);
				System.out.println("Client Discovery: Response from server: " + receivePacket.getAddress().getHostAddress());
				
				String message = new String(receivePacket.getData()).trim();
				if (message.equals("SERVER_RESPONSE")) {
					HostAddress.add(receivePacket.getAddress());
					System.out.println("Server discovered: IP " +receivePacket.getAddress());
				}
				
			}
		}
		catch (Exception ex) {
			System.out.println("Client Discovery Receive Error : " +ex.getMessage());
		}    	  
		
	}
}