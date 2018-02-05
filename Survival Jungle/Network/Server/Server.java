package Network.Server;

import java.net.ServerSocket;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Network.Client.Client;

public class Server {
	private static Thread ConnectionThread;
	private static ServerSocket ServerSocket;
	private final static HashMap<Integer, Client> Clients= new HashMap<Integer, Client>();
	
	private void startServer() {
		try {
			
		} catch (Exception ex){
			final JPanel errorPanel = new JPanel();
		    JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	private void stopServer() {
		try {
            
		} catch (Exception ex){
			final JPanel errorPanel = new JPanel();
		    JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
