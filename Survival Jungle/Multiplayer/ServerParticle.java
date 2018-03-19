package Multiplayer;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Local.Particle;
import Multiplayer.MultiplayerCell;
import Multiplayer.ClientGameState;

import java.awt.*;

public class ServerParticle {

	public static ArrayList<ServerParticle> serverParticles = new ArrayList<ServerParticle>();

	public static int particleCount;

	public int x, y, r, g, b, mass;
	public double speed, angle, dx, dy;
	private double goalX, goalY;

	private boolean cellParticle = false;
	private boolean die = false;
	public boolean isShot;
	
	public String img; 
	protected Image image; 
	public String imgBread="/Resource/objects/bread.png";
	public String imgCheese="/Resource/objects/cheese.png";
	public String imgSteak="/Resource/objects/steak.png";

	public ServerParticle(int x, int y, int mass, boolean p, String img){
		particleCount++;
		this.x = x;
		this.y = y;
		this.mass = 60;
		cellParticle = p;
		this.img=img;
		ImageIcon ii = new ImageIcon(getClass().getResource(img)); 
		this.image = ii.getImage();
	}

}