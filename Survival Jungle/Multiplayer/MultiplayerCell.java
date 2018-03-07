package Multiplayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Entity.*;

import Local.Particle;
import java.util.ArrayList;

public class MultiplayerCell {

	public static ArrayList<MultiplayerCell> cells = new ArrayList<MultiplayerCell>();
	public static ArrayList<Player> level = new ArrayList<Player>();
	public static int cellCount;
	public Player currentLv; // Type: Player --> get the current Level animal.
	public int levelNum = 0; // Type : int --> change while level up / down.
	public String name; // player name
	double radius = 50; // the radius of the animals
	public int currentExp; // instant experience
	public int currentHp; // instant health value
	int size = 100;
	int speed = 1;
	boolean isPlayer = false;
	int centre_x;
	int centre_y;
	
	public int id;

	MultiplayerCell target; // AI use..
	Particle pTarget; // AI use..

	boolean isTarget = false;
	String targetType = "p"; // to determine it is food or not.

	public double x; // each cell x-coordinate
	public double y; // each cell y-coordinate

	public double goalX; // target x-coordinate.
	public double goalY;
	boolean goalReached = true;

	public MultiplayerCell(int id, String name, double x, double y, boolean isPlayer) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;

		this.isPlayer = isPlayer;

		//this.randomColor();

		cellCount++;
	}

	// set the level array list.
	public void initLevel() {
		level.add(new Mouse());
		level.add(new Cat());
		level.add(new Dog());
		level.add(new Wolf());
		level.add(new Leopard());
		level.add(new Tiger());
		level.add(new Lion());
		level.add(new Elephant());
	}

	public void attack(int currentHp) {
		currentHp = currentHp - this.currentLv.getAttack(); // get attacked.
		// check level up: if true: reset exp && hp;
	}

	public void addExp(int exp, MultiplayerCell cell) {
		cell.currentExp += exp;
		if (cell.currentExp == cell.currentLv.getExp()) {
			levelUp(cell);
		}

		// if (currentExp > currentLv.getExp()) {
		// currentLv = level.get(++levelNum);
		// currentExp = 0;
		// }
	}

	public void levelUp(MultiplayerCell cell) {
		if (cell.currentLv instanceof Elephant) {
			cell.currentLv = level.get(7);
		} else {
			cell.currentLv = level.get(++levelNum);
			cell.currentExp = 0;
			cell.currentHp = cell.currentLv.getHealth();
		}
	}

	public void die(MultiplayerCell cell, MultiplayerCell winner) {
		int expAdd = cell.currentExp + cell.currentLv.getAddUpExp();
		addExp(expAdd, winner);
		// winner.currentExp += expAdd;
		cell.levelNum = 0;
		cell.currentLv = level.get(cell.levelNum);
		cell.currentHp = cell.currentLv.getHealth();
		cell.currentExp = 0;
		respawn(cell);
	}

	public void downgrade(MultiplayerCell cell) {
		cell.levelNum = cell.levelNum - 1;
		cell.currentLv = level.get(cell.levelNum);
		cell.currentHp = cell.currentLv.getHealth();
		cell.currentExp = 0;
	}

	public void Update() {
		if (this.levelNum > 7) { // not working!!!! elephant will become mouse
									// again~
			this.levelNum = 7;
		}
		// in case it grow out of bound.
		if (this.currentExp >= this.currentLv.getExp()) {
			this.levelNum += 1;
			this.currentLv = level.get(levelNum);
		}

		for (MultiplayerCell cell : cells) {
			if (this.checkCollide(cell)) {
				if (this.levelNum > cell.levelNum) { // attacker level > cell
														// level
					cell.currentHp -= this.currentLv.getAttack(); // cell Health
																	// value -=
																	// attacker
																	// attackValue
					if (cell.currentHp <= 0) { // HP <= 0 die
						if (cell.levelNum == 0) {
							// addExp(this);
							// this.currentExp += cell.currentExp;
							die(cell, this);
						} else {
							downgrade(cell);
							boundsOut(cell);
						}
					}
				}
//				boundsOut(cell);
			}
		}
		if (!isPlayer) {
			if (goalReached) {
				if (returnNearestCell() > -1) {
					if (!isTarget) {
						target = cells.get(returnNearestCell());
						isTarget = true;
						targetType = "c";
					} else if (isTarget && targetType.equals("c")) {
						targetType = "n";
						isTarget = false;
					}
				} else if (returnNearestCell() == -1) {
					if (!isTarget) {
						pTarget = Particle.particles.get(returnNearestP());
						isTarget = true;
						targetType = "p";
					} else if (isTarget) {
						targetType = "n";
						isTarget = false;
					}
				}
				goalReached = false;
			} else {
				double dx = 0;
				double dy = 0;
				if (targetType.equals("c")) {
					if (returnNearestCell() > -1) {
						target = cells.get(returnNearestCell());
						dx = (target.x - this.x);
						dy = (target.y - this.y);
					} else {
						goalReached = true;
					}
				} else if (targetType.equals("p")) {
					pTarget = Particle.particles.get(returnNearestP());
					dx = (pTarget.x - this.x);
					dy = (pTarget.y - this.y);
				} else {
					goalReached = true;
				}
				double distance = Math.sqrt((dx) * (dx) + (dy) * (dy));
				if (distance > 1) {
					x += (dx) / distance * speed;
					y += (dy) / distance * speed;
				} else if (distance <= 1) {
					goalReached = true;
				}

			}
		} else {
			double dx = (goalX - this.x);
			double dy = (goalY - this.y);
			this.x += (dx) * 1 / 50;
			this.y += (dy) * 1 / 50;
			// addMass(10);
		}
	}

	public void getMouseX(int mx) {
		goalX = mx;
	}

	public void getMouseY(int my) {
		goalY = my;
	}

	public void boundsOut(MultiplayerCell cell) {
		if (this.x < cell.x) {
			this.x -= 5;
			cell.x += 5;
		} else {
			this.x += 5;
			cell.x -= 5;
		}
		if (this.y < cell.y) {
			this.y -= 5;
			cell.y += 5;
		} else {
			this.y += 5;
			cell.y -= 5;
		}
	}

	public int returnNearestCell() {

		int x = 0;
		int distance = 9999999;
		int min = distance;
		int max = 200;
		for (MultiplayerCell cell : cells) {
			if (this != cell) {
				distance = (int) Math
						.sqrt((this.x - cell.x) * (this.x - cell.x) + (cell.y - this.y) * (cell.y - this.y));
				if (distance < min && distance <= max && this.levelNum > cell.levelNum) {
					min = distance;
					x = cells.indexOf(cell);
				} else if (distance < min && this.levelNum < cell.levelNum) {
					x = -1;
				}
			}
		}
		return x;
	}

	public int returnNearestP() {

		int x = 0;
		int distance = 99999999;
		int min = distance;

		for (Particle p : Particle.particles) {
			distance = (int) Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
			if (distance < min) { // particle level
				min = distance;
				x = Particle.particles.indexOf(p);
			}
		}

		return x;
	}

	// the HP value is reached.
	public void respawn(MultiplayerCell cell) {
		cell.x = (int) Math.floor(Math.random() * 10001);
		cell.y = (int) Math.floor(Math.random() * 10001);
		System.out.println("Respawn: " + cell.name);
		// this.currentLv = level.get(0);
	}

	// collision
	public boolean checkCollide(MultiplayerCell cell) {
		// Math.sqrt((x2 âˆ? x1)^2 + (y2 âˆ? y1)^2)
		double centre_x1 = this.x + 50;
		double centre_y1 = this.y + 50;
		double centre_x2 = cell.x + 50;
		double centre_y2 = cell.y + 50;
		double distance = Math.sqrt(Math.pow((centre_x1 - centre_x2), 2) + Math.pow((centre_y1 - centre_y2), 2));
		return distance < 100;
		// return (((this.x + 50) - (cell.x + 50)) < 100 || ((this.y + 50) -
		// (cell.y +
		// 50)) < 100
		// || ((cell.x + 50) - (this.x + 50)) < 100 || ((cell.y + 50) - (this.y
		// + 50)) <
		// 100);
		/*
		 * if this.cell centre(x,y) and cell centre(x,y) distance > this.cell
		 * radius + cell.radius then they collided.
		 */
		// return x < this.x + this.size && x + mass > this.x && y < this.y +
		// this.size
		// && y + mass > this.y;
	}

	public void Draw(Graphics bbg, JPanel jpanel) {
		// bbg.setColor(cellColor);
		Player player = currentLv;
		bbg.drawImage(player.getImage(), (int) x, (int) y, (int) size, (int) size, jpanel);
		bbg.setColor(Color.BLACK);
		bbg.drawString(name, ((int) x + (int) size / 2 - name.length() * 3),
				((int) y + (int) size / 2 + name.length()));
		// draw the hp bar but n
		bbg.drawString(currentHp + "/ " + player.getHealth(), (int) x, (int) y - 20);
		// draw the exp bar but without scaled.
		bbg.drawRect((int) x, (int) y + 60, 100, 10);
		bbg.setColor(Color.YELLOW);
		if (this.name == "Bruce") {
			System.out.println("current:" + currentExp);
			System.out.println("Scaled current:" + (int) ((float) currentExp / player.getExp()) * 100);
		}

		bbg.fillRect((int) x, (int) y + 60, (int) (((float) currentExp / player.getExp()) * 100), 10);
		bbg.setColor(Color.BLACK);
		bbg.drawString("Exp:", ((int) x - 50), (int) y + 60);
	}

}