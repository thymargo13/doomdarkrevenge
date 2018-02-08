package Local;
import java.awt.*; 
import java.awt.image.BufferedImage; 
import javax.swing.JFrame; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard{
  
  private int x;
  private int y;
  
  public static ArrayList<Cell> cellsCopy = Cell.cells;
  
  private int z = 10;
  
  private int currentY = 0;
  
  Color color = new Color(50, 50, 50, 128);
  
  int spots[] = new int[z];
  
  public Leaderboard(){
    for(int i = 0; i < z; i++){
      spots[i] = currentY;
      currentY+=30;
    }
  }
  
  public void Update(){
    cellsCopy = Cell.cells;
    Collections.sort(cellsCopy, new leaderComparator());
  }
  
  public void Draw(Graphics bbg){
    for(int i = 0; i < z; i++){
      bbg.setColor(color);
      bbg.drawRect(x,y+spots[i],125,30);
      bbg.fillRect(x,y+spots[i],125,30);
      bbg.setColor(Color.WHITE);
      if(Cell.cells.size() >= z){
        bbg.drawString("#"+(i+1)+": "+cellsCopy.get(i).name + " : "+(int)cellsCopy.get(i).mass,x,y+spots[i]+25);
      }
    }
  }
  
  private class leaderComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell c1, Cell c2) {
      if(c1.mass == c2.mass){
        return 0;
      }else if(c1.mass > c2.mass){
        return -1;
      }else{
        return 1;
      }
    }
  }
  
}