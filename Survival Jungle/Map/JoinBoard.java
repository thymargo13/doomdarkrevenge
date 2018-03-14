package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.MenuPanel;


//////////// DELETE THIS CLASS/////////

@SuppressWarnings("serial")
public class JoinBoard extends JPanel implements ActionListener{

	public JoinBoard(MenuPanel bgPanel) {
		super(new BorderLayout());
		setBackground(Color.GREEN);
		setBounds(250,300 , WIDTH, HEIGHT);					
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		String[] ipString = {"1","2","3","4"};
		
		//create and set up the window
		JFrame frame = new JFrame("ComboBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create and set up the content pane
		JComponent contentPane = new JoinBoard(bgPanel);
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
		//create the combo box
		JComboBox ips  = new JComboBox(ipString);
		ips.setSelectedIndex(3);
		ips.addActionListener(this);
		
		add(ips, BorderLayout.PAGE_START);
		
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
		JComboBox cb = (JComboBox)e.getSource();
		String ip = (String)cb.getSelectedItem();
		updateLabel(ip);
		
	}
	
	protected void updateLabel(String name){
		
	}

}
