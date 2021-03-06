
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
		window.pack();
		window.setVisible(true);
		
	}
	
}
