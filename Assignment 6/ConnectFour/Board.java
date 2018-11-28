//Zoe Lavoie
import java.util.*;
import javax.swing.*;
public class Board {

 public static final int P = 1; //Player
 public static final int C = -1; // Computer
 public static final int EMPTY = 0;

 private Move lastMove;
 private int lastSymbolPlayed;
 private int winner;
 private int [][] gameBoard;
 private boolean FullBoard = false;
 private boolean End;
 
 public Board() {
  lastMove = new Move();
  lastSymbolPlayed = C;
  winner = 0;
  gameBoard = new int[6][7];
  for(int i=0; i<6; i++) {
   for(int j=0; j<7; j++) {
     gameBoard[i][j] = EMPTY;}}}
 
 public Board(Board board) {
  lastMove = board.lastMove;
  lastSymbolPlayed = board.lastSymbolPlayed;
  winner = board.winner;
  gameBoard = new int[6][7];
  for(int i=0; i<6; i++) {
   for(int j=0; j<7; j++) {
     gameBoard[i][j] = board.gameBoard[i][j];}}}
 
 public Move getLastM() {
   return lastMove;}
 
 public void setLastM(Move lastMove) {
  this.lastMove.setRow(lastMove.getRow());
  this.lastMove.setCol(lastMove.getCol());
  this.lastMove.setValue(lastMove.getValue());} 
 
 public int getLastSymbolPlayed() {
  return lastSymbolPlayed; }
 
 public void setLastSymbolPlayed(int lastLetterPlayed) {
   this.lastSymbolPlayed = lastLetterPlayed;}
 
 public int[][] getGameBoard() {
   return gameBoard;}
 
 public void setGameBoard(int[][] gameBoard) {
  for(int i=0; i<6; i++) {
   for(int j=0; j<7; j++) {
     this.gameBoard[i][j] = gameBoard[i][j];}}}
   
 public int win() {
   return winner;}
 
 public void setWin(int winner) {
  this.winner = winner; }
 
 public boolean End() {
   return End;}

 public void Done(boolean End) {
   this.End = End;}
 
 public boolean Full() {
   return FullBoard;}
 
 public void setOverflowOccured(boolean FullBoard) {
   this.FullBoard = FullBoard;}

 // Makes a move based on the given column.
 public void Go(int col, int letter) {
  try {
   this.lastMove = new Move(getRowPosition(col), col);
   this.lastSymbolPlayed = letter;
   this.gameBoard[getRowPosition(col)][col] = letter;
  } catch (ArrayIndexOutOfBoundsException e) {
   //System.err.println("Column " + (col+1) + " is full!");
    JOptionPane.showMessageDialog(null, "That Column is Full!"); //Prints out message that Column is full and greys out the button
    setOverflowOccured(true);}}

 // This function is used when we want to search the whole board,
 // without getting out of borders.
 public boolean canMove(int row, int col) {
  if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
   return false;}
  return true;}
 
 
 public boolean checkFullColumn(int col) {
   if (gameBoard[0][col] == EMPTY){
     return false;}
   return true;}
 
 // It returns the position of the last empty row in a column.
 public int getRowPosition(int col) {
  int rowPosition = -1;
  for (int row=0; row<6; row++) {
   if (gameBoard[row][col] == EMPTY) {
     rowPosition = row;}}
  return rowPosition;}
 
 public ArrayList<Board> getChildren(int letter) {
  ArrayList<Board> children = new ArrayList<Board>();
  for(int col=0; col<7; col++) {
   if(!checkFullColumn(col)) {
    Board child = new Board(this);
    child.Go(col, letter);
    children.add(child);}}
  return children;}
 
 
 public int evaluate() {
  // +100000 'X' wins, -100000 'O' wins,
  // +10 for each three player chips in a row, -10 for three computer chips in a row
  // +1 for two player chips in a row, -1 for two computer chips in a row
  int PlayerWinEvaluation = 0;
  int ComputerWinEvaluation = 0;

        if (CheckWin()) {
   if(win() == P) {
    PlayerWinEvaluation = PlayerWinEvaluation + 100000;
   } else if (win() == C) {
     ComputerWinEvaluation = ComputerWinEvaluation + 100000;}}
        PlayerWinEvaluation  = PlayerWinEvaluation + check3InARow(P)*10 + check2InARow(P);
        ComputerWinEvaluation  = ComputerWinEvaluation + check3InARow(C)*10 + check2InARow(C);
  return PlayerWinEvaluation - ComputerWinEvaluation;}
 
 public boolean CheckWin() {
  
  // Check for 4 consecutive checkers in a row, horizontally.
  for (int i=5; i>=0; i--) {
   for (int j=0; j<4; j++) {
    if (gameBoard[i][j] == gameBoard[i][j+1]&& gameBoard[i][j] == gameBoard[i][j+2]&& gameBoard[i][j] == gameBoard[i][j+3]&& gameBoard[i][j] != EMPTY) {
     setWin(gameBoard[i][j]);
     return true;}}}
  
  // Check for 4 consecutive checkers in a row, vertically.
  for (int i=5; i>=3; i--) {
   for (int j=0; j<7; j++) {
    if (gameBoard[i][j] == gameBoard[i-1][j]&& gameBoard[i][j] == gameBoard[i-2][j]&& gameBoard[i][j] == gameBoard[i-3][j]&& gameBoard[i][j] != EMPTY) {
     setWin(gameBoard[i][j]);
     return true;}}}
  
  // Check for 4 consecutive checkers in a row, in down diagonals.
  for (int i=0; i<3; i++) {
   for (int j=0; j<4; j++) {
    if (gameBoard[i][j] == gameBoard[i+1][j+1]&& gameBoard[i][j] == gameBoard[i+2][j+2]&& gameBoard[i][j] == gameBoard[i+3][j+3] && gameBoard[i][j] != EMPTY) {
     setWin(gameBoard[i][j]);
     return true;}}}
  
  // Check for 4 consecutive checkers in a row, in up diagonals.
  for (int i=0; i<6; i++) {
   for (int j=0; j<7; j++) {
    if (canMove(i-3,j+3)) {
     if (gameBoard[i][j] == gameBoard[i-1][j+1]&& gameBoard[i][j] == gameBoard[i-2][j+2]&& gameBoard[i][j] == gameBoard[i-3][j+3] && gameBoard[i][j] != EMPTY) {
      setWin(gameBoard[i][j]);
      return true;}}}}
  setWin(0); // set a tie
  return false;}
 
 
 public int check3InARow(int playerSymbol) {
  
  int evaluation = 0;
  
  // Check for 3 consecutive checkers in a row, horizontally.
  for (int i = 5; i >= 0; i--) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i, j + 2)) {
     if (gameBoard[i][j] == gameBoard[i][j + 1]
       && gameBoard[i][j] == gameBoard[i][j + 2]
       && gameBoard[i][j] == playerSymbol) {
       evaluation++;}}}}

  // Check for 3 consecutive checkers in a row, vertically.
  for (int i = 5; i >= 0; i--) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i - 2, j)) {
     if (gameBoard[i][j] == gameBoard[i - 1][j]
       && gameBoard[i][j] == gameBoard[i - 2][j]
       && gameBoard[i][j] == playerSymbol) {
       evaluation++;}}}}

  // Check for 3 consecutive checkers in a row, in descending diagonal.
  for (int i = 0; i < 6; i++) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i + 2, j + 2)) {
     if (gameBoard[i][j] == gameBoard[i + 1][j + 1]
       && gameBoard[i][j] == gameBoard[i + 2][j + 2]
       && gameBoard[i][j] == playerSymbol) {
       evaluation++; }}}}

  // Check for 3 consecutive checkers in a row, in ascending diagonal.
  for (int i = 0; i < 6; i++) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i - 2, j + 2)) {
     if (gameBoard[i][j] == gameBoard[i - 1][j + 1]
       && gameBoard[i][j] == gameBoard[i - 2][j + 2]
       && gameBoard[i][j] == playerSymbol) {
       evaluation++;}}}}

  return evaluation;}

 public int check2InARow(int player) { //Small method to make the computer play smarter outside of the MiniMax method by looking at chances of 2 in a row and 3 in a row
  int evaluation = 0;
  
  // Check for 2 on horizon
  for (int i = 5; i >= 0; i--) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i, j + 1)) {
     if (gameBoard[i][j] == gameBoard[i][j + 1]
       && gameBoard[i][j] == player) {
       evaluation++;}}}}

  // Check for 3 on vertical
  for (int i = 5; i >= 0; i--) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i - 1, j)) {
     if (gameBoard[i][j] == gameBoard[i - 1][j]
       && gameBoard[i][j] == player) {
       evaluation++;}}}}

  // Check for 3 in down diagonal.
  for (int i = 0; i < 6; i++) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i + 1, j + 1)) {
     if (gameBoard[i][j] == gameBoard[i + 1][j + 1]
       && gameBoard[i][j] == player) {
       evaluation++;}}}}

  // Check for 3 in up diagonal.
  for (int i = 0; i < 6; i++) {
   for (int j = 0; j < 7; j++) {
    if (canMove(i - 1, j + 1)) {
     if (gameBoard[i][j] == gameBoard[i - 1][j + 1]
       && gameBoard[i][j] == player) {
       evaluation++;}}}}
  return evaluation;}
 
    public boolean checkEnd() { //Is there a winner?
     if (CheckWin()) {
       return true;}

     for(int row=0; row<6; row++) { //Check for Draw that all Columns are full
   for(int col=0; col<7; col++) {
    if(gameBoard[row][col] == EMPTY) {
      return false;}}}
     
     return true; //No winner, draw
    }
} //End Class