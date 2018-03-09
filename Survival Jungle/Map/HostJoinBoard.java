package Map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Main.MenuPanel;

@SuppressWarnings("serial")
public class HostJoinBoard extends JPanel implements ActionListener{
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	public HostJoinBoard(MenuPanel bgPanel) {
		setBackground(Color.GREEN);
		setBounds(250,300 , WIDTH, HEIGHT);														// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		String[] ipString = {"1","2","3","4"};
		
		//create the combo box
		JComboBox ips  = new JComboBox(ipString);
		ips.setSelectedIndex(3);
		ips.addActionListener(this);
		
		//create Join button
		JButton button1 = new JButton("Join");
		this.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				//move to Join panel
				//JoinBoard j = new JoinBoard(bgPanel);
			//	bgPanel.remove(0);
			//	bgPanel.add(j,0);
			}
		});
		
		//create Host button
		JButton button = new JButton("Back");
		//button.setLocation(500, 500);
		this.add(button);
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
