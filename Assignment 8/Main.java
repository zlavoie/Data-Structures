import java.util.*;
import java.io.*;
public class Main{

  //go through file and save all the first names in an array
  //then go through file 
  
  
  public static void main(String[] args) throws IOException {
    Edge[] edges= {
      new Edge(0,2,98),
      new Edge(0,3,4),
      new Edge(0,4,2),
      new Edge(0,1,3),
      new Edge(1,3,2),
      new Edge(1,4,3),
      new Edge(1,5,1),
      new Edge(2,4,1),
      new Edge(3,5,4),
      new Edge(4,5,2),
      new Edge(4,6,7),
      new Edge(4,7,2),
      new Edge(5,6,4),
      new Edge(6,7,5),
    };
 /*   String [] NumState;
   String [] NumConnected;
  int [] NumDistances;  
  
 Scanner scan = new Scanner(new File("map.txt")); 
 String state="";
 String prevState=" ";
 int a=0;
  while(scan.hasNextLine()){
  scan.nextLine();
  state=scan.next();
  if(!state.equals(prevState)){
  NumState[a]=state;
  prevState=state;
  a++;
  }else{
  //Has same number (a) for NumState but we need to add the new connected to state
  }
  }
  
  
  
  
  
  
  
NumState=new String[200];
NumConnected=new String[200];
NumDistances=new int[200];
int a=0;

do{
String State = scan.next();
String ConnectedState=scan.next();
int distance = scan.nextInt();
NumState[a]= State;
NumConnected[a]=ConnectedState;
NumDistances
a++;
} while(scan.hasNextLine());
  */  
Graph g = new Graph(edges);
g.calculateShortestDistance();
g.printResult();
//  scan.close();
  }}