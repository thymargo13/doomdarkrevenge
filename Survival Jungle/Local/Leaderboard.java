package Local;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Multiplayer.*;

public class Leaderboard {

	private int x;
	private int y;

	public static ArrayList<Cell> cellsCopy = Cell.cells;
	
	// KSFOONG
	public static ArrayList<MultiplayerCell> multiplayerCellCopy = MultiplayerCell.cells;

	private int z = 10;

	private int currentY = 0;

	Color color = new Color(50, 50, 50, 128);

	int spots[] = new int[z];

	// KSFOONG: for multiplayer
	boolean multiplayer = false;
	
	public Leaderboard() {
		this.multiplayer = false;
		for (int i = 0; i < z; i++) {
			spots[i] = currentY;
			currentY += 30;
		}
	}
	
	// KSFOONG: for Multiplayer
	public Leaderboard(boolean multiplayer) {
		this.multiplayer = multiplayer;
		for (int i = 0; i < z; i++) {
			spots[i] = currentY;
			currentY += 30;
		}
	}

	public void Update() {
		if(!multiplayer) {
			cellsCopy = Cell.cells;
			Collections.sort(cellsCopy, new leaderComparator());
		} else {
			multiplayerCellCopy = MultiplayerCell.cells;
			Collections.sort(multiplayerCellCopy, new multiplayerLeaderComparator());
		}

	}

	public void Draw(Graphics bbg) {
		for (int i = 0; i < z; i++) {
			bbg.setColor(color);
			bbg.drawRect(x, y + spots[i], 125, 30);
			bbg.fillRect(x, y + spots[i], 125, 30);
			bbg.setColor(Color.WHITE);	//color of the leader board string
			// KSFOONG
			if (!multiplayer) {
				if (Cell.cells.size() >= z) {
					bbg.drawString("#" + (i + 1) + ": " + cellsCopy.get(i).name + " : " + (int) cellsCopy.get(i).mass, x,
							y + spots[i] + 25);
				}
				// KSFOONG
			} else {
				if (MultiplayerCell.cells.size() >= z) {
					bbg.drawString("#" + (i + 1) + ": " + multiplayerCellCopy.get(i).name + " : " + (int) multiplayerCellCopy.get(i).mass, x, y + spots[i] + 25);
				}
			}
		}
	}

	private class leaderComparator implements Comparator<Cell> {
		@Override
		public int compare(Cell c1, Cell c2) {
			if (c1.mass == c2.mass) {
				return 0;
			} else if (c1.mass > c2.mass) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	
	// KSFOONG
	private class multiplayerLeaderComparator implements Comparator<MultiplayerCell> {
		@Override
		public int compare(MultiplayerCell c1, MultiplayerCell c2) {
			if (c1.mass == c2.mass) {
				return 0;
			} else if (c1.mass > c2.mass) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}