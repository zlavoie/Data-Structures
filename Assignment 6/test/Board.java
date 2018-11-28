//Zoe Lavoie
import java.util.*;
import javax.swing.*;
public class Board {

 public static final int P = 1; //Player
 public static final int C = -1; // Computer
 public static final int EMPTY = 0;

 private Move lastMove;
 private int LastPlayerChipPlayed;
 private int winner;
 private int [][] gameBoard;
 private boolean FullBoard = false;
 private boolean End;
 
 public Board() {
  lastMove = new Move();
  LastPlayerChipPlayed = C;
  winner = 0;
  gameBoard = new int[6][7];
  for(int a=0; a<6; a++) {
   for(int b=0; b<7; b++) {
     gameBoard[a][b] = EMPTY;}}}
 
 public Board(Board board) {
  lastMove = board.lastMove;
  LastPlayerChipPlayed = board.LastPlayerChipPlayed;
  winner = board.winner;
  gameBoard = new int[6][7];
  for(int a=0; a<6; a++) {
   for(int b=0; b<7; b++) {
     gameBoard[a][b] = board.gameBoard[a][b];}}}
 
 public Move getLastMove() {
   return lastMove;}
 
 public void setLastMove(Move lastMove) {
  this.lastMove.setRow(lastMove.getRow());
  this.lastMove.setCol(lastMove.getCol());
  this.lastMove.setValue(lastMove.getValue());} 
 
 public int getLastSymbolPlayed() {
  return LastPlayerChipPlayed; }
 
 public void setLastSymbolPlayed(int lastLetterPlayed) {
   this.LastPlayerChipPlayed = lastLetterPlayed;}
 
 public int[][] getGameBoard() {
   return gameBoard;}
 
 public void setGameBoard(int[][] gameBoard) {
  for(int a=0; a<6; a++) {
   for(int b=0; b<7; b++) {
     this.gameBoard[a][b] = gameBoard[a][b];}}}
   
 public int Win() {
   return winner;}
 
 public void setWinner(int winner) {
  this.winner = winner; }
 
 public boolean End() {
   return End;}

 public void setGameOver(boolean End) {
   this.End = End;}
 
 public boolean Full() {
   return FullBoard;}
 
 public void setOverflowOccured(boolean FullBoard) {
   this.FullBoard = FullBoard;}

 // Makes a move based on column.
 public void makeMove(int col, int letter) {
  try {
   this.lastMove = new Move(getRowPosition(col), col);
   this.LastPlayerChipPlayed = letter;
   this.gameBoard[getRowPosition(col)][col] = letter;
  } catch (ArrayIndexOutOfBoundsException e) {
    JOptionPane.showMessageDialog(null, "That Column is Full!"); //Prints out message that Column is full and greys out the button
    setOverflowOccured(true);}}

 public boolean canMove(int row, int col) {
  if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
   return false;}
  return true;} 
 
 public boolean checkFullColumn(int col) {
   if (gameBoard[0][col] == EMPTY){
     return false;}
   return true;}
 
 public int getRowPosition(int col) {
  int RowSpot = -1;
  for (int row=0; row<6; row++) {
   if (gameBoard[row][col] == EMPTY) {
     RowSpot = row;}}
  return RowSpot;}
 
 public ArrayList<Board> childrenOnTree(int letter) {
  ArrayList<Board> children = new ArrayList<Board>();
  for(int col=0; col<7; col++) {
   if(!checkFullColumn(col)) {
    Board child = new Board(this);
    child.makeMove(col, letter);
    children.add(child);}}
  return children;}
 
 
 public int evaluate() {
  int PlayerWinEvaluation = 0;
  int ComputerWinEvaluation = 0;

        if (CheckWin()) {
   if(Win() == P) {
    PlayerWinEvaluation = PlayerWinEvaluation + 100000;
   } else if (Win() == C) {
     ComputerWinEvaluation = ComputerWinEvaluation + 100000;}}
        PlayerWinEvaluation  = PlayerWinEvaluation + ThreeInRow(P)*10 + TwoInRow(P);
        ComputerWinEvaluation  = ComputerWinEvaluation + ThreeInRow(C)*10 + TwoInRow(C);
  return PlayerWinEvaluation - ComputerWinEvaluation;}
 
 public boolean CheckWin() {
  
  // Check for 4 horizon
  for (int a=5; a>=0; a--) {
   for (int b=0; b<4; b++) {
    if (gameBoard[a][b] == gameBoard[a][b+1]&& gameBoard[a][b] == gameBoard[a][b+2]&& gameBoard[a][b] == gameBoard[a][b+3]&& gameBoard[a][b] != EMPTY) {
     setWinner(gameBoard[a][b]);
     return true;}}}
  
  // Check for 4 vertical
  for (int a=5; a>=3; a--) {
   for (int b=0; b<7; b++) {
    if (gameBoard[a][b] == gameBoard[a-1][b]&& gameBoard[a][b] == gameBoard[a-2][b]&& gameBoard[a][b] == gameBoard[a-3][b]&& gameBoard[a][b] != EMPTY) {
     setWinner(gameBoard[a][b]);
     return true;}}}
  
  // Check for 4 down diag
  for (int a=0; a<3; a++) {
   for (int b=0; b<4; b++) {
    if (gameBoard[a][b] == gameBoard[a+1][b+1]&& gameBoard[a][b] == gameBoard[a+2][b+2]&& gameBoard[a][b] == gameBoard[a+3][b+3] && gameBoard[a][b] != EMPTY) {
     setWinner(gameBoard[a][b]);
     return true;}}}
  
  // Check for 4 up diag
  for (int a=0; a<6; a++) {
   for (int b=0; b<7; b++) {
    if (canMove(a-3,b+3)) {
     if (gameBoard[a][b] == gameBoard[a-1][b+1]&& gameBoard[a][b] == gameBoard[a-2][b+2]&& gameBoard[a][b] == gameBoard[a-3][b+3] && gameBoard[a][b] != EMPTY) {
      setWinner(gameBoard[a][b]);
      return true;}}}}
  setWinner(0); // set a tie
  return false;}
 
 
 public int ThreeInRow(int playerSymbol) {
  
  int evaluation = 0;
  
  // Check for 3 horizon
  for (int a = 5; a >= 0; a--) {
   for (int b = 0; b< 7; b++) {
    if (canMove(a, b + 2)) {
     if (gameBoard[a][b] == gameBoard[a][b + 1]
       && gameBoard[a][b] == gameBoard[a][b + 2]
       && gameBoard[a][b] == playerSymbol) {
       evaluation++;}}}}

  // Check for 3 vertical
  for (int a= 5; a>= 0; a--) {
   for (int b = 0;b < 7; b++) {
    if (canMove(a- 2, b)) {
     if (gameBoard[a][b] == gameBoard[a - 1][b]
       && gameBoard[a][b] == gameBoard[a- 2][b]
       && gameBoard[a][b] == playerSymbol) {
       evaluation++;}}}}

  // Check for 3 down diag
  for (int a = 0; a< 6; a++) {
   for (int b = 0; b < 7; b++) {
    if (canMove(a+ 2, b+ 2)) {
     if (gameBoard[a][b] == gameBoard[a + 1][b + 1]
       && gameBoard[a][b] == gameBoard[a + 2][b + 2]
       && gameBoard[a][b] == playerSymbol) {
       evaluation++; }}}}

  // Check for 3 up diag
  for (int a= 0; a < 6; a++) {
   for (int b = 0; b < 7; b++) {
    if (canMove(a - 2, b + 2)) {
     if (gameBoard[a][b] == gameBoard[a - 1][b + 1]
           && gameBoard[a][b] == gameBoard[a - 2][b + 2]
       && gameBoard[a][b] == playerSymbol) {
       evaluation++;}}}}

  return evaluation;}
 
 public int TwoInRow(int player) {
  
  int evaluation = 0;
  
  // Check for 2 horizon
  for (int a= 5; a >= 0; a--) {
   for (int b = 0; b < 7; b++) {
    if (canMove(a, b + 1)) {
     if (gameBoard[a][b] == gameBoard[a][b + 1]
       && gameBoard[a][b] == player) {
       evaluation++;}}}}

  // Check for 3 vertical
  for (int a= 5; a >= 0; a--) {
   for (int b = 0; b < 7; b++) {
    if (canMove(a- 1, b)) {
     if (gameBoard[a][b] == gameBoard[a - 1][b]
       && gameBoard[a][b] == player) {
       evaluation++;}}}}

  // Check for 3 down diagonal.
  for (int a= 0; a < 6; a++) {
   for (int b = 0; b< 7; b++) {
    if (canMove(a + 1, b + 1)) {
     if (gameBoard[a][b] == gameBoard[a + 1][b + 1]
       && gameBoard[a][b] == player) {
       evaluation++;}}}}

  // Check for 3up diagonal.
  for (int a= 0; a < 6; a++) {
   for (int b = 0; b < 7; b++) {
    if (canMove(a - 1, b + 1)) {
     if (gameBoard[a][b] == gameBoard[a - 1][b + 1]
       && gameBoard[a][b] == player) {
       evaluation++;}}}}
  return evaluation;}
 
    public boolean checkGameOver() { //Is there a winner?
     if (CheckWin()) {
       return true;}

     for(int row=0; row<6; row++) { //Check for Draw that all Columns are full
   for(int col=0; col<7; col++) {
    if(gameBoard[row][col] == EMPTY) {
      return false;}}}
     
     return true; //No winner, draw
    }} //End Class