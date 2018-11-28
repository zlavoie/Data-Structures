//Zoe Lavoie
import java.io.*;
import java.util.*;

//"Here's to brother Ian, brother Ian, brother Ian, 
//Here's to brother Ian, he's trying to get...."
//Too soon? Too soon.

public class Question implements Serializable { 
public String data;
public Question left;
public Question right;
                
public Question(String data) { //leaf
        this(data, null, null);}                       
public Question(String data, Question left,Question right) { //creates branch of tree with subtrees
        this.data = data;
        this.left = left;
        this.right = right;}

 public static void main(String[] args) throws IOException {  
      QuestionTree g=new QuestionTree();
      ObjectOutputStream output= new ObjectOutputStream(new FileOutputStream("GuessingTree.txt"));
   output.writeObject(g.t);
         String response=" ";
          Scanner scan=new Scanner(System.in);
   System.out.println("--------------------GUESSING GAME--------------------");
     do{
       System.out.println("Welcome to the Guessing Game!");
       System.out.println("Think of an Object/Person for me to guess. Think Hard!");
      System.out.println();
       System.out.println("Type 'ready' if you are ready to play!");
         String Ready = scan.nextLine().trim().toLowerCase();
      while(!Ready.equals("ready")){
          System.out.println("Type 'ready' if you are ready to play!");
         Ready = scan.nextLine().trim().toLowerCase();}
      g.askQuestions();
     System.out.print("Do you want to play again? I will be smarter this time....." + " (y/n)?");
     response = scan.nextLine().trim().toLowerCase();
     while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Do you want to play again? I will be smarter this time....." + " (y/n)? ");
         response = scan.nextLine().trim().toLowerCase();}} //If response is Yes, then we take what was just learned an apply it to the next game.
     while (response.equals("y"));
  
 scan.close();}}
