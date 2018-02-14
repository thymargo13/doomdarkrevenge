package Command;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class MouseListenerX implements MouseListener{

	private double x,y;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//int i = JOptionPane.showConfirmDialog(null, "Mouse clicked " + e.getX() + ", " + e.getY(), "Confirm exit", JOptionPane.YES_NO_OPTION);
		x=e.getX();
		y=e.getY();
	}
	
	public double getMousLocaX() {
		return x;
	}

	public double getMousLocaY() {
		return y;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
