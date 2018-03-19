package Local;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import Map.Board;

public class ForestTest {
	int x;
	int y;
	double colX, colY;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testForest() {
		Forest f=new Forest(30,42,55);
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

}
