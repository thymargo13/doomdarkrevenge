package Map;

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
import Network.Client.*;
import Network.Server.Client;
import Multiplayer.*;

public class Board extends JPanel implements ActionListener {
	private final int DELAY = 10;	// milliseconds delay
	private Timer timer;
	private GameState gameState;

	// KSFOONG: Multiplayer
	private MultiplayerGameState MultiplayerGameState;
	private boolean multiplayer = false;
	Network Network;
	String Address;
	
	public Board() {
		// KSFOONG
		multiplayer = false;
		setLayout(null);
		initBoard();
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
//				System.out.println("X: "+e.getX()+", Y: "+e.getY());
				gameState.mouseMoved(e);	// pass the mouse event to game state
			}
		});
	}

	private void initBoard() {
		gameState = new GameState();
		setPreferredSize(new Dimension(800, 600));	// can use setSize() if component's parent has no layout manager
		setDoubleBuffered(true);	// Sets whether this component should use a buffer to paint
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
		
	}

	
	// Multiplayer
	public Board(ArrayList<Client> Clients, boolean isHost, Network Network, String Address) {
		multiplayer = true;
		this.Network = Network;
		this.Address = Address;
		setLayout(null);
		initMultiplayerBoard(Clients,isHost);
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
//				System.out.println("X: "+e.getX()+", Y: "+e.getY());
				MultiplayerGameState.mouseMoved(e);	// pass the mouse event to game state
			}
		});
	}
	
	// Multiplayer
	private void initMultiplayerBoard(ArrayList<Client> Clients, boolean isHost) {
		MultiplayerGameState = new MultiplayerGameState(Clients, isHost);
		Network.setGameState(MultiplayerGameState);
		
		if (isHost) {
			// Host username
			Network.startServer("Dell");
		} else {
			Network.connectServer(Address);
		}
		
		// while not connected
		while(!Network.getIsConnected()) {
			System.out.println("Connecting to server...");
		}
		
		setPreferredSize(new Dimension(800, 600));	// can use setSize() if component's parent has no layout manager
		setDoubleBuffered(true);	// Sets whether this component should use a buffer to paint
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!multiplayer) {
			gameState.initDraw(g, this);	// pass the graphics to game state to control all rendering
		} else {
			MultiplayerGameState.initDraw(g, this);	// pass the graphics to game state to control all rendering
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!multiplayer) {
			gameState.update();
			gameState.draw();
		} else {
			MultiplayerGameState.update();
			MultiplayerGameState.draw();
		}

		repaint();
	}
}
