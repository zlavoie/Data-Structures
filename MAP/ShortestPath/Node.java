//Zoe Lavoie

public class Node implements Comparable
{
  protected int origin;
  protected int last;
  protected Node nxt;
  protected int dist;
  protected boolean visited;
  
  public Node()
  {
    origin=-1;
    last=-1;
    nxt=null;
    dist=0;
    visited=false;
  }
  
  public Node(int v,int p,Node n,int d)
  {
    origin=v;
    last=p;
    nxt=n;
    dist=d;
    visited=false;
  }
  
  public int compareTo(Object o)
  {
    if(dist>((Node)o).dist){
      return 1;}
    else if(dist<((Node)o).dist){
      return -1;}
      return 0;
  }}