package Command;


import javax.sound.sampled.BooleanControl;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.Audio_player;
import Main.MenuPanel;
/**
 * 
 * @author Bitnarae Kim
 * The Setting class shows a panel to stop the music
 *
 */

public class Setting extends Command{
	
	
	 
	@Override
	public JPanel execute() {
		// TODO Auto-generated method 

		MenuPanel music;
		int i = JOptionPane.showConfirmDialog(null, "Turn sound OFF?");
		if(i == JOptionPane.YES_OPTION){
		
		BooleanControl mutecontrol = (BooleanControl) Audio_player.clip.getControl(BooleanControl.Type.MUTE);	
		mutecontrol.setValue(true);	
			
		}
		

		return null;
	}

		
	
}
