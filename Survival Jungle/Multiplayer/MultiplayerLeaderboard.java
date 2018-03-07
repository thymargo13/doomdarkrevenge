package Multiplayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MultiplayerLeaderboard {

	private int x;
	private int y;

	public static ArrayList<MultiplayerCell> cellsCopy = MultiplayerCell.cells;

	private int z = 10;

	private int currentY = 0;

	Color color = new Color(50, 50, 50, 128);

	int spots[] = new int[z];

	public MultiplayerLeaderboard() {
		for (int i = 0; i < z; i++) {
			spots[i] = currentY;
			currentY += 30;
		}
	}

	public void Update() {
		cellsCopy = MultiplayerCell.cells;
		Collections.sort(cellsCopy, new leaderComparator());
	}

	public void Draw(Graphics bbg) {
		for (int i = 0; i < z; i++) {
			bbg.setColor(color);
			bbg.drawRect(x, y + spots[i], 125, 30);
			bbg.fillRect(x, y + spots[i], 125, 30);
			bbg.setColor(Color.BLACK);	//color of the leader board string
			if (MultiplayerCell.cells.size() >= z) {
				String co = "x: " +cellsCopy.get(i).x+" y:"+ cellsCopy.get(i).y;
				bbg.drawString("#" + (i + 1) + ": " + cellsCopy.get(i).name + " : " + (int) cellsCopy.get(i).currentExp + co , x,
						y + spots[i] + 25);
			}
		}
	}

	private class leaderComparator implements Comparator<MultiplayerCell> {
		@Override
		public int compare(MultiplayerCell c1, MultiplayerCell c2) {
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