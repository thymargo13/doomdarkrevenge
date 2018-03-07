package Local;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Entity.Player;

public class Forest {
	public static ArrayList<Forest> forests = new ArrayList<Forest>();
	public static int forestCount;
	protected String img; 
	protected Image image; 
	private int x;
	private int y;
	private int d=350;
	boolean couldHide=false;	//default----->no special events

//	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//			(int) Math.floor(Math.random() * 256));
	public Forest(int x,int y, int d) {
		forestCount++;
		this.x=x;
		this.y=y;
		this.d=d;
		this.img= "/Resource/objects/forest.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img)); 
		this.image = ii.getImage();

	}
	
	public boolean CheckHide(int level) {
		if(level==1 || level==4 || level ==5) {
			couldHide=true;
		}
		return couldHide;
	}
//	public void draw(Graphics bbg) {
//		bbg.setColor(color);
//		bbg.fillOval(x, y, d, d);
//		bbg.drawOval(x, y, d, d);
//	}
//	public Forest(int x, int y, int d) {
//		this.img= "/Resource/objects/forest.png";
//		ImageIcon ii = new ImageIcon(getClass().getResource(img));
//		this.image = ii.getImage();
//	}
	public void draw(Graphics bbg, JPanel jpanel) {
		Forest f= new Forest(x,y,d);
		bbg.drawImage(f.getImage(),(int) x, (int) y, (int) d, (int) d, jpanel);
	}
	
//	private boolean checkCollide(double x, double y, double mass) {
//		return x < this.x + 10 && x + mass > this.x && y < this.y + 10 && y + mass > this.y;
//	}
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
