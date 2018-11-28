//Zoe Lavoie

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MultiLinkedListGUI extends JFrame {
  
  private class Node{   
    private String data; 
    private Node first; 
    private Node second; 
    
    public Node() {
      data = ""; 
      first = null;
      second = null;}
    
    public Node(String x) 
    { 
      data = x; 
      first = null; 
      second = null;}
    
    public String toString(){
      return data;}}
  
//Variables that actually matter  
  private Node[] students = new Node[100]; //Node array of students
  private Node[] courses = new Node[100]; //Node array of courses
  private int numS = 0; //number of students
  private int numC = 0; //number of courses
  private File outputfile; //File being written to
  private static PrintWriter output;
  
  //Bunch of GUI stuff
  private JLabel pic = new JLabel(new ImageIcon("picture.jpg")); //For picture at top of frame
  private JPanel bottomPanel = new JPanel(new GridLayout(1,3)); //Bottom Panel
  private JPanel InsertPanel = new JPanel(new GridLayout(5,1)); //Panel that adds students/courses to txt file
  private JPanel buttonPanel = new JPanel(new GridLayout(3,1)); //Choice panel with buttons
  private JPanel MainPanel = new JPanel(new GridLayout(3,1)); //Directions/instructions
  private JTextArea console = new JTextArea("Welcome to the System! Please make a choice.", 15, 30);
  private JScrollPane UpDown = new JScrollPane(console);
  private JButton InsertButton = new JButton("New Entry");
  private JButton Exits = new JButton("Exit");
  private JButton FindStudentsButton = new JButton("Get Students in a Course");
  private JButton FindCoursesButton = new JButton("Get Courses for a Student");
  private JLabel NLabel = new JLabel("Name");
  private JLabel CLabel = new JLabel("Course");
  private JTextField NArea = new JTextField(); //name area
  private JTextField CArea = new JTextField(); //course area
  private JButton Submit = new JButton("Enter");
  private int f = 0;
  private JLabel title = new JLabel("Multi-Linked List System");
  
  public MultiLinkedListGUI(){
    //Awful GUI Setup
    super("Multi-Linked List");
    setBounds(250,100, 900, 820);
    setBackground(Color.WHITE);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(new GridLayout(2,1));
    add(pic);
    InsertButton.addActionListener(new ButtonListener());
    buttonPanel.add(InsertButton);
    FindStudentsButton.addActionListener(new ButtonListener());
    buttonPanel.add(FindStudentsButton);
    FindCoursesButton.addActionListener(new ButtonListener());
    buttonPanel.add(FindCoursesButton);
    buttonPanel.setBackground(Color.GRAY);
    bottomPanel.add(buttonPanel);
    title.setHorizontalAlignment(JLabel.CENTER);
    Font font = new Font("Serif", Font.BOLD, 20);
    title.setFont(font); 
    Font fonts = new Font("Serif", Font.BOLD, 30);
    NLabel.setFont(font);
    CLabel.setFont(font);
    InsertButton.setFont(font);
    FindStudentsButton.setFont(font);
    FindCoursesButton.setFont(font);
    
    MainPanel.add(title);
    console.setEditable(false);
    console.setLineWrap(true);
    console.setWrapStyleWord(true);
    MainPanel.add(UpDown);
    Exits.addActionListener(new ButtonListener());
    MainPanel.add(Exits);
    MainPanel.setBackground(Color.YELLOW);
    bottomPanel.add(MainPanel);
    NLabel.setHorizontalAlignment(JLabel.CENTER);
    InsertPanel.add(NLabel);
    InsertPanel.add(NArea);
    CLabel.setHorizontalAlignment(JLabel.CENTER);
    InsertPanel.add(CLabel);
    InsertPanel.add(CArea);
    Submit.addActionListener(new ButtonListener());
    InsertPanel.add(Submit);
    NArea.setEnabled(false);
    CArea.setEnabled(false);
    Submit.setEnabled(false);
    InsertPanel.setBackground(Color.GRAY);
    bottomPanel.add(InsertPanel);
    add(bottomPanel);
    
    //Read in the file and store the students and courses
    try{
      File file = new File("student.txt");
      Scanner input = new Scanner(file);
      outputfile = new File("students1.txt");
      output = new PrintWriter(outputfile);
      
      while(input.hasNext()){
        String name = input.next().toUpperCase();
        String course = input.next().toUpperCase();
        add(name, course);}}
    catch(Exception e){
      System.out.println(e);}}
  
  public int search(Node[] array, String x, int low, int high){
    if (low > high) {
      return -1;
    }
    int mid = (high + low)/2;
    if ((array[mid].toString()).equals(x)) {
      return mid;
    } 
    else if((array[mid].toString()).compareTo(x) > 0) {
      return search(array, x, low, mid-1);
    } 
    else {
      return search(array, x, mid+1, high);}}
  
  public void swap(Node[] array, int a, int b){
    Node temp = array[b]; 
    array[b] = array[a];
    array[a] = temp;}
  
  public void sort(Node[] array, int length){
    for(int endPos = length-1; endPos>0; endPos--){
      for(int j = 0; j<endPos; j++){
        if((array[j].toString()).compareTo(array[j+1].toString()) > 0){
          swap(array, j, j+1);  }}}}
  
  public void add(String name, String course){
    int studentPos = search(students, name, 0, numS-1);
    int coursePos = search(courses, course, 0, numC-1);
    
    Node p;
    Node q;
    Node en;
    
    if(studentPos == -1 || numS == 0){
      
      p = new Node(name);
      students[numS] = new Node(name);
      students[numS].first = p;
      numS++;}
    else{
      p = students[studentPos].first;   }   
    if(coursePos == -1 || numC == 0){
      q = new Node(course);
      courses[numC] = new Node(course);
      courses[numC].second = q;
      numC++;  }
    else{
      q = courses[coursePos].second;    }
    en = new Node();
    if(p.first == null){
      p.first = en;
      en.first = p;}
    else{    
      Node r = p.first;
      while((r.toString()).compareTo(name) != 0){
        p = r;        
        r = r.first;}
      p.first = en;
      en.first = r;}
    if(q.second == null){
      q.second = en;
      en.second = q;}
    else{ 
      Node s = q.second;
      while((s.toString()).compareTo(course) != 0){
        q = s;
        s = s.second;}
      q.second = en;
      en.second = s;}
    
    try{
      output.println(name + " " + course);}
    catch(Exception e){
      System.out.println(e);}
    sort(students, numS);
    sort(courses, numC);}
  
  public void getStudents(String course){
    
    int coursePos = search(courses, course, 0, numC-1);
    
    if(coursePos == -1){
      console.setText("Course \"" + course + "\" not found!");}
    else{
      //walk through and print all the students 
      Node courseNode = courses[coursePos].second;
      console.setText(courseNode.toString() + ": ");
      courseNode = courseNode.second;
      Node cn = null;
      while (courseNode.toString().compareTo(course) !=0){
        cn = courseNode;
        while((cn.toString()).compareTo("") == 0)
          cn = cn.first;
        console.setText(console.getText() + "\n" + cn.toString());
        courseNode = courseNode.second;}}}
  
  public void getCourses(String student){
    int studentPos = search(students, student, 0, numS-1);
    
    if(studentPos == -1){
      console.setText("Student \"" + student + "\" not found!");}
    else{
      //walk through and print all the students 
      Node studentNode = students[studentPos].first;
      console.setText(studentNode.toString() + ": ");
      studentNode = studentNode.first;
      Node sn = null;
      while (studentNode.toString().compareTo(student) !=0){
        sn = studentNode;
        while((sn.toString()).compareTo("") == 0)
          sn = sn.second;
        console.setText(console.getText() + "\n" + sn.toString());
        studentNode = studentNode.first;}}}
  
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == Exits){
        output.close();
        System.exit(0);  }
      else if(e.getSource() == FindStudentsButton){
        CArea.setEnabled(true);
        NArea.setEnabled(false);
        CArea.setText("Enter course here.");
        console.setText("Enter the course name in the box on the right, and then press 'Enter'");
        Submit.setEnabled(true);
        f = 1;}
      else if(e.getSource() == FindCoursesButton){
        NArea.setEnabled(true);
        CArea.setEnabled(false);
        NArea.setText("Name here.");
        console.setText("Enter the student name in the box on the right, and then press 'Enter'");
        Submit.setEnabled(true);
        f = 2;}
      else if(e.getSource() == InsertButton){
        NArea.setEnabled(true);
        NArea.setText("Name here.");
        CArea.setEnabled(true);
        CArea.setText("Course here.");
        console.setText("Enter the student name and course in the  two boxes on the right, and then press Enter");
        Submit.setEnabled(true);
        f  = 3;} 
      else if(e.getSource() == Submit){
        switch(f){
          case 1:
            String course = CArea.getText().toUpperCase();
            if(CArea.getText().compareTo("Enter course here.") == 0){
              console.setText("Course Not Found!");}
            else{
            getStudents(course);
            CArea.setEnabled(false);
            Submit.setEnabled(false);
            CArea.setText("Enter course here.");}
            break;
            
          case 2:
            String name = NArea.getText().toUpperCase();
            if(NArea.getText().compareTo("Enter name here.") == 0){
              console.setText("No Name Entered!");}
            else{
            getCourses(name);
            NArea.setEnabled(false);
            Submit.setEnabled(false);
            NArea.setText("Enter name here.");}
            break;
          case 3:
            name = NArea.getText().toUpperCase();
            course = CArea.getText().toUpperCase();
            console.setText("");
            if(NArea.getText().compareTo("Enter name here.") == 0){
              console.setText("No Name Entered!");}
            if(CArea.getText().compareTo("Enter course here.") == 0){
              console.setText(console.getText() + "\nNo Course Entered!");}
            else{
            add(name, course);
            CArea.setEnabled(false);
            NArea.setEnabled(false);
            Submit.setEnabled(false);
            CArea.setText("Enter course here.");
            NArea.setText("Enter name here.");}
            break; 
          default: console.setText("Error!");
          break;}}}}
  
  public static void main(String[] args){
    MultiLinkedListGUI linked = new MultiLinkedListGUI();
   linked.setVisible(true);}}