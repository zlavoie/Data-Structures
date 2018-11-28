//Zoe Lavoie
public class Dequeue<E>{

  protected class Node<E> {
        protected E data;
        protected Node<E> previous; 
        protected Node<E> next;
        
        protected Node(E data) {
            this.data = data;
            previous=null;
            next=null;
        } 
        protected E getData(){
          return data;
        }
  } //End Node Class
  
    protected Node <E> front;
    protected Node <E> rear;
    protected int size;

    public Dequeue() {
      front=null;
        rear=null;
      size=0;
    } //Constructor

    public boolean isEmpty() { //Is the queue empty?
      if(size == 0){
        return true;}
      return false;
    }

    public int size() { //return size
        return size;
    }
    
    public E peekFront(){
      return front.getData();
    }
    
    public E peekRear(){
      return rear.getData();
    }

    public void insertFront(E data) {    // add the data to the front
                    if (size++ == 0) {
            front = new Node(data);
            rear=front;} //If queue is 0 set rear = to front
            else 
            {
      Node<E> previousFront = front;
            front = new Node(data);
            front.next = previousFront;
            previousFront.previous = front;}}

    public void insertRear(E data) { //Add data to rear
        if (size++ == 0) {
            front = new Node(data);
            rear=front;}
            Node<E> previousLast = rear;
            rear = new Node(data);
            rear.previous = previousLast;
            previousLast.next = rear;}

    public E removeFront() { //Remove Front
      if (front == null) {
                System.out.println("No front to remove");
       E data=null;
        return data;
      }
        E data = front.data;
        front = front.next;
        if (--size> 0) front.previous = null;
        return data;
    }

    public E removeRear() { //Remove Rear
      if (rear == null) {
        System.out.println("No end to remove");
        E data=null;
        return data;
      }
        E data = rear.data;
        rear = rear.previous;
        if (--size> 0){ 
          rear.next = null;}
        return data;}
     
    public static void main(String[] args){
    Dequeue <String> d = new Dequeue <String> ();
     d.insertFront("Dopey");
     d.insertFront("Happy");
     d.insertRear("Doc");
     //System.out.println("Peek Front: "+d.peekFront());
     //System.out.println("Peek Rear: "+d.peekRear());
     System.out.println(d.removeFront());
     System.out.println("Dequeue size is "+ d.size());
     System.out.println(d.removeRear());
     System.out.println(d.removeFront());
     System.out.println("Dequeue size is "+ d.size());
     d.insertRear("Sleepy");
    System.out.println(d.removeFront());
      System.out.println("Dequeue size is "+ d.size());
    
    }}