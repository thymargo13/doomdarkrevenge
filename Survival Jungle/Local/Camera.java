package Local;

import java.awt.*;

public class Camera {

	public double x;
	public double y;
	public double sX;
	public double sY;

	public Camera(double x, double y, double sX, double sY) {
		this.x = x;
		this.y = y;
		this.sX = sX;
		this.sY = sY;
	}

	public double map(double x, double min1, double max1, double min2, double max2) {
		return (x - min1) * (max2 - min2) / (max1 - min1) + min2;
	}

	public void set(Graphics bbg) {
		Graphics2D g2 = (Graphics2D) bbg;
		g2.scale(sX, sY);
		g2.translate(-x, -y);
	}

	public void unset(Graphics bbg) {
		Graphics2D g2 = (Graphics2D) bbg;
		g2.translate(x, y);
	}

	public void scale(double sx, double sy) {
		sX = sx;
		sY = sy;
	}

	public void Update(Cell cell) {
		double scaleFactor;

		if (cell.mass < 1000) {
			scaleFactor = map(cell.mass, 10, 1000, 1.2, 0.1);
		} else {
			scaleFactor = 0.1;
		}

		scale(scaleFactor, scaleFactor);
		x = ((cell.x + cell.mass * 0.5) - Local.width / sX * 0.5);
		y = ((cell.y + cell.mass * 0.5) - Local.height / sY * 0.5);
	}
}