package Entity;

import java.awt.Image;

public class Player {

	// player stuff
	protected int health;
	protected int exp;
	protected String name;
	protected String img; // location of img
	protected Image image;
	protected int attack;
	protected int level;
	protected int addUpExp;
	protected boolean forestHide;
	// constructor

	public boolean getForestHide() {
		return forestHide;
	}
	
	public void setForestHide(boolean forestHide) {
		this.forestHide=forestHide;
	}
	
	public int getLevel() {
		return level;
	}

	public int getAddUpExp() {
		return addUpExp;
	}

	public void setAddUpExp(int addUpExp) {
		this.addUpExp = addUpExp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	// getter and setter
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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

}
