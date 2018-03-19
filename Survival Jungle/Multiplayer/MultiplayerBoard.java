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
	private static final long serialVersionUID = -8887303478018191470L;
	private final int DELAY = 10;	// milliseconds delay
	private Timer timer;
	
	private MultiplayerGameState MultiplayerGameState;
	Network Network;
	String Address;
	
	public MultiplayerBoard(ArrayList<Client> Clients, boolean isHost, Network Network, String Address) {
		this.Network = Network;
		this.Address = Address;
		setLayout(null);
		initMultiplayerBoard(Clients,isHost);
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				MultiplayerGameState.mouseMoved(e);	// pass the mouse event to game state
			}
		});
	}

	private void initMultiplayerBoard(ArrayList<Client> Clients, boolean isHost) {
		MultiplayerGameState = new MultiplayerGameState(Clients, Network, isHost);
		Network.setClients(Clients);
		Network.setGameState(MultiplayerGameState);	
		if (isHost) {
			Network.startServer();
		} else {
			Network.connectServer(Clients.get(0), Address);
		}
		

				
		setPreferredSize(new Dimension(800, 600));	// can use setSize() if component's parent has no layout manager
		setDoubleBuffered(true);	// Sets whether this component should use a buffer to paint
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		MultiplayerGameState.initDraw(g, this);	// pass the graphics to game state to control all rendering
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MultiplayerGameState.update();
		MultiplayerGameState.draw();
		repaint();
	}
}
