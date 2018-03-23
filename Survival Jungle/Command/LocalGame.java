package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import Map.Board;
/*
 * Creates a new board that enters single player game
 */
public class LocalGame extends Command{
	private JPanel localgame;
	public LocalGame() {
		localgame = new JPanel();
		localgame.add(new Board());
	}
	@Override
	public JPanel execute() {
		return localgame;

	}

}