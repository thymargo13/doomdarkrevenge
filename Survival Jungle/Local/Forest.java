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
	public boolean couldHide=false;	//default----->no special events

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
	public void Update() {
		for (Cell cell : Cell.cells) {
			if(cell.currentLv == cell.level.get(1) || cell.currentLv == cell.level.get(4) || 
					cell.currentLv == cell.level.get(5)) {
				couldHide=true;
			}else {
				couldHide=false;
				if(checkCollide(cell.x,cell.y)) {
//					double comparex=cell.x;
//					double comparey=cell.y;
//					cell.reduceHp(1, cell);
					double centre_x1 = x-150 ;
					double centre_y1 = y-150 ;
					double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
					if(distance<=230) {
						cell.x-=50;
						cell.y-=50;
					}
				}
			}
		}
	}
	private boolean checkCollide(double x, double y) {
		double centre_x1 = x-150;
		double centre_y1 = y-150 ;
		//this.x & this.y is particle coordinate.
		double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
//		if(distance<500) {
//			isShot=true;
//		}
//		System.out.println("test!!");
		return distance < 230;
//		return isShot;
	//	return x < this.x + 10 && x + mass > this.x && y < this.y + 10 && y + mass > this.y;
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
