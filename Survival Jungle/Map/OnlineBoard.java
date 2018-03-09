package Map;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Command.OnlineGame;
import Main.MenuPanel;

@SuppressWarnings("serial")
public class OnlineBoard extends JPanel implements ActionListener {
	
	private static final int WIDTH = 250;
	private static final int HEIGHT = 200;
	private String playerName;
	private JPanel panel;
	private JTextField text;
	int xx, xy;
	

	public OnlineBoard(MenuPanel bgPanel){
		setBackground(new Color(0,0,0,0));
		setBounds(280,400 , WIDTH, HEIGHT);	
		//setLayout(Grid)// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		JLabel label = new JLabel("Enter username");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//label.setForeground(Color.red);
		this.add(label);
		text = new JTextField();
		text.setBounds(150, 200,100, 100);
		this.add(text);
		text.setColumns(20);
		
		JButton button= new JButton("OK");
		button.setSize(500, 500);
		button.setBounds(600, 600, 500, 500);		
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//get username
				playerName = text.getText();
				System.out.println(playerName);
				//remove OnlineBoard panel and get HostJoinBoard panel
				HostJoinBoard hj = new HostJoinBoard(bgPanel);
				bgPanel.remove(0);
				bgPanel.add(hj,0);
			}
		});
		
		JButton button1 = new JButton("Back");
		this.add(button);
		this.add(button1);
		
	//	panel.setLayout(null);
	//	panel.add(label);

	//	JLabel lbl = new JLabel("Enter username");

		/*lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setForeground(new Color(240, 248, 255));
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl.setBounds(111, 343, 141, 27);

		panel.add(lbl);

		Button button = new Button("SignUp");

		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(395, 363, 283, 36);

		panel.add(button);
		text = new JTextField();
		text.setBounds(395, 83, 283, 36);
		panel.add(text);
		text.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");

		lblUsername.setBounds(395, 58, 114, 14);
		panel.add(lblUsername);

		JLabel lbl_close = new JLabel("X");


		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		panel.add(lbl_close);*/
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
    setBackground(Color.WHITE);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 729, 476);

	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JPanel panel = new JPanel();
	panel.setBackground(Color.DARK_GRAY);
	panel.setBounds(0, 0, 346, 490);
	contentPane.add(panel);
	panel.setLayout(null);

	JLabel lblNewLabel = new JLabel("Survival Jungle");

	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblNewLabel.setForeground(new Color(240, 248, 255));
	lblNewLabel.setBounds(139, 305, 84, 27);
	panel.add(lblNewLabel);

	JLabel label = new JLabel("");

	label.addMouseListener(new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {

			xx = e.getX();
			xy = e.getY();
		}

	});

	label.addMouseMotionListener(new MouseMotionAdapter() {

		@Override
		public void mouseDragged(MouseEvent arg0) {

			int x = arg0.getXOnScreen();
			int y = arg0.getYOnScreen();

			OnlineBoard.this.setLocation(x - xx, y - xy);
		}

	});

	label.setBounds(-38, 0, 420, 275);
	label.setVerticalAlignment(SwingConstants.TOP);
	label.setIcon(new ImageIcon(OnlineGame.class.getResource("/images/bg.jpg")));

	panel.add(label);

	JLabel lblWeGotYou = new JLabel("Enter username");

	lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
	lblWeGotYou.setForeground(new Color(240, 248, 255));
	lblWeGotYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
	lblWeGotYou.setBounds(111, 343, 141, 27);

	panel.add(lblWeGotYou);

	Button button = new Button("SignUp");

	button.setForeground(Color.WHITE);
	button.setBackground(new Color(241, 57, 83));
	button.setBounds(395, 363, 283, 36);

	contentPane.add(button);
	text = new JTextField();
	text.setBounds(395, 83, 283, 36);
	contentPane.add(text);
	text.setColumns(10);

	JLabel lblUsername = new JLabel("USERNAME");

	lblUsername.setBounds(395, 58, 114, 14);
	contentPane.add(lblUsername);

	JLabel lbl_close = new JLabel("X");


	lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_close.setForeground(new Color(241, 57, 83));
	lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lbl_close.setBounds(691, 0, 37, 27);
	contentPane.add(lbl_close);
	
	}*/


}
