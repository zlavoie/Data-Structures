import java.util.*;
class Stack<E> 
{
    private E[] items;
    int top;
    int stackSize;
    int max;

    public Stack()
    // default constructor; creates an empty stack
    {
        items = (E[]) new Object [49]; // capacity is 10
        top = -1;
        stackSize = 0;
        max = 10;
    }

    public Stack(int max)
    //one argument constructor, creates a stack capacity max
    {
        items = (E[]) new Object [max];
        top = -1;
        stackSize = 0;
        this.max = max;
    }

    public void push(E x)
    {
        if (stackSize == max)
        {
            System.out.println("Push:Stack Overflow");
            System.exit(0);
        }
        top++;
        items[top] = x;
        stackSize++;
    }

    public E pop()
    {
        if (stackSize == 0)
        {
            System.out.println("pop::Stack Underflow");
            System.exit(0);
        }
        E topItem = items[top];
        top--;
        stackSize--;
        return topItem;
    }

    public E peek()
    {
        if (stackSize == 0)
        {
            System.out.println("peek:Stack Underflow");
            System.exit(0);
        }
        return items[top];
    }

    public boolean empty()
    {
        return stackSize == 0;
    }
    public int size()
    {
        return stackSize;
    }

}