package Multiplayer;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.Mouse;
import Entity.Player;
import Local.Particle;

public class MultiplayerCell {
	
	
	public static ArrayList<MultiplayerCell> cells = new ArrayList<MultiplayerCell>();
	public static int cellCount;

	public String name;
	public double mass = 10;
	int speed = 1;
	public boolean isPlayer = false;
	Particle pTarget;
	public boolean isTarget = false;
	public String targetType = "p";
	public double x;
	public double y;
	
	public Color cellColor;
	public double goalX, goalY;
	public boolean goalReached = true;
	
	public int id;

	// isPlayer = is this PC player
	public MultiplayerCell(int id, String name, double x, double y, boolean isPlayer) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;

		this.isPlayer = isPlayer;

		this.randomColor();

		cellCount++;
	}

	public void randomColor() {

		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color randomColor = new Color(r, g, b);

		this.cellColor = randomColor;
	}

	public void addMass(double mass) {
		this.mass += mass;
	}

	public void Update() {
		if (this.mass > 3500) {
			this.mass = 3500;
		}
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			if (this.checkCollide(cell.x, cell.y, cell.mass) && this != cell && this.mass > cell.mass + 10) {
				if (1 / (this.mass / cell.mass) >= .4 && this.mass < 4000) {
					addMass(cell.mass);
					// If player eats player
				}
				respawn(cell);
			}
		}
		// remove if else
		// update goalX and goalY if receive data from queue about moving
//		if (!this.isPlayer) {
//			//if not this pc player
//			// get data from the queue
//		} else {
			double dx = (this.goalX - this.x);
			double dy = (this.goalY - this.y);
			this.x += (dx) * 1 / 50;
			this.y += (dy) * 1 / 50;
			// addMass(10);
//		}
	}

	public void getMouseX(int mx) {
		goalX = mx;
	}

	public void getMouseY(int my) {
		goalY = my;
	}

	public void respawn(MultiplayerCell cell) {
		cell.x = (int) Math.floor(Math.random() * 10001);
		cell.y = (int) Math.floor(Math.random() * 10001);
		cell.mass = 20;
	}

	public boolean checkCollide(double x, double y, double mass) {
		return x < this.x + this.mass && x + mass > this.x && y < this.y + this.mass && y + mass > this.y;
	}

	public void shootMass() {
		double startX = this.x + this.mass / 2;
		double startY = this.y + this.mass / 2;
		Particle a = new Particle((int) startX, (int) startY, 100, true);
		Particle.particles.add(a);
		a.speed = 5;
		a.angle = Math.atan2(goalX - startX, goalY - startY);
		a.dx = (a.speed) * Math.cos(a.angle);
		a.dy = (a.speed) * Math.sin(a.angle);
		a.isShot = true;
	}

	public void Draw(Graphics bbg, JPanel jpanel) {
		bbg.setColor(cellColor);
//		bbg.drawRect((int) x, (int) y, (int) mass, (int) mass);
//		bbg.fillRect((int) x, (int) y, (int) mass, (int) mass);
//		bbg.drawOval((int) x, (int) y, (int) mass, (int) mass);
//		bbg.fillOval((int) x, (int) y, (int) mass, (int) mass);
		Player player = new Mouse();
//		System.out.println(player.getImage());
		bbg.drawImage(player.getImage(), (int) x, (int) y, (int) mass, (int) mass, jpanel);
		bbg.setColor(Color.BLACK);
		bbg.drawString(name, ((int) x + (int) mass / 2 - name.length() * 3),
				((int) y + (int) mass / 2 + name.length()));
	}

//	private Image getReource(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}