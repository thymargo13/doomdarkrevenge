package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Exit extends Command {

	@Override
	public JPanel execute() {
		// TODO Auto-generated method stub
		
		int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Confirm exit", JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		return null;
	}

}
