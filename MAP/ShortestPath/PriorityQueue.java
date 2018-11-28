//Zoe Lavoie
public class PriorityQueue<E extends Comparable>
{
  private E[] data;
  private int size;
  private int max;
  private Integer num;
  
  public PriorityQueue()
  {
    data = (E[]) new Comparable[10000];
    max=10000;
  }
 
  public PriorityQueue(int n)
  {
    data = (E[]) new Comparable[n];
    max=n;
  }

  public void insert(E key)
  {
    
    if(size==max)
    {
      System.out.println("Heap overflow"); 
      System.exit(0);
    }
    int i=size;
    
    while(i>0 && data[(i-1)/2].compareTo(key) <0)
    {
      data[i]=data[(i-1)/2];
      i=(i-1)/2; 
    }
    
    data[i]=key;
    size++;
    
    heapify(data,size,0);
  } 

  public E remove()
  {
    if(size<1)
    {
      System.out.println("Heap underflow");
      System.exit(0);
    }
    E top =data[0];
    data[0] = data[size-1];
    size--;
    heapify(data,size,0);
    return top;
  }
  
  public void heapify(E[] A, int size, int i)
  {
    int biggest;
    int left = (2*i)+1;
    int right = (2*i)+2;
    
    if((left<size)&&(data[left].compareTo(data[i])<0))
      biggest=left;
    else
      biggest=i;
    
    if((right<size)&&(data[right].compareTo(data[biggest])<0))
      biggest=right;
    if(biggest!=i)
    {
      swap(i,biggest);
      heapify(data,size,biggest);}}
 
  public void swap(int i,int biggest)
  {
    E temp = data[i];
    data[i] = data[biggest];
    data[biggest]=temp;
  }

  public int size()
  {
    return size; 
  }
  
  public boolean empty()
  {
    if(size==0)
      return true;
    return false;
  }
  
  //returns the element on the top of the heap.
  public E top()
  {
    if(size==0)
    {
      System.out.println("Error.");
      System.exit(0);
    }
    return data[0];
  }
  
  //prints the heap in order.
  public void print()
  {
    for(int i=0;i<size;i++)
      System.out.print(data[i]+" ");
    System.out.println();
  }}