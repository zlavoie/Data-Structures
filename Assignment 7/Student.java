

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Student
{
static Scanner input;
  
static void ReadFile(LinkedList<Course> list) throws FileNotFoundException, IOException //to read data from file names.txt
{
String line=null;
list.clear();
BufferedReader reader=new BufferedReader(new FileReader("student.txt"));
while(( line=reader.readLine())!=null)
{
String[] arr=line.split("\\s+"); //spliting string by space and storing into array
list.add(new Course(arr[0],arr[1])); //adding data to linked list
}
}
static void findStudent(LinkedList<Course> list,String CName) //to find all student of course
{
System.out.println("\n");
for(Course course:list)
{
if(course.getCourse().equalsIgnoreCase(CName))
System.out.println(course.getName());
}
}
static void findCourse(LinkedList<Course> list,String SName) //to find all course for student
{
System.out.println("\n");
for(Course course:list)
{
if(course.getName().equalsIgnoreCase(SName))
System.out.println(course.getCourse());
}
}
  
static void addStudent(LinkedList<Course> list) // to add new name to course
{
String name,cName;
System.out.println("Enter Course Name: ");
cName=input.next();
System.out.println("Enter Student Name: ");
name=input.next();
try
{
FileWriter writer=new FileWriter("student.txt",true);
writer.write(System.getProperty("line.separator")+name+" "+cName);
writer.close();
System.out.println("File Created!!!!");
ReadFile(list);
  
}catch(Exception e)
{
System.out.println(e);
}
}
static void menu(LinkedList list) //menu
{
int n;
System.out.println("\n1.Find all students in a course"+
"\n2.Find all courses for a student"+
"\n3.Add a student to a course"+
"\n4.Exit"+
"\nEnter Your Choice: ");
n=input.nextInt();
switch(n)
{
case 1:String CName;
System.out.println("Enter Course Name: ");
CName=input.next();
findStudent(list,CName);
break;
case 2:String Name;
System.out.println("Enter Student Name: ");
Name=input.next();
findCourse(list,Name);
break;
case 3:addStudent(list);
break;
case 4:
System.exit(0);
break;
default:System.out.println("Wrong Choice!!!");
}
}
public static void main(String[] args) throws IOException //main()
{
LinkedList<Course> list=new LinkedList(); //linkedlist
ReadFile(list);
input=new Scanner(System.in);
while(true) //while until user exits
{
menu(list);
}
}
  
}
class Course //class course
{
String name;
String course;
public Course() //constructor
{
name="";
course="";
}
public Course(String name,String course) //constructor
{
this.name=name;
this.course=course;
}
public String getName() //to get name
{
return name;
}
public String getCourse() //to get course
{
return course;
}
}