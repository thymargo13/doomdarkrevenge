package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Mouse extends Player {
	public Mouse() {
		super.health = 50;
		super.exp = 50;
		super.attack = 5;
		super.img = "/Resource/animals/mouse.png";
		ImageIcon ii = new ImageIcon(getClass().getResource(img));
		super.image = ii.getImage();
		super.level =0;
		super.addUpExp = 5;
		super.forestHide=false;
	}
}
