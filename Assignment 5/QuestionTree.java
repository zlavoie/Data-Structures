//Zoe Lavoie
import java.io.*;
import java.util.*;

public class QuestionTree implements Serializable  {
  
  public class GuessInNode implements Serializable  {
 public String guess;
    public GuessInNode yes; //Yes
    public GuessInNode no; //No
               
    public GuessInNode(String guess) { //leaf node
        this(guess, null, null);}                      

 public GuessInNode(String guess, GuessInNode yes, GuessInNode no) {
        this.guess = guess;
        this.yes = yes;
        this.no = no;}} //End Guess Node Class
  
 public GuessInNode t;
 transient public Scanner scan;

 public QuestionTree() {
  scan = new Scanner(System.in);
  t = new GuessInNode("Is it human?");
  t.yes= new GuessInNode("Is it Donald Trump?");
  t.no= new GuessInNode("Is it an elephant?");
 }
 
 public void read(Scanner input) {
  t = ReadTree(input);
 }
 
 private GuessInNode ReadTree(Scanner input) {
  String ReadInputType = input.nextLine();
  String text = input.nextLine();
  if (ReadInputType.equals("Q:")) {
   return new GuessInNode(text, ReadTree(input), ReadTree(input));
  } else { //if not just return the node with the text as the leaf node
   return new GuessInNode(text);
  }
 }
 
 public void write(PrintStream out) {
  t = WriteToFile(t, out);
 }
 
 private GuessInNode WriteToFile(GuessInNode t, PrintStream out) {
  
  if (t != null) {
   //if it's not a leaf node then print out as a question
   if (t.yes != null || t.no != null) {
    out.println("Q:");
    out.println(t.guess);
   } else { //else it is a leaf node
    out.println("A:");
    out.println(t.guess);
   }
   t.yes = WriteToFile(t.yes, out);
   t.no = WriteToFile(t.no, out);
  }
  return t;  
 }
 
 public GuessInNode askQuestions() {
  return AskQuestion(t);
 }
 
 private GuessInNode AskQuestion(GuessInNode t) {

        String OtherPersonObj = "";
        GuessInNode OtherPersonObjNode = null;
        String GetSmarterQuestion = "";
        String OppenentAnsw = "";
  
        if (t.yes == null && t.no == null) { 
   System.out.print("Is the Object/Person you were thinking of: " + t.guess+" (y/n)?"); //if it is a leaf, check answer
    String answer=scan.nextLine().trim().toLowerCase();  
  while (!answer.equals("y") && !answer.equals("n")) {
         System.out.println("Do you want to play again? I will be smarter this time....." + " (y/n)? ");
        answer = scan.nextLine().trim().toLowerCase();}
   
   if (answer.equalsIgnoreCase("y")) { //Computer wins if it is a yes
     System.out.println("I got it!"); }//end of the game
     
    else if (answer.equalsIgnoreCase("n")) { //Answer is a no, we collect guess to make the game smarter
     System.out.println();
    System.out.print("What is the name of your Object/Person? ");
    OtherPersonObj = scan.nextLine();
    OtherPersonObjNode = new GuessInNode(OtherPersonObj);
    System.out.print("Please give me a (y/n) question that distinguishes your Object/Person: ");
    GetSmarterQuestion = scan.nextLine();
    System.out.print("What is the answer for you question that applies to your Object/Person? (y/n)? ");
    OppenentAnsw = scan.nextLine();
    
    //if answer is yes then store in yes node, else store in no node: we are storing the guess we just collected base on the input we recieved
                if (OppenentAnsw.equalsIgnoreCase("y")) {
                    GuessInNode guess = new GuessInNode(t.guess);
                    GuessInNode question = new GuessInNode(GetSmarterQuestion, OtherPersonObjNode, guess);
                                   
                    t = question; //change pointer
          } else if (OppenentAnsw.equalsIgnoreCase("n")) {
           GuessInNode guess = new GuessInNode(t.guess);
                    GuessInNode question = new GuessInNode(GetSmarterQuestion, guess, OtherPersonObjNode);
                    t = question;}
          else {return t;}      
   } 
  } else { //not leaf node
   System.out.print(t.guess + " (y/n)? ");
   String TreeAnswer = scan.nextLine();
   
   if (TreeAnswer.equalsIgnoreCase("y")) {
    t.yes = AskQuestion(t.yes);
   } else if (TreeAnswer.equalsIgnoreCase("n")) {
    t.no = AskQuestion(t.no);
   } else { while (!TreeAnswer.equalsIgnoreCase("y") && !TreeAnswer.equalsIgnoreCase("n")) {
         System.out.println("Do you want to play again? I will be smarter this time....." + " (y/n)? ");
        TreeAnswer = scan.nextLine().trim().toLowerCase();}}}
        return t;}}