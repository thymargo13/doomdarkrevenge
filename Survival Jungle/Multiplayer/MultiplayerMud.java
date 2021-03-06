package Multiplayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Local.Cell;
/**
 * @author Khang Sheong Foong
 * This is a copy of the Mud class from the Local game mode. In order to make it synchronized with server and clients, some minor modification had been done in this seperate class.
 * This class will perform checking for which animal can enter it and which animal can't.
 */
public class MultiplayerMud {
	public static ArrayList<MultiplayerMud> muds = new ArrayList<MultiplayerMud>();
	public static int mudCount;
	protected String img; 
	protected Image image; 
	private int x;
	private int y;
	private int d=270;
	double colX, colY;
	public boolean couldHide=false;
//	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//			(int) Math.floor(Math.random() * 256));
	public MultiplayerMud(int x,int y, int d) {
		mudCount++;
		this.x=x;
		this.y=y;
		this.d=d;
		this.img= "/Resource/objects/mud.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		this.image = ii.getImage();
	}
	public void Update() {
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			
				if(checkCollide(cell.x,cell.y)) {
					if(cell.currentLv == cell.level.get(0)) {
						
					}else {
//						double dx = (this.colX - this.x);
//						double dy = (this.colY - this.y);
//						cell.x += (dx) * 1 / 100;
//						cell.y += (dy) * 1 / 100;
						
						boundsOut(cell);
						cell.colRached = true;

					}

				}
		}
	}
	
	public void boundsOut(MultiplayerCell cell) {
		int distance = 100;
		if (this.x < cell.x) {
			cell.colX = cell.x + distance;
		} else if (this.x > cell.x) {
			cell.colX = cell.x - distance;
		}
		if (this.y < cell.y) {
			cell.colY = cell.y + distance;
		} else if (this.y > cell.y) {
			cell.colY = cell.y - distance;
		}
	}
	
	private boolean checkCollide(double x, double y) {
		double centre_x1 = x-75;
		double centre_y1 = y-70 ;
		//this.x & this.y is particle coordinate.
		double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
		return distance < 170;
	}
	public void draw(Graphics bbg, JPanel jpanel) {
		MultiplayerMud m= new MultiplayerMud(x,y,d);
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
