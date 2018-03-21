package Network.Client;

import java.io.DataOutputStream;
import java.util.concurrent.BlockingQueue;

import javax.swing.JOptionPane;

public class ClientSender implements Runnable{
	
	private BlockingQueue<String> queue;
	private DataOutputStream output;
	boolean running = true;
	
	ClientSender(BlockingQueue<String> queue, DataOutputStream output){
		this.queue = queue;
		this.output = output;
	}
	
	public void run() {
		while (running) {
			try {
				String message = queue.take();
				output.writeBytes(message);
				System.out.print("Client sender message: " + message);
			}
			catch(Exception ex) {
				System.out.println("Client Sender error: " + ex.getMessage());
				closeSocket();
				running = false;
				
				JOptionPane.showMessageDialog(null, "Disconnected from server.", "Error", JOptionPane.ERROR_MESSAGE);				
				System.exit(0);
			}

		}
	}
	
	public void closeSocket(){
		try {
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
