
public class Queue<E> 
{
    private class Node
    {
        E data;
        Node next;
        public Node()
        {
            data = null;
            next = null;
        }
        public Node (E data)
        {
            this.data = data;
            next = null;
        }
    }

    Node front, rear;
    int size;

    public Queue()
    {
        front = rear = null;
        size = 0;
    }

    public void insert(E x)
    {
        Node p = new Node(x);

        if (size == 0)
            front = rear = p;
        else // adds at rear
        {
            rear.next = p;
            rear = p;
        }
        size++;
    }

    public E remove()
    {
        if (size == 0)
        {
            System.out.println(" Queue Underflow");
            System.exit(0);
         }
        E temp = front.data; // get the data to return
        front = front.next; // move front up one node
        size--;
        if (size == 0) // if now empty
            rear = null;
        return temp;
    }

    public boolean empty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public E peek()
    {
        if (size == 0)
        {
            System.out.println(" Queue Underflow");
            System.exit(0);
        }
        E temp = front.data;
        return temp;
    }
}

