package Command;


import javax.sound.sampled.BooleanControl;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.Audio_player;
import Main.MenuPanel;
/**
 * 
 * @author Bitnarae Kim
 * The Setting class mutes the music when YES is pressed or unmutes if NO is pressed
 *
 */

public class Setting extends Command{
	private static boolean mute;
	
	 
	@Override
	public JPanel execute() {
		// TODO Auto-generated method 

		int i = JOptionPane.showConfirmDialog(null, "Turn sound OFF?");
		BooleanControl mutecontrol = (BooleanControl) Audio_player.clip.getControl(BooleanControl.Type.MUTE);
		if(i == JOptionPane.YES_OPTION){
			mute = true;
		}
		if(i == JOptionPane.NO_OPTION) {
			mute = false;
		}
		mutecontrol.setValue(mute);

		return null;
	}
	public boolean getMute() {
		return mute;
	}

		
	
}
