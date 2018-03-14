package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MenuPanel;

@SuppressWarnings("serial")
public class HostJoinBoard extends JPanel implements ActionListener{
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	JFrame f = new JFrame("Online Game");
	JButton button = new JButton("Join");
	
	private String[] ipString = new String[] {"111","222","333","444"};
	private JComboBox<String> cb = new JComboBox<String>(ipString);
	
	public HostJoinBoard(MenuPanel bgPanel){		
		
		f.getContentPane().setLayout(null);
		JLabel lbl = new JLabel("Select IP : ");
		//cb.addItem("111");
		//cb.addItem("222");
		//cb.addItem("333");
	    //cb.addItem("444");
		lbl.setBounds(100,150,70,20);
		cb.setBounds(170,150,100,20);
		button.setBounds(80, 90,  70, 20);
		
		f.add(lbl);
		f.add(cb);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String selectedIP = (String)cb.getSelectedItem();
			}
		});
		add(button);
		
		f.setSize(500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//create Join button
		/*JButton button1 = new JButton("Join");
		this.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){*/
				
				//move to Join panel
				//JoinBoard j = new JoinBoard(bgPanel);
			//	bgPanel.remove(0);
			//	bgPanel.add(j,0);
		//	}
	//	});
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
        String selectedIP = (String)cb.getSelectedItem();
       // updateLabel(ipList);
		
	}
	
}
