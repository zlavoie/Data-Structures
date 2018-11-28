//Zoe Lavoie
import java.awt.Color;

public class Tile {
    private Color SetTColor;
    private boolean NotEmpty;
    
    public Tile(boolean Whites) {
        NotEmpty = false;

        if (Whites) {
            SetTColor = Color.WHITE; }else { //Sets color to white if boolean is true
            SetTColor = Color.RED;}}//Sets color to red if boolean is false. these give us our checker board
    
    public Color getColor() {
        return SetTColor;
    }
    
    public boolean isEmpty() {
        return !NotEmpty;
    }
    
    public void place(boolean data) {
        NotEmpty = data;
    }
}