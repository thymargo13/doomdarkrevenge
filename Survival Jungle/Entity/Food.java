package Entity;

import java.awt.*;
import javax.swing.JPanel;

public abstract class Food {
	private String img; //image
	private int r; //radius
	private int x,y; //coordinate
	
	//constructor
	public Food() {}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
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
	
	public abstract void draw (Graphics g) ;
}
