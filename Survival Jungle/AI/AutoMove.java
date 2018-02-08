
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
    private int dir;	//方向
    public int speed = 5;  //d speed
//    public int ySpeed = 5;
    
    List<Enemy> npc= new ArrayList<Enemy>();
    
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

    public void addNPC() {
    	x=rand.nextInt(800);
    	y=rand.nextInt(600);
    	dir=(int) (Math.random()*4);  
//    	Color col=new Color(r,g,b);  
    	int d=(int) (Math.random()*30);
    	int level=(int)(Math.random()*7+1);	//一共8个等级
    	npc.add(new Enemy(x,y,dir,d,speed,level));
//    	dir=(int) (Math.random()*4); 
    }

    public void setDirection() {	
    	
    }
    public void ifEatFood() {	
    	
    }
   
//  private int dir;
//  public void setXDirection(int dir){
//      xDir = dir;
//  }
//  
//  public void setYDirection(int dir){
//      yDir = dir;
//  }
    public void Move(){
    	new Thread(){  
            public void run(){  
                while(true){  
                    for(int i=1;i<npc.size();i++){               //所有npc运动一次  
                        Enemy e=npc.get(i);  
                        e.move();  
                    }  
                    for(int i=0;i<npc.size()-1;i++){           //两个for循环判断是否碰撞  
                    	 Enemy e1=npc.get(i); 
                        for(int j=i+1;j<npc.size();j++){  
                         	Enemy e2=npc.get(i); 
                        	NpcAndNpc nan=new NpcAndNpc(); 
                            if(nan.destroy(e1, e2)==1){        //碰到了  
                            	if(e1.level>=e2.level) {
                            		npc.remove(j);
                            		break;
                            	}else if(e1.level<e2.level) {
                            		npc.remove(i);
                            		break;
                            	}  
                            }  
                              
                        }  
                    }  
                    paint();                        //调用画npc的函数 
                    try {  
                        Thread.sleep(30);            //休眠  
                    } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                }  
            }  
        }.start();  
//       time++;
//       
//       if (time % rand.nextInt() == 0){
//           //change direction
//           if (dir == 0){
//               //direction == stationary
//           }
//           
//           if (dir == 1){
//               //diredtion == up
//           }
//           
//           if (dir == -1) {
//        	   	//direction == opposite
//           }
//           
//       }
//       x+=xDir;
//       y+=yDir;
    }
   
    
    public void run(){
        Move();
        
    }
}