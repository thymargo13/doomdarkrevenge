package Entity;

import javax.swing.ImageIcon;

public class Cat extends Player {
	public Cat() {
		super.health = 100;
		super.exp = 200;
		super.attack = 10;
		super.img = "/Resource/animals/cat.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		super.image = ii.getImage();
		super.level =1;
		super.addUpExp = 10;
	}
}
