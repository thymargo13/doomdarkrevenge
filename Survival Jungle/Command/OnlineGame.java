package Command;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.MenuPanel;
import Map.OnlineBoard;

public class OnlineGame extends Command{

	private JPanel onlinegame;
	private JFrame frame;
	public OnlineGame() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		onlinegame = new JPanel();
		onlinegame.add( new OnlineBoard());
		
	}
	public JPanel execute(){
		
		return onlinegame;
		
	}
}
