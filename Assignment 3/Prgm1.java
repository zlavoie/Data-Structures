import java.io.*;
import java.util.*;

public class Prgm1 {
int r;
int n;
int c;
int AreaCount;
int Spot;
char [][] grid;
 
public Prgm1(){ //Reads in file, catches exceptions
  try{   
    Spot=0;
  String fileName = "grid.txt"; //Grid text
      File file = new File(fileName); //Get File Reader
      Scanner input = new Scanner(file);
      String line="";
line=input.nextLine(); //Read in line to get what length nxn grid is
line=line.replaceAll("\\s+", "");
n = line.length();
     grid = new char[n][n]; //Set up grid char
      for(int y= 0; y< n; y++){
        if(y>0){
          line = input.nextLine(); //Convert to char arrays
        line=line.replaceAll("\\s+", "");}
        for(int x = 0; x< n; x++){
          grid[x][y] = line.charAt(x);}}
      
input.close(); //Close Scanner
}
catch(FileNotFoundException e){
System.out.println("Cannot Find File");
}
 
System.out.println("----------Read In Grid----------");
DisplayTotalGrid();//Displays the read in board from grid.txt file
System.out.println();
 
for(int i=1; i<n; i++){
for(int j=1; j<n; j++){
AreaCount = Locate(i,j);
 
if(AreaCount!=0){
++Spot;
System.out.print(" Location: "+Spot);
System.out.print(": "+AreaCount);
System.out.print(" 'w' space(s) ||");
}}}
}//end default constructor
 
public int Locate(int r, int c){ //Locates the white spaces
if(grid[r][c]=='w'){
grid[r][c] = 'z'; //mark the visited spot as another letter so you know not to go back 
return (1 + Locate(r+1, c) + Locate(r, c+1) + Locate(r-1, c) + Locate(r, c-1));
}
return 0;} //No White Spaces
 
public void DisplayTotalGrid(){ //Print out the read in grid
for(int a=0; a<n; a++){
for(int b=0; b<n; b++){
  if(grid[a][b]=='w'){
System.out.print(grid[a][b]+" ");}
  else{
    System.out.print(grid[a][b]+"  ");
  }}
System.out.println();}}
 
public static void main(String [] args){ //Main
Prgm1 p = new Prgm1(); 
}
}//End Everything