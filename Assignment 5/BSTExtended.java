//Zoe Lavoie
import java.util.*;
public class  BSTExtended<E extends Comparable> extends BinaryTree<E>
{
      public BSTExtended()  // constructor
      {
        super();
      }
      
public void levelOrder(){
   Queue<Node> q = new Queue<Node>();//new queue
  q.insert(root);//insert root into queue
  
  while(!q.empty()){
   Node x = q.remove();//remove the front element
   System.out.print(x.data + " ");
   
   if(x.left!=null){
     q.insert(x.left);}
   if(x.right!=null){
     q.insert(x.right);}
   
  }//while q is not empty
}

 
public void displayTree(){
recurdisplayTree(root);
}
 
public void recurdisplayTree (Node root){ // Each child of a tree is a root of its subtree.
   System.out.print(root.data);
  if (root.left != null){
      System.out.print("(");
        recurdisplayTree (root.left);
        System.out.print(" ");
    }
    if (root.right != null){
        recurdisplayTree (root.right);
        System.out.print(")");
        System.out.print(" ");
    }
  if ((root.left==null&&root.right!=null)||(root.left!=null&&root.right==null))
  {
     System.out.print(" -");}
}

      public void insert(E x) 
      // inserts a n in subtree of shrter height
      {
         if (root == null)
         {
            root = new Node(x);
             return;
         }
         Node p= root;
         Node q = null;
         while (p != null)
         {
            q = p;
            if (x.compareTo(p.data)<=0)
              p= p.left;
            else
              p = p.right;
         }
         if (x.compareTo(q.data)<=0)
         {
             q.left = new Node(x);
         }
         else
         {
             q.right = new Node(x);
         }
      }
      
      public E search(E x)
      {
         if( root == null)
            return null;
         Node p = root;
         while(p != null )
             if (x.compareTo(p.data)<0)      // x < p.data
               p = p.left;
             else if (x.compareTo(p.data)>0) // x > p.data
               p = p.right;
             else
                 return p.data;              // x equal to p.data

           return null;
      }

        public boolean delete(E x)
        {
         Node p = root;
         Node parent = null;
          while (p != null)
          {
            if (x.compareTo(p.data) < 0)
            {
               parent = p;
               p = p.left;
            }
            else if (x.compareTo(p.data) > 0)
            {
               parent = p;
               p = p.right;
            }
            else
               break; //p is pointing to x
          }
          if (p == null)
            return false;

          // p has no left children
          if (p.left == null)
          {
            if (parent == null)
               root = p.right;
            else
            {
               if(x.compareTo(parent.data) < 0)
                  parent.left = p.right;
               else
                  parent.right = p.right;
            }
          }
          else   //p has a left subtree
          {            //find the immediate predecessor of p 
            Node q = p;
            Node r = p.left;   // go left from p

            while( r.right != null)  
                           //continue right as far as possible
            {
               q = r;
               r = r.right;
            }
            p.data = r.data; //put data of predecessor into n p

            if(q.right == r)  // adjust referneces
               q.right = r.left;
            else
               q.left = r.left;
          }
          return true;
   }
        
     public static void main(String[] args) {
       BSTExtended tree = new BSTExtended();
       Scanner scan = new Scanner(System.in);
 
        /*  BST
                9
             /     \
             5    13
           /   \    
         3    7 
       /  \    /
     2   4  6         
        tree.insert(9);
        tree.insert(5);
        tree.insert(13);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
         tree.insert(6);*/
       
       
       //TO TEST: Copy and Paste the example given in the assignment: 9 5 13 3 7 2 4 6 0
       int num=-1;
       System.out.println("Please enter numbers to add to the tree (Enter 0 to Stop): ");
       while(num!=0){
       num = scan.nextInt();
       if(num!=0){
         tree.insert(num);
       }}
         System.out.println();
          System.out.println("Level Order: ");
   tree.levelOrder();
   System.out.println();
   System.out.println();
   System.out.println("Display Tree: ");
   tree.displayTree();
   System.out.println();
       
    }       
}
