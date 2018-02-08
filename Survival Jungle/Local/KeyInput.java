package Local;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Local game;

	public KeyInput(Local game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}