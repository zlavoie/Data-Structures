//Zoe Lavoie
//Assingment 2

import java.io.*;
import java.util.*;

public class Jack{
  
  private static char[][] matrix;
  private static String[] states;
  private static int[] visited;
  private Stack<Integer> moveStack = new Stack<Integer>(1000);
  //creates variables
  
  public Jack(){
    String fileName = "matrix.txt";
    matrix = getMatrix(fileName);
  }
  //default constructor
  
  public char[][] getMatrix(String fileName){
    states = new String[49];
    visited = new int[49];
    char[][] array = new char[0][0]; 
    try{
      File file = new File(fileName);
      Scanner input = new Scanner(file);
      for(int k = 0; k < 49; k++){
        String line = input.nextLine();
        states[k] = line.substring(line.indexOf(" ")+1);
        visited [k] = 0;
      }
      array = new char[49][49];
      input.nextLine();
      for(int i = 0; i< 49; i++){
        String line = input.nextLine(); 
        for(int j = 0; j< 49; j++){
          array[j][i] = line.charAt(j); 
        }
      }
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
    return array; 
  }
  //reads in the matrix to a char array
  
  public void goTo(int number){
    System.out.println("Visited " + states[number]);
    visited[number] = 1;
    moveStack.push(number);
  }
  
  public int getUnvisitedAdjacent(int number){
    for(int j = 0; j< 49; j++){
      if(matrix[j][number] == '1'){
        //if is adjacent
        if(visited[j] == 0){
          return j;
        }
        //and is not visited yet
      }
    }
    return -1;
  }
  //checks if a neigbhor has been visited
  
  public void roadTrip(Stack<Integer> stack){
    int lastVisited = -2;
    System.out.println("Started Trip.");
    System.out.println();
    goTo(47);
    while(!stack.empty()){
      //while stack is not empty
      int w = getUnvisitedAdjacent(stack.peek());
      if(w != -1){
        //While there is an unvisited state W adjacent to top of stack
        goTo(w); 
        lastVisited = w;
      }
      else{
        int x = stack.pop();
        if(lastVisited != x)
        System.out.println("Went back to " + states[x]);
        
      }
    }
    System.out.println();
    System.out.println("Finished Trip and returned to Start.");
  }
  //recursive loop to complete the trip
  
  public boolean done(){
    for(int j = 0; j<visited.length; j++){
      if(j != 1)
        return false;
    }
    return true;
  }
  //checks if is done
  
  public static void main(String[] args){
    Jack jack = new Jack();
    jack.roadTrip(jack.moveStack);
  }
  //main method
}