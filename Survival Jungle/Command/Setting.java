package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.Audio_player;

public class Setting extends Command{

	@SuppressWarnings("null")
	@Override
	public JPanel execute() {
		// TODO Auto-generated method 
<<<<<<< HEAD
		//System.out.println("Setting");
		Object[] options = {"Sound effects ON", "Sound effects OFF"};
		int n = JOptionPane.showOptionDialog(null, null, "Sound settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		if(n == JOptionPane.OK_OPTION){
			System.exit(0);
		}
=======
		Audio_player audio = null;
		int i = JOptionPane.showConfirmDialog(null, "Turn sound OFF?");
		if(i == JOptionPane.YES_OPTION){
			audio.stop();
		}
		
>>>>>>> UI
		return null;
	}
		
	
}
