package Command;

import javax.swing.JPanel;

import Map.GameBackground;

public class LocalGame extends Command{

	@Override
	public JPanel execute() {
		// TODO Auto-generated method stub
//		System.out.println("LocalGame");
		return new GameBackground();
	}

}
