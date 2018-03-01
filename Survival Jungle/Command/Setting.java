package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.Audio_player;

public class Setting extends Command{

	@SuppressWarnings("null")
	@Override
	public JPanel execute() {
		// TODO Auto-generated method 
		Audio_player audio = null;
		int i = JOptionPane.showConfirmDialog(null, "Turn sound OFF?");
		if(i == JOptionPane.YES_OPTION){
			audio.stop();
		}
		
		return null;
	}

}
