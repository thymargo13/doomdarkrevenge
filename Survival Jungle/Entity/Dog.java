package Entity;

import javax.swing.ImageIcon;

public class Dog extends Player{
	public Dog() {
		super.health = 150;
		super.exp = 400;
		super.attack = 15;
		super.img = "/Resource/animals/dog.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		super.image = ii.getImage();
		super.level =2;
		super.addUpExp = 15;
	}
}
