  import java.util.*;
  //A Board object holds the current configuration
  //the number of stones remaining in the pile and the player
  //whose turn it is 1 for Max(computer) or 0 (for Min)
  public class Board{
  public int numStones;
  public int turn; //1 for computer, 0 for player
  
  Board(){
    numStones=10;
    turn=1;
  }
  Board(int n, int player){
    numStones=n;
    turn=player;
  }
  Board(Board b){
    this.numStones=b.numStones;
    this.turn=b.turn;}
  
  boolean done(){
    return numStones==0;
  }
  
  void play(Move tryIt){
    numStones=numStones-tryIt.getMove(); //remove Stones
  }
  
  int evaluate(){
    if(turn==1){
      return 1;}//win for max
    return -1; //win for player
  }
  
  void setTurn(){ //switch turn
    turn =(turn+1)%2;}
  
  public void legalMoves(Stack<Move> moves){//pushes all legal move on a stack
    for(int i=1;i<=2;i++){
      if(i<=numStones){
        Move newMove= new Move(i);
        moves.push(newMove);
      }}}
    
    int worstCase(){
      if(turn==1){
        return -1;}//worst value for max
      return 1; //worst value for min
    }
    
    public void setStones(int n){
      numStones=n;
    }
    
    public int getTurn(){
      return turn;}
    
    boolean better(int value, int oldValue) //Is value better than oldValue?
    {
      if(turn==1){
        return(value>=oldValue);}
      return(value<=oldValue);
    }}