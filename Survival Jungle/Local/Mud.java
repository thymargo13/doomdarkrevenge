package Local;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author hxy719@student.bham.ac.uk Mud class----include show the Mud on the
 *         map, and the function of Mud: Mud is a similar to Forest, but it can
 *         only hide the mouse from its predator.
 */
public class Mud {
	public static ArrayList<Mud> muds = new ArrayList<Mud>();
	public static int mudCount;
	protected String img;
	protected Image image;
	private int x;
	private int y;
	private int d = 270;
	double colX, colY;
	public boolean couldHide = false;

	// private Color color = new Color((int) Math.floor(Math.random() * 256),
	// (int) Math.floor(Math.random() * 256),
	// (int) Math.floor(Math.random() * 256));
	/**
	 * 
	 * @param x:
	 *            the x position of mud
	 * @param y:
	 *            the y position of mud
	 * @param d:
	 *            the radius of mud
	 */
	public Mud(int x, int y, int d) {
		mudCount++;
		this.x = x;
		this.y = y;
		this.d = d;
		this.img = "/Resource/objects/mud.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		this.image = ii.getImage();
	}

	/**
	 * Update the states of muds and have different responses to different
	 * animals
	 */
	public void Update() {
		for (Cell cell : Cell.cells) {

			if (checkCollide(cell.x, cell.y)) {

				if (cell.currentLv == cell.level.get(0)) {

				} else {
					boundsOut(cell);
					cell.colRached = true;
				}
			}
		}

	}

	/**
	 * 
	 * @param cell
	 *            let the cell have different movements when collide (rebound)
	 */
	public void boundsOut(Cell cell) {
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

	/**
	 * 
	 * @param x:
	 *            the x position of mud
	 * @param y:
	 *            the y position of mud
	 * @return true/false
	 */
	private boolean checkCollide(double x, double y) {
		double centre_x1 = x - 75;
		double centre_y1 = y - 70;
		// this.x & this.y is particle coordinate.
		double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
		return distance < 195;
	}

	public void draw(Graphics bbg, JPanel jpanel) {
		Mud m = new Mud(x, y, d);
		bbg.drawImage(m.getImage(), (int) x, (int) y, (int) d, (int) d, jpanel);
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
		this.d = d;
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
