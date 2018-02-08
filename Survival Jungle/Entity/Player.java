package Entity;

import java.awt.Image;

public class Player {

	// player stuff
	protected int health;
	protected int maxHealth;
	protected int exp;
	protected int maxExp;
	protected boolean dead;
	protected boolean speeding;
	protected long speedTimer;
	protected String name;
	protected String img;
	protected Image image;
	public int x = 100;
	public int y = 100;
	
	public int degree = 30;
	
	//constructor
	public Player() {
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
	public long getSpeedTimer() {
		return speedTimer;
	}
	public void setSpeedTimer(long speedTimer) {
		this.speedTimer = speedTimer;
	}

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

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void move() {
		
	}
}
