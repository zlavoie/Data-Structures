//Zoe Lavoie
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MapsGUI extends JFrame
{
  private JButton clear,compute,exit;
  private JPanel center,bottom,top;
  private JLabel start,end,distance;
  private JList startList,endList;
  private JTextField DistanceStops;
  private JScrollPane scroll,scroll2,scrollPane;
  private Maps MAPS; 
  private JTextArea path;
  
  public MapsGUI() //GUI
  {
    super("Maps");
    setBounds(300,25,500,490);
    
    MAPS = new Maps(); //Map objects
    top = new JPanel(); //Top Panel
    start = new JLabel("Orgin");
    start.setHorizontalAlignment(SwingConstants.CENTER);
    startList = new JList(MAPS.locations);
    startList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    startList.setVisibleRowCount(8);
    scroll = new JScrollPane(startList);
    end = new JLabel("  Destination");
    endList = new JList(MAPS.locations);
    endList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    end.setHorizontalAlignment(SwingConstants.CENTER);
    endList.setVisibleRowCount(8);
    scroll2 = new JScrollPane(endList);
    
    top.add(start);
    top.add(scroll);
    top.add(end);
    top.add(scroll2);
    add(top,BorderLayout.NORTH);
    
    center = new JPanel();
    clear = new JButton("Clear");
    compute = new JButton("Compute");
    exit = new JButton("Exit");
    path = new JTextArea(15, 35);
    scrollPane = new JScrollPane(path);
    path.setEditable(false);
    
    center.add(clear);
    center.add(compute);
    center.add(exit);
    center.add(scrollPane);
    
    add(center,BorderLayout.SOUTH); //Bottom Panel
    bottom = new JPanel();
    distance = new JLabel("Mileage");
    distance.setHorizontalAlignment(SwingConstants.CENTER);
    DistanceStops = new JTextField(20);
    DistanceStops.setOpaque(true);
   DistanceStops.setBackground(Color.WHITE);
    DistanceStops.setEditable(false);
    bottom.add(distance);
    bottom.add(DistanceStops);
    add(bottom);
    
    //adds buttons to handlers
    clear.addActionListener(new ButtonHandler());
    compute.addActionListener(new ButtonHandler());
    exit.addActionListener(new ButtonHandler());
  }
  
  //Handles all the button stuff
  private class ButtonHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent e) 
    {
      if(e.getSource()==compute) //Calculates Distance Between Locations
      {
        MAPS = new Maps();
        int start = startList.getSelectedIndex();
        int end = endList.getSelectedIndex();
        DistanceStops.setText(MAPS.shortestPath(start,end)+"");
        String data="";
        for(int i=MAPS.counter-1;i>=0;i--)
          data+=MAPS.path[i]+'\n' ;
        if(MAPS.counter==0)
          path.setText("No path available");
        else
        path.setText(data);
      }
      if(e.getSource()==clear) //CLEAR BUTTON
      {
        path.setText("");
        DistanceStops.setText("");
      } 
      if(e.getSource()==exit) //EXIT BUTTON to get out of this hellish program
        System.exit(0);
    }
  }
  
  //main method.
  public static void main(String[] args)
  {
    JFrame frame = new MapsGUI(); 
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(500,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  } 
}