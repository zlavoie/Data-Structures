//Zoe Lavoie
//Assingment 2

public class Position{
  public int x;
  public int y;
  
  public Position(){
    x = 0;
    y = 0;
  }
  
   public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
   
   public boolean equals(Position b){
     if((b.x == this.x) && (b.y == this.y)){
      return true; 
     }
     else{
      return false; 
     }
   }
   
   public void print(){
    System.out.println("(" + x  + ", " + y + ")"); 
   }
   
}