package Local;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import Map.Board;

public class PoolTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPool() {
		Pool p=new Pool(3,4,5);
	}

	@Test
	public void testDraw() {
		Pool p=new Pool(3,4,5);
		BufferedImage backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics bbg= backBuffer.getGraphics();
		Board b=new Board();
		JPanel jpanel =b;
		p.draw(bbg,jpanel);
	}

	@Test
	public void testUpdate() {
		for (Cell cell : Cell.cells) {

			int index=cell.currentLv.getHealth();
			if (true) {
				if(cell.currentLv != cell.level.get(2) || cell.currentLv != cell.level.get(3)) {
					cell.reduceHp(1, cell);
				}
//				if(cell.currentLv == cell.level.get(0)) {
//					cell.reduceHp(1, cell);
//				}
				if(cell.currentHp<=index-30) {
//					cell.currentHp=cell.currentHp;
					break;
				}
//				cell.addExp(5,cell); //add exp
			} 
		}
	}

}
