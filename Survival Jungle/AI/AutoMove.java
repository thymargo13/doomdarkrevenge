
package jungle;
import java.util.*;
import java.io.*;
/**
 *
 * @author sojournerchua
 */
public class AutoMove {
    int xDir;
    int yDir;
    
    private int time = 0;
           
    public int Direction(){
        Random r = new random();
        int[] ranDirection = new int[3];
        ranDirection[0] = 0;
        ranDirection[1] = 1;
        ranDirection[2] = -1;
        int randChoice = r.nextInt(3);
        return ranDirection(randChoice);
    }
    
    public void setXDirection(int dir){
        xDir = dir;
    }
    
    public void setYDirection(int dir){
        yDir = dir;
    }
            
    public void Move(){
       time++;
       
       if (time % random.nextInt() == 0){
           //change direction
           if (dir == 0){
               //direction == stationary
           }
           
           if (dir == 1){
               //diredtion == up
           }
           
           
       }
       
       AI.x+=xDir;
       AI.y+=yDir;
    }
    
    public void run(){
        move();
        
    }
}
