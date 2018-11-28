
//Zoe Lavoie
//Assingment 2

import java.io.*;
import java.util.*;

public class Mouse{
  
  private char[][] maze;
  private char[][] walkedMaze;
  private int rows;
  private int columns;
  private Position exit;
  private Stack<Position> moveStack = new Stack<Position>(1000);
  //declaration of variables
  
  
  public Mouse(){
    String fileName = "maze.txt";
    maze = getMaze(fileName);
    walkedMaze = maze;
  }
  //constructor
  
  public char[][] getMaze(String fileName){
    char[][] array = new char[0][0]; 
    try{
      File file = new File(fileName);
      Scanner input = new Scanner(file);
      rows = Integer.parseInt(input.next());
      columns = Integer.parseInt(input.next());
      array = new char[columns][rows];
      input.nextLine();
      for(int i = 0; i< rows; i++){
        String line = input.nextLine(); 
        for(int j = 0; j< columns; j++){
          array[j][i] = line.charAt(j); 
          if(line.charAt(j) == 'm'){
            Position start = new Position(j, i);
            moveStack.push(start);
          }
          if(line.charAt(j) == 'e'){
            exit = new Position(j, i);
          }
        }
      }
      input.close();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
    return array; 
  }
  //gets maze from text document and converts it to a char array
  
  public char[][] walkMaze(char[][] maze, Stack<Position> stack){
    if(stack.peek().equals(exit)){
      return maze;
    }
    
   // stack.peek().print();
    maze[stack.peek().x][stack.peek().y] = 'x';
    
    
    if(maze[stack.peek().x+1][stack.peek().y] == '0' || maze[stack.peek().x+1][stack.peek().y] == 'e' )
      stack.push(new Position(stack.peek().x+1, stack.peek().y));
    else if(maze[stack.peek().x][stack.peek().y+1] == '0' || maze[stack.peek().x][stack.peek().y+1] == 'e')
      stack.push(new Position(stack.peek().x, stack.peek().y+1));
    else if(maze[stack.peek().x-1][stack.peek().y] == '0' || maze[stack.peek().x-1][stack.peek().y] == 'e')
      stack.push(new Position(stack.peek().x-1, stack.peek().y));
    else if(maze[stack.peek().x][stack.peek().y-1] == '0' || maze[stack.peek().x][stack.peek().y-1] == 'e')
      stack.push(new Position(stack.peek().x, stack.peek().y-1));
    else{
      if(stack.numItems == 1){
        System.out.println("Trapped -- No Way out!");
        return maze;
      }
      else{
      Position last = stack.pop();
      maze[last.x][last.y] = '1';  
      }
    }
    
    return walkMaze(maze, stack);
  }
  //recursive method to walk the maze using stacks and mark the path
  
  public void printMaze(){
    //System.out.println(rows + " Rows");
    //System.out.println(columns + " Columns");
    for(int i = 0; i< rows; i++){
      for(int j = 0; j< columns; j++){
        System.out.print(walkedMaze[j][i] +" ");
      }
      System.out.println();
    }
  }
  //prints the walked maze marked with the path
  
  public static void main(String[] args){
    Mouse m = new Mouse();
    m.walkMaze(m.walkedMaze, m.moveStack);
    m.printMaze();
    
    
  }
  //main method
}