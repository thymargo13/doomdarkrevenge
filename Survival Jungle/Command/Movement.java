package Command;

import java.awt.Point;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class Movement {
	private double angle = 0;

	public double calculateAngle(Point nowCor, Point tagCor) {
		angle = (double)(tagCor.getY() - nowCor.getY()) /(double) (tagCor.getX() - nowCor.getX());
		return angle;
	}
	
	public void up(Point coordinate){
		coordinate.setLocation(coordinate.getX(), coordinate.getY()+1);
	}
	
	public void down(Point coordinate){
		coordinate.setLocation(coordinate.getX(), coordinate.getY()-1);
	}
	
	public void right(Point coordinate){
		coordinate.setLocation(coordinate.getX()+1, coordinate.getY());
	}
	
	public void left(Point coordinate){
		coordinate.setLocation(coordinate.getX()-1, coordinate.getY());
	}
	
	//if it's target location, return true or false.
	public boolean isTag (Point nowCor, Point tagCor){
		if(nowCor.getX() == tagCor.getX() && nowCor.getY() == tagCor.getY()) 
			{
				return true;
			}
		else return false;
		
	}
	
	//speed up method x+10 and y+10
	public void flash(Point nowCor, Point tagCor){
		double Nowangle = (double)(tagCor.getY() - nowCor.getY()) / (double)(tagCor.getX() - nowCor.getX());
		//1st quadrant
		if (tagCor.getX() >= nowCor.getX() && tagCor.getY() >= nowCor.getY()) {
			nowCor.setLocation(nowCor.getX()+5, nowCor.getY()+Nowangle*5);
		}
		
		//4th quadrant 
		if (tagCor.getX() > nowCor.getX() && tagCor.getY() < nowCor.getY()) {
			nowCor.setLocation(nowCor.getX()+5, nowCor.getY()-Nowangle*5);
		}
		//2nd quadrant
		if (tagCor.getX() > nowCor.getX() && tagCor.getY() > nowCor.getY()) {
			nowCor.setLocation(nowCor.getX()-5, nowCor.getY()+Nowangle*5);

		}
		//3rd quadrant
		if (tagCor.getX() > nowCor.getX() && tagCor.getY() > nowCor.getY()) {
			nowCor.setLocation(nowCor.getX()-5, nowCor.getY()-Nowangle*5);

		}	
		
	}
	
	
	
	//false: moving ture: taget    bounds are on the first and third quadrants
	public void updateCor(Point nowCor, Point tagCor){
		if (!isTag(nowCor, tagCor)) {
			// get the angle of these two point.
			double Nowangle = (double)(tagCor.getY() - nowCor.getY()) / (double)(tagCor.getX() - nowCor.getX());
			// judge the direction tag coordinate.
			//1st quadrant
			if (tagCor.getX() >= nowCor.getX() && tagCor.getY() >= nowCor.getY()) {
				if(Nowangle>=angle){
					up(nowCor);
				}
				else{
					right(nowCor);
				}
			}
			
			//4th quadrant 
			if (tagCor.getX() > nowCor.getX() && tagCor.getY() < nowCor.getY()) {
				if(Nowangle>=angle){
					right(nowCor);
				}
				else{
					down(nowCor);
				}
			}
			//2nd quadrant
			if (tagCor.getX() > nowCor.getX() && tagCor.getY() > nowCor.getY()) {
				if(Nowangle>=angle){
					up(nowCor);
				}
				else{
					left(nowCor);
				}
			}
			//3rd quadrant
			if (tagCor.getX() > nowCor.getX() && tagCor.getY() > nowCor.getY()) {
				if(Nowangle>=angle){
					left(nowCor);
				}
				else{
					down(nowCor);
				}
			}
		}
	}
}
