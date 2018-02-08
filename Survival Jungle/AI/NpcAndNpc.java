package AI;

public class NpcAndNpc {
	static final int left_up=0,right_up=1,left_down=2,right_down=3;  
    public void collision(Enemy a,Enemy b){               //两球相碰反弹  等级相同时 
        int x1=a.x+a.d/2;  
        int y1=a.y+a.d/2;  
        int x2=b.x+b.d/2;  
        int y2=b.y+b.d/2;  
          
        double dis= Math.sqrt( (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));  
        if(dis<=a.d/2+b.d/2){  
            switch (a.dir) {  
            case left_up:  
                a.dir=right_down;  
                break;  
            case left_down:  
                a.dir=right_up;  
                break;  	
            case right_up:  
                a.dir=left_down;  
                break;  
            case right_down:  
                a.dir=left_up;  
                break;  
            }  
              
            switch (b.dir) {  
            case left_up:  
                b.dir=right_down;  
                break;  
            case left_down:  
                b.dir=right_up;  
                break;  
            case right_up:  
                b.dir=left_down;  
                break;  
            case right_down:  
                b.dir=left_up;  
                break;  
            }  
        }  
    }  
    public int destroy(Enemy a,Enemy b){         //判断两球是否相碰  
        int ret=0;  
        int x1=a.x+a.d/2;  
        int y1=a.y+a.d/2;  
        int x2=b.x+b.d/2;  
        int y2=b.y+b.d/2;  
        double dis= Math.sqrt( (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));  
        if(dis<=a.d/2+b.d/2) {
        	ret=1;  
        }
        return ret;  
    }  
}
