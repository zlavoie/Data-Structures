//Zoe Lavoie
//This was a class that was supplied to us
public class PriorityData <E>
{ 
    private E data; 
    private int priority; 
    public PriorityData()
    {
        // default constructor 
        data = null;
        priority = 0;
    }

    public PriorityData(E data, int priority) 
    {
        this.data = data;
        this.priority = priority;
    }

    public E getData()
    {
        return data;
    }

    public int getPriority()
    {
        return priority;
    }
    public void setData(E data)
    {
        this.data = data;
    }
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
}