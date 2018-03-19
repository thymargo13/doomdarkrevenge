package Multiplayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Audio.Audio_player;
import Entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerCell {

	public static ArrayList<ServerCell> serverCells = new ArrayList<ServerCell>();
	public int id;
	public static ArrayList<Player> level = new ArrayList<Player>();
	public static int cellCount;

	public Player currentLv; // Type: Player --> get the current Level animal.
	public int levelNum = 0; // Type : int --> change while level up / down.
	public String name; // player name
	double radius = 50; // the radius of the animals
	public int currentExp; // instant experience
	public int currentHp; // instant health value
	int size = 100;
	int speed = 1;
	public boolean isPlayer = false;
	int centre_x;
	int centre_y;

	public int index=0;

	boolean isTarget = false;
	String targetType = "p"; // to determine it is food or not.

	public double x; // each cell x-coordinate
	public double y; // each cell y-coordinate

	public double goalX, goalY; // target x-coordinate.
	boolean goalReached = true;

	double colX, colY;
	boolean colRached = false;
	int colCount = 0;
	ServerCell colCell;
	
	ClientGameState ClientGameState;

	private HashMap<String, Audio_player> sfx;

	
	public ServerCell(int id, String name, double x, double y) {
		initLevel();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		// this.randomColor();
		this.currentExp = 0;
		this.currentLv = level.get(levelNum);
		this.currentHp = currentLv.getHealth();
		cellCount++;
		 
		//add audio
		sfx = new HashMap<String, Audio_player>();
		sfx.put("exdown",new Audio_player("/Audio/exdown.mp3"));
		sfx.put("exup",new Audio_player("/Audio/exup.mp3"));

	}
	
	public ServerCell(int id, String name, double x, double y, int currentHP , int currentExp) {
		initLevel();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.currentExp = currentExp;
		this.currentLv = level.get(levelNum);
		this.currentHp = currentHP;
		cellCount++;
		 
		//add audio
		sfx = new HashMap<String, Audio_player>();
		sfx.put("exdown",new Audio_player("/Audio/exdown.mp3"));
		sfx.put("exup",new Audio_player("/Audio/exup.mp3"));

	}

	// set the level array list.
	public void initLevel() {
		level.add(new Mouse());
		level.add(new Cat());
		level.add(new Dog());
		level.add(new Wolf());
		level.add(new Leopard());
		level.add(new Tiger());
		level.add(new Lion());
		level.add(new Elephant());
	}

	public void setExp(int exp) {
		this.currentExp = exp;
	}
	
	public void setHp(int hp) {
		this.currentHp = this.currentHp - hp;
	}
	
	public void setLevel(int levelnum) {
		if (this.currentLv.getLevel() > levelnum) {
			// Level down
			this.currentLv = level.get(levelnum);
			this.currentExp = 0;
			this.currentHp = this.currentLv.getHealth();
		} else if (this.currentLv.getLevel() < levelnum){
			// Level up
			this.currentLv = level.get(levelnum);
			this.currentExp = 0;
			this.currentHp = this.currentLv.getHealth();
		} else if (this.currentLv.getLevel() != levelnum){
			
		}
	}

	public void getMouseX(int mx) {
		goalX = mx;
	}

	public void getMouseY(int my) {
		goalY = my;
	}
	


	






}
