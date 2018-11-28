//Zoe Lavoie
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
 static Board board = new Board();
 static JFrame OutsideFrame; //Jframe holding the panels
 static JFrame framesetEnd; //Pop up frame when the game ends
 static JPanel panelMain; //Main Panel
 static JPanel ChipNums; //Panel Holding Buttons to Drop Chips
 static JLayeredPane  PictureC4; // Layered part that uses a gif image as a board
 static boolean firstGame = true;//Is it the first game?

 static JButton Chip1 = new JButton("1"); //Create buttons to press to drop chip
 static JButton Chip2 = new JButton("2");
 static JButton Chip3 = new JButton("3");
 static JButton Chip4 = new JButton("4");
 static JButton Chip5 = new JButton("5");
 static JButton Chip6 = new JButton("6");
 static JButton Chip7 = new JButton("7");

 static int maxDepth = 4; //Depth to look ahead on MiniMax tree
 static String player1Color = "RED"; //Color of Player 1 chip
 static String player2Color = "YELLOW"; //Color of CPU chip
 static int turn; //Keeps track of who's turn it is
 static MiniMax SmartMove =new MiniMax(maxDepth, Board.P);
 
 public Main() {
 }

 public static JLayeredPane MakeMainBoard() { //Connect 4 picture made into a working board
   PictureC4 = new JLayeredPane();
   PictureC4.setPreferredSize(new Dimension(570, 525)); //Set size of new panel

  ImageIcon PicBoard = new ImageIcon("Board.gif"); //Take board image
  JLabel PicBoardLabel = new JLabel(PicBoard); //Slap it on a label
  PicBoardLabel.setBounds(20, 20, 532, 458); //set Bounds
   PictureC4.add(PicBoardLabel); //Add it to the panel
  return  PictureC4;}
 
 
 public static KeyListener gameKeyListener = new KeyListener() {  
  public void keyTyped(KeyEvent e) {
  }
  public void keyPressed(KeyEvent e) {
   String button = KeyEvent.getKeyText(e.getKeyCode());
   if (button.equals("1")) { //Depending on Button pressed, that is the Column the move is made in
    Go(0);
   } else if (button.equals("2")) {
    Go(1);
   } else if (button.equals("3")) {
    Go(2);
   } else if (button.equals("4")) {
    Go(3);
   } else if (button.equals("5")) {
    Go(4);
   } else if (button.equals("6")) {
    Go(5);
   } else if (button.equals("7")) {
    Go(6);}
  
    if (!board.Full()) { //Is the whole board full? If not, play the game
     game(); //Play Game (Player)
     ComputerMove();}} //Computer Moves 
  
  public void keyReleased(KeyEvent e) {}//necessary component in using KeyListener 
 };
  
 public static void NewGame() {
  board = new Board();
  SmartMove.setMaxDepth(5);
  OutsideFrame = new JFrame("Connect 4");
  Component compMainWindowContents = createContentComponents();
  OutsideFrame.getContentPane().add(compMainWindowContents, BorderLayout.CENTER);
  
  OutsideFrame.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    System.exit(0);}});
  
  OutsideFrame.addKeyListener(gameKeyListener);
  OutsideFrame.setSize(580,640);
  OutsideFrame.setVisible(true);{
   if (turn==1) {
    Move  ComputerMove = SmartMove.minimax(board);
    board.Go( ComputerMove.getCol(), Board.C);
    game();}}}
 
 public static void Go(int col) {
  board.setOverflowOccured(false);
  
  int previousRow = board.getLastM().getRow();
  int previousCol = board.getLastM().getCol();
  int previousLetter = board.getLastSymbolPlayed();
  
  if (previousLetter==Board.C) {
   board.Go(col, Board.P);
  } else {
   board.Go(col, Board.C);}
  
  if (board.Full()) {
   board.getLastM().setRow(previousRow);
   board.getLastM().setCol(previousCol);
   board.setLastSymbolPlayed(previousLetter);}}
 
 // It places a checker on the board.
 public static void placeChecker(String color, int row, int col) {
  int MoveX = 75 * col;
 int MoveY = 75 * row;
  if(color.equals("RED")){
    ImageIcon ChipIcon = new ImageIcon("RED.gif");
   JLabel ChipPiece = new JLabel(ChipIcon);
  ChipPiece.setBounds(27 + MoveX, 27 + MoveY, 68,68);
   PictureC4.add(ChipPiece, 0, 0);
  OutsideFrame.paint(OutsideFrame.getGraphics());}
     else{
       ImageIcon ChipIcon = new ImageIcon("YELLOW.gif");
        JLabel ChipPiece = new JLabel(ChipIcon);
  ChipPiece.setBounds(27 + MoveX, 27 + MoveY, 68,68);
   PictureC4.add(ChipPiece, 0, 0);
  OutsideFrame.paint(OutsideFrame.getGraphics());}}
 
 public static void game() {
  int row = board.getLastM().getRow();
  int col = board.getLastM().getCol();
  int currentPlayer = board.getLastSymbolPlayed();
 
  if (currentPlayer == Board.P) {
   //It places a player checker
    placeChecker(player1Color, row, col);}
   else if (currentPlayer == Board.C) {
   // It places computer checker
    placeChecker(player2Color, row, col);}
  
  if (board.checkEnd()) {
   gameOver();} //Checks if Game is Over
 } //End Game Method

 public static void  ComputerMove(){ //Makes Computer Move Using MiniMax Algorithm

  if (!board.End()) {
    Move  ComputerMove = SmartMove.minimax(board);
    board.Go(ComputerMove.getCol(), Board.C);
    game();}} 
 
 public static Component createContentComponents() {
  // Create a panel to set up the board buttons.
  ChipNums = new JPanel();
  ChipNums.setLayout(new GridLayout(1, 7, 6, 4));
 
  //Checks if column is full and will grey out the button if it is
  if(board.checkFullColumn(0)){
    Chip1.setEnabled(false);
  ChipNums.add(Chip1);
  }else{Chip1.setEnabled(true);ChipNums.add(Chip1);}//End Chip1
 
  if(board.checkFullColumn(1)){
    Chip2.setEnabled(false);
  ChipNums.add(Chip2);
  }else{Chip2.setEnabled(true);ChipNums.add(Chip2);}//End Chip2
  
  if(board.checkFullColumn(2)){
    Chip3.setEnabled(false);
  ChipNums.add(Chip3);
  }else{Chip3.setEnabled(true);ChipNums.add(Chip3);}//End Chip3
  
 if(board.checkFullColumn(3)){
    Chip4.setEnabled(false);
  ChipNums.add(Chip4);
  }else{Chip4.setEnabled(true);ChipNums.add(Chip4);} //End Chip 4

  if(board.checkFullColumn(4)){
    Chip5.setEnabled(false);
  ChipNums.add(Chip5);
  }else{Chip5.setEnabled(true);ChipNums.add(Chip5);} //End Chip 5

  if(board.checkFullColumn(5)){
    Chip6.setEnabled(false);
  ChipNums.add(Chip6);
  }else{Chip6.setEnabled(true);ChipNums.add(Chip6);} //End Chip 6
  
if(board.checkFullColumn(6)){
    Chip7.setEnabled(false);
  ChipNums.add(Chip7);
  }else{Chip7.setEnabled(true);ChipNums.add(Chip7);}//End slot 7
  
  if (firstGame) {
   Chip1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) { 
     Go(0);
     if (!board.Full()) {
      game();
      ComputerMove();}
     else{
       Chip1.setEnabled(false);}}});
   
   Chip2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(1);
     if (!board.Full()) {
      game();
        ComputerMove();}
      else{
     Chip2.setEnabled(false);}
     OutsideFrame.requestFocusInWindow();} });
   
   Chip3.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(2);
     if (!board.Full()) {
      game();
        ComputerMove(); }
      else{
     Chip3.setEnabled(false);}
     OutsideFrame.requestFocusInWindow(); }});
   
   Chip4.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(3);
     if (!board.Full()) {
      game();
      ComputerMove();}
      else{
     Chip4.setEnabled(false);}} });
   
   Chip5.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(4);
     if (!board.Full()) {
      game();
      ComputerMove();}
      else{
     Chip5.setEnabled(false);}}});
   
   Chip6.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(5);
     if (!board.Full()) {
      game();
      ComputerMove();}
      else{
     Chip6.setEnabled(false);}}});
   
   Chip7.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     Go(6);
     if (!board.Full()) {
      game();
        ComputerMove();}
      else{
     Chip7.setEnabled(false);}}});
   firstGame = false;}
 
  JPanel OptionButtons = new JPanel();
  OptionButtons.setLayout(new GridLayout(1, 7, 6, 4)); //Put buttons on the bottom
  JButton Exits = new JButton("Exit"); //Exit button
 JButton NewGame = new JButton("New Game"); //New Game Button
 OptionButtons.add(Exits); //Add buttons to frame
 OptionButtons.add(NewGame);
 
 Exits.addActionListener(new ActionListener() { //Exit Button
    public void actionPerformed(ActionEvent e) {
    System.exit(0);}}); //Quit
 
  NewGame.addActionListener(new ActionListener() { //New Game Button
    public void actionPerformed(ActionEvent e) {
    OutsideFrame.dispose(); //Get rid of fram to spawn a new game 
    Main c=new Main();
c.NewGame();}});
 
   PictureC4 = MakeMainBoard();
  panelMain = new JPanel(); //Create panel for everything going on Board
  panelMain.setLayout(new BorderLayout());
  panelMain.add(ChipNums, BorderLayout.NORTH); //Add panel with buttons to add chips to main panel
  panelMain.add( PictureC4, BorderLayout.CENTER); //Add connect4 panel to main panel
  panelMain.add(OptionButtons, BorderLayout.SOUTH);//Add exit and New Game Buttons to Main Panel
  OutsideFrame.setResizable(false);
  return panelMain;}
 
 public static void gameOver() {
  board.Done(true);

  int answer = 0;
  board.CheckWin();
  
  if (board.win() == Board.P) { //Player Win
    answer = JOptionPane.showConfirmDialog(null, "You Won! Play Again?" ,"Game Finished", JOptionPane.YES_NO_OPTION);
  } else if (board.win() == Board.C) { //Computer Win
    answer = JOptionPane.showConfirmDialog(null, "Computer wins! Play Again?" ,"Game Finished", JOptionPane.YES_NO_OPTION);
  }else {answer = JOptionPane.showConfirmDialog(null, "It is a Tie! Play Again?" ,"Game Finished", JOptionPane.YES_NO_OPTION);}
  
if (answer == JOptionPane.YES_OPTION) {
  OutsideFrame.dispose();
Main c=new Main();
c.NewGame();
  } else {System.exit(0);}}
 
 public static void main(String[] args){
  Main c = new Main();
  c.NewGame();}}