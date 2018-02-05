package Entity;

public class Player {

	// player stuff
	private int health;
	private int maxHealth;
	private int exp;
	private int maxExp;
	private boolean dead;
	private boolean speeding;
	private long speedTimer;
	private String name;
	private String img;
	
	//constructor
	public Player() {}
	
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
	
}
