package Multiplayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Khang Sheong Foong
 * This is a copy of the leaderboard class from the Local game mode. In order for the leaderboard to work in Multiplayer mode, there's a little modification to this class.
 */
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
			bbg.setColor(Color.CYAN);	//color of the leader board string
			try {
				bbg.drawString("#" + (i + 1) + ": " + cellsCopy.get(i).name + " : " + (int) cellsCopy.get(i).currentExp, x,	y + spots[i] + 25);
			} catch (Exception ex) {
				
			}
		}
	}

	private class leaderComparator implements Comparator<MultiplayerCell> {
		@Override
		public int compare(MultiplayerCell c1, MultiplayerCell c2) {
			if (c1.calTotalExp()  == c2.calTotalExp()) {
				return 0;
			} else if (c1.calTotalExp() > c2.calTotalExp()) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}