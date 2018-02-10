package Local;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Pool {
		public static ArrayList<Pool> pools = new ArrayList<Pool>();
		public static int poolCount;
		protected String img; 
		protected Image image; 
		private int x;
		private int y;
		private int d=200;
//		private Color color = new Color((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256),
//				(int) Math.floor(Math.random() * 256));
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

