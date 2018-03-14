package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import Map.Board;

public class LocalGame extends Command{
	private JPanel localgame;
	public LocalGame() {
		localgame = new JPanel();
		localgame.add(new Board());
	}
	@Override
	public JPanel execute() {
		return localgame;
		
<<<<<<< HEAD
//		Local local = new Local();
//		local.run();
//		return null;
=======
>>>>>>> UI
	}

}