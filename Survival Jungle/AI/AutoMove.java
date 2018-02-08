
package AI;
import java.util.*;
import java.io.*;
/**
 *
 * @author sojournerchua
 */
public class AutoMove {
//    private int xDir, yDir;
    private int x,y;
    public int xSpeed = 5;  //d speed
    public int ySpeed = 5;
    private int time = 0;
    private Random rand=new Random();       
    public int Direction(){
        Random r = new Random();
        int[] ranDirection = new int[3];
        ranDirection[0] = 0;
        ranDirection[1] = 1;
        ranDirection[2] = -1;
        int randChoice = r.nextInt(3);
        return ranDirection[randChoice];
    }
//    private int dir;
//    public void setXDirection(int dir){
//        xDir = dir;
//    }
//    
//    public void setYDirection(int dir){
//        yDir = dir;
//    }
    public void addNPC() {
    	x=rand.nextInt(800);
    	y=rand.nextInt(600);
    	dir=(int) (Math.random()*4); 
    }
    public void setDirection() {	//�ƶ�����
    	
    }
    public void ifEatFood() {	//��û�г�ʳ��
    	
    }
    public void isHit() {		//��û�����Ľ�ɫ��/����
    	
    }
    public void Move(){
       time++;
       
       if (time % rand.nextInt() == 0){
           //change direction
           if (dir == 0){
               //direction == stationary
           }
           
           if (dir == 1){
               //diredtion == up
           }
           
           if (dir == -1) {
        	   		//direction == opposite
           }
           
       }
       
       x+=xDir;
       y+=yDir;
    }
   
    
    public void run(){
        Move();
        
    }
}