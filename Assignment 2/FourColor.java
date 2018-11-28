//Zoe Lavoie
import java.util.*;
import java.io.*;

public class FourColor {
private String[] StatesString = new String[49]; //Holds all the States
private char[][] Map = new char[49][49]; 
private boolean[] Marked = new boolean[49]; //Keeps track with true/false if the state has been visited
private Stack<Integer> tracking = new Stack<Integer>(49); //Set both stacks to a capacity of 49
private Stack<Integer> StateList = new Stack<Integer>(49);
private int ColorIn= 0; //Color in the states
private int Previous; //Keeps track previous number we were at for state position
private boolean done; //Are we done running through all the states? true/false
private int PreviousState;
private int x =1;
private String[] ColorList = {"Red", "Green", "Yellow", "Blue"}; //Just like the map displayed online

public FourColor() throws FileNotFoundException{
Scanner scan = new Scanner(new File("matrix.txt"));
Previous=0;
PreviousState=0;
for(int k=0;k<49;k++){
String IncomingState = scan.nextLine(); //IncomingState saves the the string of the next line
StatesString[k] = IncomingState.substring(IncomingState.indexOf(" ")+1);
Marked[k] = false;}
scan.nextLine(); //Gets us past blank line to matrix
for(int a=0; a<49;a++){
String IncomingState = scan.nextLine(); //Takes in the first line of the matrix
for(int b=0;b<49;b++){
Map[b][a] = IncomingState.charAt(b);}} // Converts it to char in an array for that line in a two dimensional array
}

public boolean Finished(){
done= true; //This checks to see if the location is unmarked. If it is unmarked then the location is false and we have more spaces to go.
for(int z=0; z<49; z++){
if(Marked[z]==false){
done = false;}}
return done;}

public int Marking(int count){
for(int c=0;c<49;c++){
if((Map[c][count]=='1')&&(Marked[c]==false)){
return c;}}
return 99;}

public void StatesToStack(int count){
int s = StateList.peek(); //Take the top of the stack without removing it and save the number to variable s to get color
if(s==4){ //If s is greater than 3 then we need to reset the Color Count to 0 to cycle back to the first color
ColorIn= 0;
s=ColorIn;}
System.out.println(x+". "+StatesString[count]+" ----->"+" Color "+StatesString[count]+" "+ColorList[s]);
x++;
Marked[count] = true;
tracking.push(count);
}

public void Coloring(){ //Coloring the states 
StateList.push(ColorIn); // Color the state with ColorIn variable
StatesToStack(47); //send 47 as count to stack

while(tracking.empty()==false){ //If the stack is not empty then we take the state number
int StateNum = Marking(tracking.peek()); //StateNum is set
if(StateNum == 99){ //If State num returnd 99 then it is false
StateList.pop();
Previous = tracking.pop();}
else{
ColorIn=ColorIn+1;
StateList.push(ColorIn); //Push Color onto Stack
StatesToStack(StateNum); //Send State to Stack
PreviousState = StateNum;}} //Set PreviousState to the current State
System.out.println("---------------------------------------------");
System.out.println("The Trip Through The States Is Done!");}

public static void main(String [] args) throws FileNotFoundException{
FourColor ColorStates = new FourColor();
ColorStates.Coloring();
}}