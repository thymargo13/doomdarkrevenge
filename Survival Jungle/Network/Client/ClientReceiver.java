package Network.Client;

import java.io.BufferedReader;

public class ClientReceiver implements Runnable{
	private BufferedReader input;
	
	ClientReceiver(BufferedReader input){
		this.input = input;
	}

	public void run(){
		while (true) {
			try {
				String message = input.readLine();
				System.out.println("Received message from server: " + message);
			} catch (Exception ex) {
			}
		}
	}

}
