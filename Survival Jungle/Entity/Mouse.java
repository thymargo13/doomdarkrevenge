package Entity;

import javax.swing.ImageIcon;

public class Mouse extends Player {
	public Mouse() {
		super.health = 50;
		super.exp = 50;
		super.attack = 2000;
		super.img = "/Resource/animals/mouse.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		super.image = ii.getImage();
		super.level =0;
		super.addUpExp = 5;
	}
}
