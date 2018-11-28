//Zoe Lavoie
import java.util.ArrayList;

public class Tour {
    private Board Board;
    private final int[] Rows; //x direction move array
    private final int[] Cols; //y dirrection move array
    private int NxtRow; //Looks at Next Row
    private int NxtCol; //Looks at Next Col
    private int MovesTrue; //Checks move validity

    public Tour(Board board) {
        Rows = new int[] {1, -1, 2, -2, 1, -1, 2, -2}; //Array for x direction
        Cols = new int[] {-2, -2, -1, -1, 2, 2, 1, 1}; //Array for moves in y direction
        Board = board; }

    public ArrayList <Coordinate> FindTour(int beginRow, int beginCol) {
        ArrayList <Coordinate> SpotOnBoard = new ArrayList<Coordinate>();
        Coordinate nxtMove = null; //For start next Move is null
        int NxtRow = beginRow; //Set Beginning Row
        int NxtCol = beginCol; //Set Beginning Col
        int StartMovesCount = 0; //Initialize Counter
        
        while(StartMovesCount < 64) {
            SpotOnBoard.add(new Coordinate(NxtRow, NxtCol)); //Adding new position
            Board.checkTile(NxtRow, NxtCol); //Checks to see if Next Tile Has Been Visited and Next Num Can be Placed
            nxtMove = getBestMove(getPossibleMoves(NxtRow, NxtCol));
            NxtRow = nxtMove.getR(); //Calculates the Next Row Using Knights Moves
    
            NxtCol = nxtMove.getC(); //Calculates the Next Col Using Knights Moves
            StartMovesCount++;
        }
        return SpotOnBoard;}

    public ArrayList <Coordinate> getPossibleMoves(int row, int column) {
        ArrayList <Coordinate> SpotOnBoard = new ArrayList<Coordinate>();
        int NxtRow = 0;
        int NxtCol = 0;

        for (int start=0; start<8; start++) {
           NxtRow = 0;
          NxtCol = 0;
           MovesTrue = 0;
            NxtRow = row + Rows[start]; //Gives the next row
            NxtCol = column + Cols[start]; //Gives the next col
            if (CheckValidity(NxtRow, NxtCol)) { //Checks we are still in the Board
                SpotOnBoard.add(new Coordinate(NxtRow, NxtCol, Check(NxtRow, NxtCol)));
            }}
        return SpotOnBoard;}

    public Coordinate getBestMove(ArrayList <Coordinate> spot) {
        int row = -1; int column = -1; int maxMoves = 9; int SpotMoves = 0;  int listLength = spot.size();
        
       for (int start=0; start < listLength; start++) {
            SpotMoves = spot.get(start).getMovement();
            if(SpotMoves < maxMoves) {
                maxMoves = SpotMoves;   //finds position with fewer valid moves
                row = spot.get(start).getR();
                column = spot.get(start).getC();
            }}
        return new Coordinate(row, column); }

    public int Check(int row, int column) {
        for (int start=0; start<8; start++) {
            NxtRow = row + Rows[start];
            NxtCol = column + Cols[start];
            if (CheckValidity(NxtRow, NxtCol)) {
                MovesTrue++;}}
        return MovesTrue;}

    private boolean CheckValidity(int row, int column) {
        if ((Board.inBounds(row)) && (Board.inBounds(column))) { //Board contains position
            return Board.isEmpty(row, column); //position is empty
        } else {//position is not in bounds of Board 
          return false;}}}