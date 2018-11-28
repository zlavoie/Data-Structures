import java.util.*;

public class Move{
  //a move object holds the number of stones to be taken from the pile
  //this number can be 1,2,3
  private int move; //remove 1 2 3
  
  public Move(){ //dummy constructor
    move=0;
  }
  public Move(int n){//sets number of stones to remove
    move=n;
  }
  
  public void setMove(Move n) //paraneter is of type Move(class)
  {
    this.move=n.move;
  }
  
  public int getMove(){
    return move;
  }
  
  public String toString(){
    return ""+move;
  }
}