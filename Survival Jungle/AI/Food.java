package AI;
import java.awt.Color;  
import java.awt.Graphics;  
import java.awt.Point;  
import java.util.Random;  
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Entity.Player;

//�������water���ֵ�λ�ã���������ʧ�����ˢ��
public class Food extends JFrame {
	//����������
	private GamePanel gamepanel;
	private Player player;
	//С����������
	public Point location;
//	public Point size;
//	public int x;
//	public int y;
//	public Color col;
	private Random rand=new Random();
	public ArrayList<Food> ball =new ArrayList<Food>();	//������ʳ���������
	public Food(GamePanel gp, Player p) {
		gamepanel=gp;
		player=p;
//		x=rand.nextInt(800);
//		y=rand.nextInt(600);
		location=new Point(Math.abs(rand.nextInt(gp.getWidth())%gamepanel.getWidth()),Math.abs(rand.nextInt(gp.getHeight())%gamepanel.getHeight()));  
//		size=new Point(60,60);
	}
	
//	private Image food;
	public void drawFood(Graphics g) {
		g.setColor(Color.RED);  
		int d=30;
		g.fillOval(location.x, location.y, d, d);  

	}
	public void drawWater(Graphics g) {
		g.setColor(Color.BLUE);  
		int d=30;
		g.fillOval(location.x, location.y, d, d);  

	}
//		food=new ImageIcon().getImage();
	public void regenerate() {
		if((player.x-location.x)*(player.x-location.x)+(player.y-location.y)*(player.y-location.y)<(player.r*player.r)){  
            //��̰���ߵ���ͷ��ʳ�﷢����ײ������������µ�ʳ��λ��  
            location=new Point(Math.abs(rand.nextInt(gp.getWidth())%gamepanel.getWidth()),Math.abs(rand.nextInt(gp.getHeight())%gamepanel.getHeight()));  
            
        }               
	}
}