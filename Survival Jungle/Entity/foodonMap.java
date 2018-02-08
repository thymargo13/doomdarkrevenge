package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.security.PrivilegedActionException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class foodonMap {
	private int x;
	private int y;
	private int exp;	
	protected String img = "/Resource/objects/MudObstacle2.png"; //location of img
	protected Image image; 
	private boolean eaten = false;  // on the map? or not now
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public String getImg() {
		return img;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	public foodonMap() {
		// TODO Auto-generated constructor stub
		setX(100);
		setY(200);
		setExp(20);
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		image = ii.getImage();
				
	}
	public Graphics2D draw (Graphics2D g, JPanel jp) {
		//Graphics2D g2d = (Graphics2D) g;
		//g.rotate(Math.toRadians(30),getX(),getY());
		g.drawImage(getImage(), getX(), getY(), 100, 100,jp);
	//	Toolkit.getDefaultToolkit().sync();
		return g;
	}


}
