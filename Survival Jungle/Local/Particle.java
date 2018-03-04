package Local;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Entity.foodonMap;
import Map.GameState;
import java.awt.*;

public class Particle {

	public static ArrayList<Particle> particles = new ArrayList<Particle>();

	public static int particleCount;

	public int x, y, r, g, b, mass;
	public double speed, angle, dx, dy;
	private double goalX, goalY;
	public String img; 
	protected Image image; 
	private boolean cellParticle = false;
	private boolean die = false;
	public boolean isShot;
	public String img1="/Resource/objects/bread.png";
	public String img2="/Resource/objects/cheese.png";
	public String img3="/Resource/objects/steak.png";
	private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
			(int) Math.floor(Math.random() * 256));

	public Particle(int x, int y, int mass, boolean p, String img) {
		particleCount++;
		this.x = x;
		this.y = y;
		this.mass = 20;
		cellParticle = p;
//		this.img=foodonMap.getCheese();
//		this.img= "/Resource/objects/bread.png";
		this.img=img;
		ImageIcon ii = new ImageIcon(getClass().getResource(img)); 
		this.image = ii.getImage();
	}
//	public void Update() {
//		for (Cell cell : Cell.cells) {
//			if (this.checkCollide(cell.x, cell.y) && !cellParticle) {
//				
//				// mass = exp
//				if (cell.currentExp <= 200) {
//					cell.addExp(5,cell); //add exp
//				}
//				if (cell.currentExp >= 200) {
//					cell.isTarget = false;
//					cell.goalReached = true;
//					cell.targetType = "c";
//				}
//				if (cell.targetType.equals("p")) {
//					cell.goalReached = true;
//					cell.isTarget = false;
//				}
//				this.x = (int) Math.floor(Math.random() * 10001);
//				this.y = (int) Math.floor(Math.random() * 10001);
//			} else if (this.checkCollide(cell.x, cell.y) && cellParticle && !cell.isPlayer) {
//				cell.addExp(5,cell); //add exp
//				this.die = true;
//			}
//		}
//		if (isShot) {
//			dx = (speed) * Math.cos(angle);
//			dy = (speed) * Math.sin(angle);
//			x += dx;
//			y += dy;
//			speed -= 0.1;
//			if (speed <= 0) {
//				isShot = false;
//				speed = 0;
//			}
//		}
//	}

	public void Update(String img) {
		for (Cell cell : Cell.cells) {
			if (this.checkCollide(cell.x, cell.y) && !cellParticle) {
				
				// mass = exp
				if (cell.currentExp <= 200) {
					if(img==img1) {
						cell.addExp(5,cell); 
					}else if(img==img2){
						cell.addExp(10,cell); 
					}else if(img==img3) {
						cell.addExp(15,cell); 
					}
//					cell.addExp(5,cell); //add exp
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
			} else if (this.checkCollide(cell.x, cell.y) && cellParticle && !cell.isPlayer) {
				if(img==img1) {
					cell.addExp(5,cell); 
				}else if(img==img2){
					cell.addExp(10,cell); 
				}else if(img==img3) {
					cell.addExp(15,cell); 
				}
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

	public void Draw(Graphics bbg, JPanel jpanel) {
//		img= "/Resource/objects/bread.png";
		Particle p=new Particle(x,y,mass,cellParticle,img);
		bbg.drawImage(p.getImage(),(int) x, (int) y, (int) mass, (int) mass, jpanel);
//		bbg.setColor(color);
//		bbg.fillRect(x, y, 10, 10);
//		bbg.drawRect(x, y, 10, 10);
	}

	public boolean getHealth() {
		return die;
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