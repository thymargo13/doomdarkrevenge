package Local;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Entity.*;

import java.util.ArrayList;

public class Cell {

	public static ArrayList<Cell> cells = new ArrayList<Cell>();
	public static ArrayList<Player> level = new ArrayList<Player>();
	public static int cellCount;
	public Player currentLv; // Type: Player --> get the current Level animal.
	public int levelNum = 0; // Type : int --> change while level up / down.
	public String name; // player name
	double radius = 50; // the radius of the animals
	int currentExp;
	public int currentHp;
	int size = 100;
	int speed = 1;
	boolean isPlayer = false;
	int centre_x;
	int centre_y;

	Cell target; // AI use..
	Particle pTarget; // AI use..

	boolean isTarget = false;
	String targetType = "p"; // to determine it is food or not.

	public double x; // each cell x-coordinate
	public double y; // each cell y-coordinate

	Color cellColor;

	double goalX, goalY; // target x-coordinate.
	boolean goalReached = true;

	public Cell(String name, int x, int y, boolean isPlayer) {
		initLevel();
		this.name = name;
		this.x = x;
		this.y = y;
		this.isPlayer = isPlayer;
		this.randomColor();
		this.currentExp = 0;
		this.currentLv = level.get(levelNum);
		this.currentHp = currentLv.getHealth();
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

	public void randomColor() {

		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color randomColor = new Color(r, g, b);

		cellColor = randomColor;
	}

	public void attack(int currentHp) {
		// this.mass += mass;
		currentHp = currentHp - this.currentLv.getAttack(); // get attacked.
		// if (mass > currentLv.getExp()) {
		// currentLv = level.get(1 + (currentLv.getLevel()));
		// }
	}

	public void addExp(double exp) {
		currentExp += exp;
		// LevelUp();
		if (currentExp > currentLv.getExp()) {
			currentLv = level.get(++levelNum);
		}
	}

	public int returnNearestCell() {
		//
		// int x = 0;
		// int distance = 9999999;
		// int min = distance;
		// int max = 500;
		// for (Cell cell : cells) {
		// if (this != cell) {
		// distance = (int) Math
		// .sqrt((this.x - cell.x) * (this.x - cell.x) + (cell.y - this.y) * (cell.y -
		// this.y));
		// if (distance < min && this.mass > cell.mass + 10) {
		// min = distance;
		// x = cells.indexOf(cell);
		// } else if (distance < min && this.mass < cell.mass + 10 && cell.cellCount ==
		// cells.size()) {
		// x = -1;
		// }
		// }
		// }
		// return x;
		return 1;
	}

	public int returnNearestP() {

		int x = 0;
		// int distance = 99999999;
		// int min = distance;
		//
		// for (Particle p : Particle.particles) {
		// distance = (int) Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) *
		// (this.y - p.y));
		// if (distance < min && this.mass > p.mass) {
		// min = distance;
		// x = Particle.particles.indexOf(p);
		// }
		// }

		return x;
	}

	public void Update() {
		// in case it grow out of bound.
		if (this.levelNum > 7) {
			this.levelNum = 7;
		}
		for (Cell cell : cells) {
			if(this.checkCollide(cell)) {
				if(this.levelNum > cell.levelNum) {
					cell.currentHp -= this.currentLv.getAttack();
					if(cell.currentHp <=0 || this.currentHp <=0) {
						if(cell.levelNum <=0 || this.levelNum <=0)
							die();
						else {
							downgrade();
						}
					}
				}
					boundsOut(cell);
			}
		}
		/*
		 * check it collide? true: check level: 1. one high one low: reduce lower level
		 * hp with higher level attack value. 2. same level: separate. check HP: if
		 * currentHp == 0: die(){ levelNum > 0: levelNum-1 if isPlayer: levelNum == 0:
		 * Game over. else: repaint with full hp & levelNum ==0; }
		 */

		/*
		 * for (Cell cell : cells) { if (this.checkCollide(cell.x, cell.y, cell.mass) &&
		 * this != cell && this.mass > cell.mass + 10) { if (1 / (this.mass / cell.mass)
		 * >= .4 && this.mass < 5000) { attack(cell.currentHp);// get the attacker value
		 * System.out.println("this:" + this.name + " Loser:" + cell.name); } if
		 * (currentHp <= 0 && currentLv != level.get(0)) { respawn(cell);
		 * addExp(cell.currentExp); } respawn(cell); } }
		 */

		if (!isPlayer) {
			if (goalReached) {
				if (returnNearestCell() > -1) { // No Cell Found
					if (!isTarget) {
						target = cells.get(returnNearestCell());
						isTarget = true;
						targetType = "c";
					} else if (isTarget && targetType.equals("c")) {
						targetType = "n";
						isTarget = false;
					}
				} else if (returnNearestCell() == -1) { // Cell Found
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
	
	public void boundsOut(Cell cell) {
		if(this.x < cell.x ) {
			this.x -=5;
			cell.x+=5;
		} else {
			this.x +=5;
			cell.x -=5;
		}
		if(this.y < cell.y ) {
			this.y -=5;
			cell.y +=5;
		} else {
			this.y +=5;
			cell.y -=5;
		}
	}

	// the HP value is reached.
	public void respawn(Cell cell) {
		cell.x = (int) Math.floor(Math.random() * 10001);
		cell.y = (int) Math.floor(Math.random() * 10001);
		System.out.println("Respawn: " + cell.name);
		// this.currentLv = level.get(0);
	}

	// collision
	public boolean checkCollide(Cell cell) {
		//Math.sqrt((x2 − x1)^2 + (y2 − y1)^2)
		double centre_x1 = this.x+50;
		double centre_y1 = this.y+50;
		double centre_x2 = cell.x+50;
		double centre_y2 = cell.y+50;
		double distance = Math.sqrt( Math.pow((centre_x1-centre_x2), 2)+Math.pow((centre_y1-centre_y2),2));
		return distance < 100;
		//return (((this.x + 50) - (cell.x + 50)) < 100 || ((this.y + 50) - (cell.y + 50)) < 100
			//	|| ((cell.x + 50) - (this.x + 50)) < 100 || ((cell.y + 50) - (this.y + 50)) < 100);
		/*
		 * if this.cell centre(x,y) and cell centre(x,y) distance > this.cell radius +
		 * cell.radius then they collided.
		 */
		// return x < this.x + this.size && x + mass > this.x && y < this.y + this.size
		// && y + mass > this.y;
	}

	public void Draw(Graphics bbg, JPanel jpanel) {
		bbg.setColor(cellColor);
		Player player = currentLv;
		bbg.drawImage(player.getImage(), (int) x, (int) y, (int) size, (int) size, jpanel);
		bbg.setColor(Color.BLACK);
		bbg.drawString(name, ((int) x + (int) size / 2 - name.length() * 3),
				((int) y + (int) size / 2 + name.length()));
		// draw the hp bar but n
		bbg.drawString(currentHp + "/ " + player.getHealth(), (int) x, (int) y - 20);
		// draw the exp bar but without scaled.
		bbg.drawRect((int) x, (int) y + 60, player.getExp(), 10);
		bbg.setColor(Color.YELLOW);
		bbg.fillRect((int) x, (int) y + 60, currentExp, 10);
		bbg.setColor(Color.BLACK);
		bbg.drawString("Exp:", ((int) x - 50), (int) y + 60);
	}

}