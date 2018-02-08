package AI;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	int x,y,dir,d;  
    int speed;  
    Color col;  
    static final int left_up=0,right_up=1,left_down=2,right_down=3;  
    public Enemy(int x,int y,int dir,int d,int speed, Color col){  
        super();  
        this.x=x;  
        this.y=y;  
        this.dir=dir;  
        this.d=d;  
        this.speed=speed;  
        this.col=col;  
    }  
    void Draw(Graphics g){           //画出这个小球  
        g.setColor(col);  
        g.fillOval(x, y, d, d);  
    }  
    void move(){                      //小球运动  
        switch (this.dir) {  
        case left_up:  
            x-=speed;
            y-=speed;  
            if(x<=0) {
            	dir=right_up;  
            }
            else if(y<=0) {
            	dir=left_down;  
            }
            break;  
        case right_up:  
            x+=speed;
            y-=speed;  
            if(x>=800-d) {
            	dir=left_up;  
            }
            else if(y<=0) {
            	dir=right_down;  
            }
            break;  
        case left_down:  
            x-=speed;
            y+=speed;  
            if(x<=0) {
            	dir=right_down;  
            }
            else if(y>=600-d) {
            	dir=left_up;  
            }
            break;  
        case right_down:  
            x+=speed;
            y+=speed;  
            if(x>=800-d) {
            	dir=left_down;  
            }
            else if(y>=600-d) {
            	dir=right_up;  
            }
            break;  
          
        }  
    }  
}
