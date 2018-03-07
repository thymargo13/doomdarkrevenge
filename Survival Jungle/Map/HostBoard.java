package Map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Main.MenuPanel;

@SuppressWarnings("serial")
public class HostBoard extends JPanel implements ActionListener{

	public HostBoard(MenuPanel bgPanel) {
		setBackground(Color.GREEN);
		setBounds(250,300 , WIDTH, HEIGHT);														// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
