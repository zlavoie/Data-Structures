//Zoe Lavoie
import java.io.*;
import java.util.*;
public class BinaryDigits
           {
                   public static void bitChecker(String bits)
                   {
                    Stack<Character>moveStack = new Stack<Character> (bits.length());
                    char[] array = new char[bits.length()];
                    //char[] temp = new char[bits.length()];
                     for(int j = 0; j< bits.length(); j++){
          array[j]= bits.charAt(j);
          if(moveStack.empty()){
            moveStack.push(array[j]);
                     }
          else if(array[j]==moveStack.peek()){
           // System.out.print("HERE");
            moveStack.push(array[j]);
          }
          else {
          moveStack.pop();}}
                   
                       if(moveStack.empty()==true){
                         System.out.println("Same number of 0's and 1's");
                       }
                      else if((Character) moveStack.peek() == '0'){
                       System.out.println("Different number of 0's and 1's.");
                       System.out.print(" "+moveStack.size()+" additional 0's");
                        System.out.println();
                       }
                      else if((Character) moveStack.peek() == '1'){
                        System.out.println();
                       System.out.print("Different number of 0's and 1's.");
                       System.out.print(" "+moveStack.size()+" additional 1's");
                       System.out.println();
                       }
                       
                }

                public static void main(String[] args)
                {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Enter a string of bits. Enter 11111 to exit: ");
                  

                    String bits = input.nextLine();
                    while (!bits.equals("11111"))
                     {
                               bitChecker(bits);
                               System.out.println();
                               System.out.print("Enter a string of bits. Enter 11111 to exit: ");
                                bits = input.nextLine();
                       }

                  }

           }