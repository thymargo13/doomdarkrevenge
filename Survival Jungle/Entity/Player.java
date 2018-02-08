package Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Player {

	// player stuff
	protected int health;
	protected int maxHealth;
	protected int exp;
	protected int maxExp;
	protected boolean dead;
	protected boolean speeding;
	protected int speed;
	protected String name;
	protected String img; //location of img
	protected Image image; 
	private int x = 100; // x-coordinate
	private int y = 100; // y-coordinate
	private double degree = 30;
	private int dx;
	private int dy;
	//constructor
	public Player() {
		speed = 1;
	}

	//getter and setter
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}
	public boolean isSpeeding() {
		return speeding;
	}
	public void setSpeeding(boolean speeding) {
		this.speeding = speeding;
	}
//	public long getSpeed() {
//		return speed;
//	}
//	public void setSpeed(int speed) {
//		this.speed = speed;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void move() {	
		double distance;
		distance = Math.sqrt(((dx-x)*(dx-x))+((dy-y)*(dy-y)));
		System.out.println(distance);
	}
	public Graphics2D draw (Graphics2D g, JPanel jp) {
		//Graphics2D g2d = (Graphics2D) g;
		g.rotate(Math.toRadians(getDegree()),getX(),getY());
		g.drawImage(getImage(), getX()-50, getY()-50, 100, 100,jp);
	//	Toolkit.getDefaultToolkit().sync();
		return g;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
}
