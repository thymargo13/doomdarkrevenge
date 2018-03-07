package Map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Main.MenuPanel;

public class HostJoinBoard extends JPanel implements ActionListener{
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	public HostJoinBoard(MenuPanel bgPanel) {
		setBackground(Color.GREEN);
		setBounds(250,300 , WIDTH, HEIGHT);														// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		JButton button = new JButton("Host");
		this.add(button);
		
		
		JButton button1 = new JButton("Join");
		this.add(button1);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JoinBoard j = new JoinBoard(bgPanel);
				bgPanel.remove(0);
				bgPanel.add(j,0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
