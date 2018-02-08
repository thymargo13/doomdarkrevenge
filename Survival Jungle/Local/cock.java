package Local;

import java.util.Scanner;

public class cock{
  
  public static void main(String args[]){
  
    Scanner scan = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    
    double[] terms = new double[11];
    
    boolean doneTerms = false;
      
    int index = 0;
    
    while(!doneTerms){
      System.out.println("Give me n term# "+(index+1));
      terms[index] = scan.nextDouble();
      index++;
      System.out.println("Continue?");
      
      String next = scan2.nextLine();
      
      if(next.equals("done")){
        doneTerms = true;
      }else{
        continue;
      }
    }
    

    for(int i = index; i < terms.length;i++){
      terms[i] = function(i);
      System.out.println("A"+(i)+" = "+function(i));
    }
    
  }
  
  public static double function(double x){
    double term = (x - 5);
    //double term = (-2*Math.pow(x,2) - 7 * x - 6);
    return (term);
  }
}