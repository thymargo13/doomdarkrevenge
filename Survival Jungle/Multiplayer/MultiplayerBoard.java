package Multiplayer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Network.Network;
import Network.Server.Client;

public class MultiplayerBoard extends JPanel implements ActionListener {
	private final int DELAY = 10;	// milliseconds delay
	private Timer timer;
	
	private ClientGameState ClientGameState;
	private boolean multiplayer = true;
	Network Network;
	String Address;
	
	public MultiplayerBoard(ArrayList<Client> Clients, boolean isHost, Network Network, String Address) {
		this.Network = Network;
		this.Address = Address;
		setLayout(null);
		initMultiplayerBoard(Clients,isHost);
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
//				System.out.println("X: "+e.getX()+", Y: "+e.getY());
				ClientGameState.mouseMoved(e);	// pass the mouse event to game state
			}
		});
	}

	private void initMultiplayerBoard(ArrayList<Client> Clients, boolean isHost) {
		ClientGameState = new ClientGameState(Clients, Network);
		Network.setClients(Clients);
		Network.setGameState(ClientGameState);
		
		if (isHost) {
			// Host username
			ServerGameState ServerGameState = new ServerGameState(new ArrayList<Client>(), Network);
			Network.setServerGameState(ServerGameState);
			Network.startServer();
			Network.connectServer(Clients.get(0), "127.0.0.1");

		} else {
			Network.connectServer(Clients.get(0), Address);
		}
		

		// while not connected
//		while(!Network.getIsConnected()) {
//			System.out.println("Connecting to server...");
//		}
		
		setPreferredSize(new Dimension(800, 600));	// can use setSize() if component's parent has no layout manager
		setDoubleBuffered(true);	// Sets whether this component should use a buffer to paint
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ClientGameState.initDraw(g, this);	// pass the graphics to game state to control all rendering
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ClientGameState.update();
		ClientGameState.draw();
		repaint();
	}
}
