//Zoe Lavoie
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KnightTourStart implements ActionListener {
    private final char ConvertToString = ' ';
    private KnightGUI display;
    private Tour t;
    private Board board;
    private int n;

    KnightTourStart(){ //Sets everything up including the display, the board, and the Tour of finding the moves
      n=0;
        display = new KnightGUI(this, 8, ConvertToString); //sends in object of the KnightTour, the 8 of the board, and the char , to separate spaces
        board = new Board(8);
       t = new Tour(board);
         for (int r=0; r<8; r++) {
            for (int c=0; c<8; c++) {
              setColor(r, c);}}
         display.Visible();
         }
    
    public void Again(){ //Resets the board so you can keep clicking wherever you want and it will print out the board
      board = new Board(8);
       t = new Tour(board);
         for (int r=0; r<8; r++) {
            for (int c=0; c<8; c++) {
              setColor(r, c);}} //Sets the color for that specific spot using booleans to keep flipping the color like a chess board
         display.Visible(); //Make the board visible
    }

    public void actionPerformed(ActionEvent event){
      if(n>0){ //This is magical idk why this works to keep it continuously running, i tried everything 
        n=0;
        Again();
      }
        String [] MarksTile = (event.getActionCommand()).split(ConvertToString+"");
        int r = Integer.parseInt(MarksTile[0]); //Gives r to calculate t
        int c = Integer.parseInt(MarksTile[1]); //Gives c to calculate t.... There is probably a better way to do this but nothing I was doing was working
        CalculateTour(r, c);
        n++;}

    public void CalculateTour(int startRow, int startCol) { //FINDS THE PATH IN WHICH THE KNIGHT CAN MOVE
        ArrayList <Coordinate> pos = null;
        pos = t.FindTour(startRow, startCol); //Starts the tour with beginning r and c

        for (int start=0; start<64; start++) { //Goes until the 8 of all the squares which is 64 on an 8x8 board
           int RowAfterStart = pos.get(start).getR(); //recursively runs through the rest of the rs setting the text using ArrayList pos
           int ColAfterStart = pos.get(start).getC(); //Did this idea from the jack problem with using the coordinates to get what i need
            TextLabel(start + 1, RowAfterStart, ColAfterStart);}} //Moves up the start+1 to get the new text number up to the next tile number and send in the current r and c number

    public void setColor(int r, int c) { //SETS COLOR
        display.setBackground(board.TileColor(r, c), r, c);} //SETS TILE COLOR

    public void TextLabel(int text,int r, int c) { //SETS NUMBER TEXT
        display.setText(Integer.toString(text), r, c);}

    public static void main(String [] args) {
     KnightTourStart k = new KnightTourStart(); //Starts the Whole Tour
     }}