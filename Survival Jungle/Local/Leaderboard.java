package Local;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 
 * @author hxy719@student.bham.ac.uk
 * Show the leaderboard on the left corner of map
 *
 */
public class Leaderboard {

	private int x;
	private int y;

	public static ArrayList<Cell> cellsCopy = Cell.cells;

	private int z = 10;

	private int currentY = 0;

	Color color = new Color(50, 50, 50, 128);

	int spots[] = new int[z];

	public Leaderboard() {
		for (int i = 0; i < z; i++) {
			spots[i] = currentY;
			currentY += 30;
		}
	}
	/**
	 * Update the information of leaderboard
	 */
	public void Update() {
		cellsCopy = Cell.cells;
		Collections.sort(cellsCopy, new leaderComparator());
	}
	/**
	 * 
	 * @param bbg
	 * draw LeaderBoard
	 */
	public void Draw(Graphics bbg) {
		for (int i = 0; i < z; i++) {
			bbg.setColor(color);
			bbg.drawRect(x, y + spots[i], 125, 30);
			bbg.fillRect(x, y + spots[i], 125, 30);
			bbg.setColor(Color.BLACK);	//color of the leader board string
			if (Cell.cells.size() >= z) {
				String co = " x: " + (int)cellsCopy.get(i).x+" y: "+ (int)cellsCopy.get(i).y;
				bbg.drawString("#" + (i + 1) + ": " + cellsCopy.get(i).name + " : " + (int) cellsCopy.get(i).currentExp + co , x,
						y + spots[i] + 25);
			}
		}
	}
	/**
	 * 
	 * @author hxy719@student.bham.ac.uk
	 * Compare the currentExp of two cells
	 *
	 */
	private class leaderComparator implements Comparator<Cell> {
		@Override
		public int compare(Cell c1, Cell c2) {
			if (c1.currentExp == c2.currentExp) {
				return 0;
			} else if (c1.currentExp > c2.currentExp) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}