package Entity;

import javax.swing.ImageIcon;

public class Mouse extends Player {
	public Mouse() {
		super.health = 50;
		super.maxHealth = 50;
		super.exp = 0;
		super.maxExp = 50;
		super.dead = false;
		super.speeding = false;
		super.speed = 1;
		super.img = "/Resource/animals/mouse.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		super.image = ii.getImage();
	}
}
