import java.util.*;

public class Nim{
  int stones; //number of stones
  boolean done; //is the game finished?
  Board game;
  Scanner input;
  
  public Nim(){//default constructor
    input=new Scanner(System.in);
    do //until the number of stones entered is 6 or more
    {
      System.out.print("How many stones (at least 6): ");
      stones = input.nextInt();
    }while (stones<6);
    
    String first; //computer or person
    do//who goes first:computer or person
    {
      System.out.print("who goes first -- c for computer, p for person: ");
      first = (input.next()).toLowerCase();
    }while (!(first.equals("c")||first.equals("p")));
    done=false;
    
    if(first.equals("p")){ //if then human goes first, make the first move
      int num;//num of stones to be removed
      do{
        System.out.print("\nHow many stones would you like to take?: ");
        num = input.nextInt();
      }while (num>=3||num<=0);
      
      stones=stones-num ; //adjust the pile
      System.out.println("Stones remaining: "+stones);
      if(stones==0) //game over(will not happen with 6+ stones)
      {
        done=true;
        System.out.println("Computer wins, player loses");}}
    game=new Board(stones, 0); //Begin the game with new number of stones
    playGame();
  }
  
  public void playGame(){
    while(!done){
      Move recommendedMove= new Move(); //will eventually be the recommended move
      int depth = stones;
      int value = MiniMax.lookAhead(game, depth, recommendedMove); //gets the move
      System.out.println("Computer takes "+recommendedMove.getMove());
      stones=stones-recommendedMove.getMove();
      System.out.println("Stones remaining: "+stones);
      game.setStones(stones);
      if(stones==0)//the computer took the last stone --loses
      {
        done=true;
        System.out.println("\nPlayer wins, computer loses");}
      else //player goes
      {
        int num;
        do{
          System.out.print("\nHow many stones would you like to take: ");
          num=input.nextInt();}while(num>stones||num<=9||num>3);
          stones=stones-num;
          System.out.println("Stones remaining: "+stones);
          game.setStones(stones);
          if(stones==0)//player took the last stone
          {
            done=true;
            System.out.println("\nComputer wins, player loses");
          }}}}
  
  public static void main(String[] args){
    Nim nim = new Nim();
  }}
                                        