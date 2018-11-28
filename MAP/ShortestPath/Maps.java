//Zoe Lavoie

import java.util.*;
import java.io.*;

public class Maps  
{
  protected String[] locations;
  protected int routeLength=0, mileage=0;
  protected PriorityQueue<Node> p;
  protected Node[] route;
  protected String[] path;
  protected int counter;
 
  public Maps()
  {
    locations = new String[325];
    route = new Node[1000];
    path = new String[1000];
    p = new PriorityQueue<Node>();
    try
    {
      String Cities = "cityNames.txt";
      File fileIn = new File(Cities);
      Scanner scan = new Scanner(fileIn);
      int i=0;
      while(scan.hasNext()) //Take in first file
      {
        String city = scan.next();
        locations[i]=city;
        i++;
      }
     Cities = "map.txt";
      fileIn = new File(Cities);
      scan = new Scanner(fileIn);
      while(scan.hasNext())//Take in second file
      {
        String origin = scan.next();
        String last = scan.next();
        int d = scan.nextInt();
        int index=0;
        int PrevIndex=0;
        for(int j=0;j<325;j++)
        {
          if(origin.equals(locations[j]))
            index=j;
          if(last.equals(locations[j]))
            PrevIndex=j;
        }
        
        if(route[index]==null)
        {
          Node first = new Node(index,-1,null,0);
          route[index] = first;       
          route[index].nxt= new Node(PrevIndex,index,null,d);
        }
        else{
          Node Temporary = route[index];
          while(Temporary.nxt!=null){
            Temporary=Temporary.nxt;}
          Temporary.nxt= new Node(PrevIndex,index,null,d);}
        
        if(route[PrevIndex]==null){
          Node second = new Node(PrevIndex,-1,null,0);
          route[PrevIndex] = second;
          route[PrevIndex].nxt= new Node(index,PrevIndex,null,d);}
        else{
          Node Temporary = route[PrevIndex];
          while(Temporary.nxt!=null){
            Temporary=Temporary.nxt;}
          Temporary.nxt= new Node(index,PrevIndex,null,d);}}}
    
    catch(Exception e)
    {
      System.out.println("File not found.");
      System.exit(0);}
  }
  
  public int shortestPath(int start,int end)
  {
    boolean done=false;
    p.insert(route[start]);
    Node destination= new Node();
    while(!done && !p.empty()){
      Node x = p.remove();
      int v = x.origin;
      if(!route[v].visited){
        route[v].visited=true;
        route[v].dist=x.dist;
        route[v].last = x.last; }
      if(v==end){
        done=true;
        destination=route[v];}
      else{
        for(Node w = route[v].nxt; w!=null;w=w.nxt){
          if(!w.visited){
            w.visited=true;
            int d = route[v].dist + w.dist;
            p.insert(new Node(w.origin,v,null,d));}}}}
      if(!done){
        System.out.println("No path");
        return 0;}
      else{
        int totalDist=destination.dist;
        counter=0;
        int counter2=0;
        int[] testing = new int[1000000];
        Node minus=route[destination.last];
        
        while(minus.last!=-1)
        {
          testing[counter2]=minus.dist;
          counter2++;
          minus=route[minus.last];
        }
        testing[counter2]=0;
        
       // int counter3=0;
        //places the path in the array
        while(destination.last!=-1)
        {
        //  int minusDist=0;
          path[counter]= locations[destination.last] +"  -->  "+locations[destination.origin] +"  "+(destination.dist-testing[counter]);
          counter++;
          //counter3++;
          destination=route[destination.last];
        }
        return totalDist;
      }}}