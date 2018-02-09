package Main;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import Command.*;

@SuppressWarnings("serial")
public class Button extends JButton {
	private String[] cmd = { "LocalGame", "OnlineGame", "Setting", "Exit" };

	public Button(String imgPath, int panel) {
		setIcon(new ImageIcon(getClass().getResource(imgPath)));
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFont(new Font("Marker Felt", Font.BOLD, 40));
		// Action Listener
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame jf = (JFrame) getRootPane().getParent();
				try {
					Command functionCmd = (Command) Class.forName("Command." + cmd[panel]).newInstance();
					jf.setContentPane(functionCmd.execute());
				} catch (Exception ex) {
					ex.printStackTrace();
				} // jump to next panel
				jf.setVisible(true);
				JPanel imagePanel = (JPanel) jf.getContentPane();
				imagePanel.setOpaque(false);
			}
		});
	}
}