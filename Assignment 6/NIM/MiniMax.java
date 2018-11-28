import java.util.*;

public class MiniMax{
  public static int lookAhead(Board game, int depth, Move recommended)
    //a utility method, returns the value of a leaf and also the recommended move for the computer
  {
  game.setTurn(); //flips between players 1 and 0
  if (game.done() || depth==0){
    return game.evaluate();}
  else{
    Stack <Move> moves = new Stack <Move>();
    game.legalMoves(moves); // puts lefal moves on the stack, moves
    int value; 
    int bestValue = game.worstCase(); //best value so far
    while(!moves.empty()){
      Move move = new Move();
      Move tryIt=moves.peek();// get the next legal move
      Board newGame = new Board(game);//make a copy of the current board
      newGame.play(tryIt); //play the move on the new board
      //recursive call to lookahead, accepts the new game board and returns the
      //value of that board configuration
      value=lookAhead(newGame,depth-1, move);
      if(game.better(value, bestValue)) //is the reutned value better?
      {
        bestValue=value;
        recommended.setMove(tryIt); //setMove take a move object
      }
      moves.pop();
    }
    return bestValue;
  }}}