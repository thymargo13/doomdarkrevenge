package Local;
import java.awt.*;  
import javax.swing.*; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author hxy719@student.bham.ac.uk
 * Pool class----include show the Pool on the map, and the function of Pool:
 * Pool is a trap for most animals, it will reduce their HP if they get into the Lake. 
 * But Dogs and Wolves can swim in the Pool which is a protection place for Dog and Wolf. 
 *
 */
public class Pool {
		public static ArrayList<Pool> pools = new ArrayList<Pool>();
		public static int poolCount;
		protected String img; 
		protected Image image; 
		private int x;
		private int y;
		private int d=270;
		public boolean isShot=true;
//		BufferedImage bg = null; 

//		private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//				(int) Math.floor(Math.random() * 256));
		/**
		 * 
		 * @param x: the x position of pool
		 * @param y: the y position of pool
		 * @param d: the radius of pool
		 */
		public Pool(int x,int y, int d) {
			poolCount++;
			this.x=x;
			this.y=y;
			this.d=d;
			this.img= "/Resource/objects/pool.png";
			ImageIcon ii = new ImageIcon(getClass().getResource(img));
			this.image = ii.getImage();
		}

		public void draw(Graphics bbg, JPanel jpanel) {
			Pool p= new Pool(x,y,d);
			bbg.drawImage(p.getImage(),(int) x, (int) y, (int) d, (int) d, jpanel);
		}
		/**
		 * Update the states of pools
		 * and have different responses to different animals
		 */
		public void Update() {
			for (Cell cell : Cell.cells) {

				int index=cell.currentLv.getHealth();
				if (this.checkCollide(cell.x, cell.y)) {
					if(cell.levelNum != 2 || cell.levelNum != 3) {
						cell.reduceHp(1, cell);
					}
//					if(cell.currentLv == cell.level.get(0)) {
//						cell.reduceHp(1, cell);
//					}
					if(cell.currentHp<=index-30) {
//						cell.currentHp=cell.currentHp;
						break;
					}
//					cell.addExp(5,cell); //add exp
				} 
				
			}
		}
		/**
		 * 
		 * @param x: the x position of pool
		 * @param y: the y position of pool
		 * @return  true/false
		 */
		private boolean checkCollide(double x, double y) {
			double centre_x1 = x-75;
			double centre_y1 = y-75 ;
			//this.x & this.y is particle coordinate.
			double distance = Math.sqrt(Math.pow((centre_x1 - this.x), 2) + Math.pow((centre_y1 - this.y), 2));
//			if(distance<500) {
//				isShot=true;
//			}
//			System.out.println("test!!");
			return distance < 150;
//			return isShot;
		//	return x < this.x + 10 && x + mass > this.x && y < this.y + 10 && y + mass > this.y;
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
			this.d=d;
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

