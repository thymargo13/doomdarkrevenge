package Map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Main.MenuPanel;

@SuppressWarnings("serial")
public class JoinBoard extends JPanel implements ActionListener{

	public JoinBoard(MenuPanel bgPanel) {
		setBackground(Color.GREEN);
		setBounds(250,300 , WIDTH, HEIGHT);														// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		String[] ipString = {"1","2","3","4"};
		
		//create the combo box
		JComboBox ips  = new JComboBox(ipString);
		ips.setSelectedIndex(3);
		ips.addActionListener(this);/*new ActionListener(){
			public void actionPerformed(ActionEvent e);
			{
				
			}
		});*/
		
		//Join the game
		JButton button = new JButton("Join");
		this.add(button);
		button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
		}
	});
		//Refresh ip list
		JButton button1 = new JButton("Refresh");
		this.add(button1);
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
