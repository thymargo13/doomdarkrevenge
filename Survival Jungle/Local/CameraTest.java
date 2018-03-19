package Local;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

public class CameraTest {
	public double x;
	public double y;
	public double sX;
	public double sY;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCamera() {
		Camera c=new Camera(1,1,2,2);
	}


	@Test
	public void testSet() {
		BufferedImage backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics bbg= backBuffer.getGraphics();
		Graphics2D g2 = (Graphics2D) bbg;
		g2.translate(-x, -y);
	}

	@Test
	public void testUnset() {
		BufferedImage backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics bbg= backBuffer.getGraphics();
		Graphics2D g2 = (Graphics2D) bbg;
		g2.translate(x, y);
	}

	
	@Test
	public void testUpdate() {
		for (Cell cell : Cell.cells) {
			double scaleFactor = 0.1;
			x = ((cell.x + cell.size * 0.5) - 800 / sX * 0.5);
			y = ((cell.y + cell.size * 0.5) - 600 / sY * 0.5);
		}
	}

}
