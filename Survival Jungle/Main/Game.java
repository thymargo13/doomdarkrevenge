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

import javax.swing.*;

import Command.MouseListenerX;



public class Game {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//setting JFrame
		JFrame window = new JFrame("Survival Jungle"); //set the frame title
		window.addMouseListener(new MouseListenerX());
		window.setSize(800,600);
		window.setContentPane(new MenuPanel(window)); //add panel
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set close button
		window.setResizable(false); //restrict the frame size
		//((JPanel) window.getContentPane()).setOpaque(false);
		window.pack();
		window.setVisible(true);
		
	}
	
}
