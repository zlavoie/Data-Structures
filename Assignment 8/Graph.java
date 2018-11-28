import java.util.*;

public class Graph{
 private Node[] nodes;
 private int numOfNodes;
 private Edge[] edges;
 private int numOfEdges;
 
 public Graph(Edge[] edges){
   this.edges=edges;
   
   this.numOfNodes=calculateNumOfNodes(edges);
   this.nodes= new Node[this.numOfNodes];
   
   for(int n=0; n<this.numOfNodes;n++){
     this.nodes[n]=new Node();
   }
   this.numOfEdges=edges.length;
   
   for(int edgeToAdd=0;edgeToAdd<this.numOfEdges;edgeToAdd++){
     this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
     this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
   }  
 }
 
 private int calculateNumOfNodes(Edge[] edges){
   int numOfNodes=0;
   
   for(Edge e : edges){
     if(e.getToNodeIndex()>numOfNodes)
       numOfNodes=e.getToNodeIndex();
     if(e.getFromNodeIndex()>numOfNodes)
       numOfNodes=e.getFromNodeIndex();}
   
   numOfNodes++;
  return numOfNodes;
 }
 
 public void calculateShortestDistance(){

   this.nodes[0].setDistanceFromSource(0);
   int nextNode=0;
   
   for(int i=0; i<this.nodes.length; i++){
     ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
     
     for(int joinedEdge =0;joinedEdge<currentNodeEdges.size();joinedEdge++){
       int neighborIndex = currentNodeEdges.get(joinedEdge).getNeighborIndex(nextNode);
       
       if(!this.nodes[neighborIndex].isVisited()){
         int tentative=this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
         
         if(tentative<nodes[neighborIndex].getDistanceFromSource()){
           nodes[neighborIndex].setDistanceFromSource(tentative);
         }}}
nodes[nextNode].setVisited(true);

nextNode=getNodeShortestDistanced();
}}
 
private int getNodeShortestDistanced(){
  int storedNodeIndex = 0;
    int storedDist = Integer.MAX_VALUE;
    
    for(int i=0;i<this.nodes.length;i++){
      int currentDist=this.nodes[i].getDistanceFromSource();
      
      if(!this.nodes[i].isVisited() && currentDist<storedDist){
        storedDist = currentDist;
        storedNodeIndex=i;
      }}
    return storedNodeIndex;
}

public void printResult(){
  String output = "Number of nodes = "+this.numOfNodes;
  output+="\nNumber of edges =" +this.numOfEdges;
  
  for(int i=0;i<this.nodes.length;i++){
    output+=("\nThe shortest distance from the node 0 to node "+i+" is "+nodes[i].getDistanceFromSource());
  }
System.out.println(output);}

 public Node[] getNodes(){
   return nodes;}
 public int getNumofNodes(){
   return numOfNodes;}
 public Edge[] getEdges(){
   return edges;}
 public int getNumOfEdges(){
   return numOfEdges;}
}