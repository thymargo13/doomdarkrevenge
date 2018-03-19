package Network.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerDiscovery implements Runnable {
	DatagramSocket DatagramSocket;
	 byte[] receviedBuffered;
	
	public ServerDiscovery(DatagramSocket socket) {
		this.DatagramSocket = socket;
	}

	public void run() {
	    try {    
	    	DatagramSocket.setBroadcast(true);
			System.out.println("Server discovery: Ready to receive broadcast packets!");
			while (true) {
				receviedBuffered = new byte[15000];
				DatagramPacket DatagramPacket = new DatagramPacket(receviedBuffered, receviedBuffered.length);
				DatagramSocket.receive(DatagramPacket);
				
				System.out.println("Server discovery: Discovery packet received from: " + DatagramPacket.getAddress().getHostAddress());
				System.out.println("Server Discovery: Message : " + new String(DatagramPacket.getData()));
			
				String message = new String(DatagramPacket.getData()).trim();
			    if (message.equals("SERVER_REQUEST")) {
			    	byte[] sendData = "SERVER_RESPONSE".getBytes();
						
			    	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, DatagramPacket.getAddress(), DatagramPacket.getPort());
			    	DatagramSocket.send(sendPacket);
					System.out.println("Server Discovery: Reply to " + sendPacket.getAddress().getHostAddress());
			    }
			}		
		} catch (IOException ex) {
			System.out.println("Server discovery error : " +ex.getMessage());
		}
	}
}

