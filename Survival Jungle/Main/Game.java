//package Main;
//
//public class Game {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
package Main;

import java.awt.*;
import java.net.InetAddress;

import javax.swing.*;

// Networking Debug
import Network.*;
import Network.Client.ClientDiscoveryReceive;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
//		//setting JFrame
//		JFrame window = new JFrame("Survival Jungle"); //set the frame title
//		window.setSize(800,600);
//		window.setContentPane(new MenuPanel(window)); //add panel
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set close button
//		window.setResizable(false); //restrict the frame size
//		//((JPanel) window.getContentPane()).setOpaque(false);
//		window.pack();
//		window.setVisible(true);
		
		Network Network = new Network();
		Network.startServer();
		int i = 1;
		
		while (i<3) {
			System.out.println(i);
			TimeUnit.SECONDS.sleep(1);
			i++;
		}

		Network.ClientStartDiscovery();
		i = 1;
		while (i<3) {
			System.out.println(i);
			TimeUnit.SECONDS.sleep(1);
			i++;
		}
		for (InetAddress a : Network.getDiscoveredServer()) {
			System.out.println("AAA Found:" + a.getHostAddress());
		}
		
		Network.clientStopDiscover();
		Network.stopServer();

		
	}
	
}
