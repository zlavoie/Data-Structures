//Zoe Lavoie
import java.awt.Color;

public class Board {
    private Tile tile[][];
    private final int boardLength;

    public Board(int length) {
        boardLength = length;
        tile = new Tile [length][length];
        PlaceTiles();}

    private void PlaceTiles() { //Changes the board tiles to different colors like a chess board
        boolean switchTiles = true;
        for (int row=0; row<boardLength; row++) {
            for (int col=0; col<boardLength; col++) {
                if (switchTiles) {
                    tile[row][col] = new Tile(true);} //Creates new tiles dependeding on the last color set using a switch statement
                else {
                    tile[row][col] = new Tile(false);
               }
                switchTiles = !(switchTiles);}
                switchTiles = !(switchTiles);}//Do this statement twice, once in each for loop to get the checkered look
    }

    public boolean isEmpty(int row, int col) { //Is the square empty? returns true/false
        return tile[row][col].isEmpty();}

    public Color TileColor(int row, int col) { //Returns the color of the set tile
        return tile[row][col].getColor(); }

    public void checkTile(int row, int col) { //Checks the tile position
        tile[row][col].place(true);}
    
   public boolean inBounds(int position) { //Checks to make sure we are still inside the 8x8 chess board
        return ((position >= 0) && (position < boardLength));}
}