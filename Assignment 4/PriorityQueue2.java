import java.util.*;
//Zoe Lavoie
//Edited class from the one supplied to us
public class PriorityQueue2<E>
{
  // inserts data into the queue in order of SearchPriority
 // so the element with highest SearchPriority (lowest number) is first
 // remove() takes the  first element from queue

    private class Node
    {
        E data;
        int SearchPriority;
        Node next;
       
        public Node()  //default constructor
        {
            data = null;
            SearchPriority = 0;
            next = null;
        }
        public Node (E data,int SearchPriority) // one arguement constructor
        {
            this.data = data;
            this.SearchPriority = SearchPriority;
            next = null;} }

        Node front;
        int PriorityNode;
        Node NextHighest;
        int size;

        public PriorityQueue2() //Consturctor for Priority Queue 2
        {
            front = null;
            size = 0;
        }
        
        public void insert(PriorityData<E> x)
        {
            Node AddFront = new Node(x.getData(), x.getPriority()); //Takes the node and inserts it
            
            if(size==0){ //If there is nothing in the queue
             front = AddFront;
            }
            else if(size>0){
             Node setHighPriority = front; //Inserts at front of queue
             front = AddFront;
             front.next = setHighPriority;
            }
            size++;
        }
        
        public PriorityData<E> remove()
        {
           if (size == 0)
           {
                System.out.println("Nothing in the Stack! Cannot Remove.");
                System.exit(0);
           }
           Node l = null;
           Node f = front;
           PriorityNode = front.SearchPriority;
           NextHighest = front;
           Node PreviousNode = null;
           int SearchPriority;
           
           while (f != null) // while there is still a front to take from
           {
            SearchPriority = f.SearchPriority;
            if(SearchPriority<PriorityNode){ //Takes the priority node and compares it to all the others to find the node which is the least important in priority
                PriorityNode = SearchPriority; //Overwrites priority node because search priority was bigger
                NextHighest = f; //Bumps up a node, moves pointers up one node to the next
                PreviousNode = l ; 
               }
               l = f;
            f = f.next; //Move way down nodes
           }
           
           if(PreviousNode==null){
            front = NextHighest.next; //If there is no node, then the front is set to the highest SearchPriority
           }
           else{
            PreviousNode.next = NextHighest.next;}           
          
           PriorityData<E> setHighPriority = new PriorityData<E>(); //Sets the Highest Priority Node
           setHighPriority.setData(NextHighest.data);
           setHighPriority.setPriority(NextHighest.SearchPriority);

           size--;
           return setHighPriority; //Returns the new highest priority
        }
        
        public static void main(String args[])
    {
        Scanner input = new Scanner (System.in);
        PriorityData<String> priorityData;
        PriorityQueue2<String> q = new PriorityQueue2<String>();
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Enter a name and a priority: ");
            String n = input.next();

            int p = input.nextInt();
            priorityData= new PriorityData<String>(n,p);  // make a PriorityData object
            q.insert(priorityData); // insert the data and priority into the queue
        }

        System.out.println("The data in order of priority are:");
        for (int i = 0; i < 5; i++)
        {
            priorityData = q.remove();
            System.out.println(priorityData.getData() + " "+ priorityData.getPriority()); // print data with priority

        } 
        input.close();
    }
}
