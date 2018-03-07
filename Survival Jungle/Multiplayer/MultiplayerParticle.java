package Multiplayer;

import java.util.ArrayList;

import Multiplayer.MultiplayerCell;
import Multiplayer.MultiplayerGameState;

import java.awt.*;

public class MultiplayerParticle {

	public static ArrayList<MultiplayerParticle> particles = new ArrayList<MultiplayerParticle>();

	public static int particleCount;

	public int x, y, r, g, b, mass;
	public double speed, angle, dx, dy;
	private double goalX, goalY;

	private boolean cellParticle = false;
	private boolean die = false;
	public boolean isShot;

	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
			(int) Math.floor(Math.random() * 256));

	public MultiplayerParticle(int x, int y, int mass, boolean p) {
		particleCount++;
		this.x = x;
		this.y = y;
		this.mass = mass;
		cellParticle = p;
	}

	public void Update(MultiplayerGameState MultiplayerGameState) {
		for (MultiplayerCell cell : MultiplayerCell.cells) {
				if (this.checkCollide(cell.x, cell.y) && !cellParticle) {
				
				// mass = exp
				if (cell.currentExp <= 200) {
					cell.addExp(5,cell); //add exp
				}
				if (cell.currentExp >= 200) {
					cell.isTarget = false;
					cell.goalReached = true;
					cell.targetType = "c";
				}
				if (cell.targetType.equals("p")) {
					cell.goalReached = true;
					cell.isTarget = false;
				}
				this.x = (int) Math.floor(Math.random() * 10001);
				this.y = (int) Math.floor(Math.random() * 10001);
				String message = "SCORE:" + cell.id + ":" + cell.currentExp;
				MultiplayerGameState.sendMessage(message);
			} 
				else if (this.checkCollide(cell.x, cell.y) && cellParticle && !cell.isPlayer) {
//				cell.addExp(5,cell); //add exp
				this.die = true;
			}
		}
		if (isShot) {
			dx = (speed) * Math.cos(angle);
			dy = (speed) * Math.sin(angle);
			x += dx;
			y += dy;
			speed -= 0.1;
			if (speed <= 0) {
				isShot = false;
				speed = 0;
			}
		}
	}
	
	

	private boolean checkCollide(double x, double y) {
		double centre_x1 = x + 50;
		double centre_y1 = y + 50;
		//this.x & this.y is particle coordinate.
		double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
		return distance < 50;
	//	return x < this.x + 10 && x + mass > this.x && y < this.y + 10 && y + mass > this.y;
	}

	public void Draw(Graphics bbg) {
		bbg.setColor(color);
		bbg.fillRect(x, y, 10, 10);
		bbg.drawRect(x, y, 10, 10);
	}

	public boolean getHealth() {
		return die;
	}

}