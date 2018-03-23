package Multiplayer;
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

/**
 * @author Khang Sheong Foong
 * This is a copy of the Forest class from the Local game mode. In order to make it synchronized with server and clients, some minor modification had been done in this seperate class.
 * This class will perform checking for which animal can enter it and which animal can't.
 */
public class MultiplayerForest {
	public static ArrayList<MultiplayerForest> forests = new ArrayList<MultiplayerForest>();
	public static int forestCount;
	protected String img; 
	protected Image image; 
	private int x;
	private int y;
	private int d=270;
	int colCount = 0;
	double colX, colY;
	public boolean couldHide=false;	//default----->no special events

//	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//			(int) Math.floor(Math.random() * 256));
	public MultiplayerForest(int x,int y, int d) {
		forestCount++;
		this.x=x;
		this.y=y;
		this.d=d;
		this.img= "/Resource/objects/forest.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img)); 
		this.image = ii.getImage();

	}
	public boolean checkHide(int level) {
		if(level==1 || level==4|| level==5) {
			couldHide=true;
		}else {
			couldHide=false;
		}
		return couldHide;
	}
	public void Update() {
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			couldHide=false;
			this.colCount = 0;
			if(cell.currentLv == cell.level.get(1) || cell.currentLv == cell.level.get(4) || 
					cell.currentLv == cell.level.get(5)) {
				couldHide=true;
			}else {
				couldHide=false;
				if(checkCollide(cell.x,cell.y)) {
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
		} else if (this.x > cell.x){
			cell.colX = cell.x - distance;
		}
		if (this.y < cell.y) {
			cell.colY = cell.y + distance;
		} else if (this.y > cell.y){
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
		MultiplayerForest f= new MultiplayerForest(x,y,d);
		bbg.drawImage(f.getImage(),(int) x, (int) y, (int) d, (int) d, jpanel);
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
