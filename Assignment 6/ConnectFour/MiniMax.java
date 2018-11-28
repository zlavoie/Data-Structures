//Zoe Lavoie
import java.util.*;

public class MiniMax {
  private int maxDepth;
  private int playerLetter;

  public void setMaxDepth(int maxDepth) { //Set Depth you want the max to go to in the game tree: limits look ahead so game doesnt run slow
   this.maxDepth = maxDepth;}
  
    public int getMaxDepth() { 
   return maxDepth;}

  public int WhichPlayer() {
   return playerLetter;}

  public MiniMax() {
   maxDepth = 8;
   playerLetter = Board.P;}
  
  public MiniMax(int maxDepth, int playerLetter) {
   this.maxDepth = maxDepth;
   this.playerLetter = playerLetter;
  }

  public Move minimax(Board board) { //Decide Min/Max depending on player
    //If X then we want to Maximize value
         if (playerLetter == Board.P) {
             return max(new Board(board), 0);
         }
         //If O then Minimize the value
         else {
           return min(new Board(board), 0);}}

  public Move max(Board board, int depth) { //MOVE FOR MAXIMUM
         Random r = new Random();
   if((board.checkEnd()) || (depth == maxDepth)){
    Move lastMove = new Move(board.getLastM().getRow(), board.getLastM().getCol(), board.evaluate());
    return lastMove;}
     
   ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.P));
   Move maxMove = new Move(Integer.MIN_VALUE);
   for (Board child : children) {
    Move move = min(child, depth + 1);
    if(move.getValue() >= maxMove.getValue()) {
                 if ((move.getValue() == maxMove.getValue())) {
                     if (r.nextInt(2) == 0) {
                         maxMove.setRow(child.getLastM().getRow());
                         maxMove.setCol(child.getLastM().getCol());
                         maxMove.setValue(move.getValue());}}
                 else {
                     maxMove.setRow(child.getLastM().getRow());
                     maxMove.setCol(child.getLastM().getCol());
                     maxMove.setValue(move.getValue());}}}
   return maxMove;}

  public Move min(Board board, int depth) { //MOVE FOR MINIMUM
         Random r = new Random();

   if((board.checkEnd()) || (depth == maxDepth)) {
    Move lastMove = new Move(board.getLastM().getRow(), board.getLastM().getCol(), board.evaluate());
    return lastMove;}
   ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.P));
   Move minMove = new Move(Integer.MAX_VALUE);
   for (Board child : children) {
    Move move = max(child, depth + 1);
    if(move.getValue() <= minMove.getValue()) {
                 if ((move.getValue() == minMove.getValue())) {
                     if (r.nextInt(2) == 0) {
                         minMove.setRow(child.getLastM().getRow());
                         minMove.setCol(child.getLastM().getCol());
                         minMove.setValue(move.getValue());}}
                 else {
                         minMove.setRow(child.getLastM().getRow());
                         minMove.setCol(child.getLastM().getCol());
                         minMove.setValue(move.getValue());}}}
         return minMove;}}