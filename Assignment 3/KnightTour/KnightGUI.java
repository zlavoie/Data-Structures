//Zoe Lavoie
import java.awt.*;
import javax.swing.*;

class KnightGUI extends JFrame{
    private KnightTourStart ObjectMemory;
    private JButton[][] matrix;
    private final char seperate;
    private final int rows;
    private final int columns;
    private final int Height = 800;
    private final int Width = 800;
    private final int LocationX = 500;
    private final int LocationY = 0;

    public KnightGUI (KnightTourStart start, int side, char s){
        this.ObjectMemory = start;
        this.rows = side;
        this.columns = side;
        this.seperate = s;
        matrix = new JButton[rows][ columns];
        Everything();}
    
    public void setBackground(Color color, int row, int col) {
        matrix[row][col].setBackground(color);} //Sets the background color for tile
    
    public void setText(String text, int row, int col) {
        matrix[row][col].setText(text);} //Sets numbers on tiles
    
    public void Everything(){
        setResizable(false);
        setSize(Width, Height); //Sets Size of Board nxn
        setLocation(LocationX, LocationY); //Sets Location
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Allows the Frame To Close
        setLayout(new GridLayout(rows, columns)); //Create Grid For All The Buttons
        
            for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++){
                matrix[i][j] = new JButton();
                matrix[i][j].setFont(matrix[i][j].getFont().deriveFont(20.0f)); //The font comes up super tiny on my screen so i put it at 20 IF YOU CANNOT SEE THE NUMBERS EDIT THIS
                matrix[i][j].addActionListener(ObjectMemory); //Adds action listener for this button
                matrix[i][j].setActionCommand(Integer.toString(i) + seperate + Integer.toString(j));
                this.add(matrix[i][j]);}}}
    
    public void Visible() {
        this.setVisible(true);}}