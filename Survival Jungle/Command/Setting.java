package Command;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Setting extends Command{

	@Override
	public JPanel execute() {
		// TODO Auto-generated method 
		//System.out.println("Setting");
		Object[] options = {"Sound effects ON", "Sound effects OFF"};
		int n = JOptionPane.showOptionDialog(null, null, "Sound settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		if(n == JOptionPane.OK_OPTION){
			System.exit(0);
		}
		return null;
	}
		
	
}
