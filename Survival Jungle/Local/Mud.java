package Local;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Mud {
	public static ArrayList<Mud> serverMuds = new ArrayList<Mud>();

	public static ArrayList<Mud> muds = new ArrayList<Mud>();
	public static int mudCount;
	protected String img; 
	protected Image image; 
	private int x;
	private int y;
	private int d=400;
//	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//			(int) Math.floor(Math.random() * 256));
	public Mud(int x,int y, int d) {
		mudCount++;
		this.x=x;
		this.y=y;
		this.d=d;
		this.img= "/Resource/objects/mud.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		this.image = ii.getImage();
	}
	
	public void draw(Graphics bbg, JPanel jpanel) {
		Mud m= new Mud(x,y,d);
		bbg.drawImage(m.getImage(),(int) x, (int) y, (int) d, (int) d, jpanel);
	}
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
	
	public int getD() {
		return d;
	}
	
	public void setD(int d) {
		this.d=d;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public Image getImage() {
		return image;
	}
}
