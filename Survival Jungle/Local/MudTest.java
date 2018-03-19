package Local;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import Map.Board;

public class MudTest {

	int x;
	int y;
	double colX, colY;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMud() {
		Mud m=new Mud(3,4,5);
	}

	@Test
	public void testUpdate() {
		
		for (Cell cell : Cell.cells) {
			
			if(true) {
				int x;
				int y;
				double colX, colY;
				double dx = (this.colX - this.x);
				double dy = (this.colY - this.y);
				cell.x += (dx) * 1 / 100;
				cell.y += (dy) * 1 / 100;
			}
		}
	}

	

	@Test
	public void testDraw() {
		Mud m=new Mud(3,4,5);
		BufferedImage backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics bbg= backBuffer.getGraphics();
		Board b=new Board();
		JPanel jpanel =b;
		m.draw(bbg,jpanel);
	}

}
