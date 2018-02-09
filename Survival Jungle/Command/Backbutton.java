package Command;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Main.MenuPanel;

public class Backbutton extends JLayeredPane implements Runnable{
	//private static final JFrame JFrame;
	private MenuPanel menu;
	JFrame frame;
	
	public Backbutton(JFrame frame) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		this.frame=frame;
		menu = new MenuPanel(frame);
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
