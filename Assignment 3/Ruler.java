import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ruler extends Canvas{
  public void paint(Graphics graph){
    graph.drawLine(39, 100, 1040, 100); //External Boundaries
    graph.drawLine(39,140,1040,140);
    graph.drawLine(39,100,39,140);
    graph.drawLine(1040,100,1040,140);
    
    graph.drawLine(60,100,60,132); //markings for end points
    graph.drawLine(1030,100,1030,132);
    
    drawRuler(graph,32,550,70,1030);//call method drawRuler from middle point of ruler
  }
  
  public void drawRuler(Graphics graph, int YCoord, int location, int LeftBorder, int RightBorder){
    if(location<=LeftBorder||location>RightBorder){ //if external dimensions of ruler are crossed then return
      return;}
    
    if(YCoord<4){ //return if YCoord of mark to be made is less than 4
      return;}
    
    graph.drawLine(location,100,location,100+YCoord); //mark measurement
    drawRuler(graph,YCoord,(int)(location+YCoord*2.5),location,RightBorder);//draw right hand side of ruler
    drawRuler(graph,YCoord,(int)(location-YCoord*2.5),LeftBorder,location);//left hand side of ruler
    drawRuler(graph,YCoord/2,location,LeftBorder,RightBorder);}//Mark smaller parts of the ruler
  
  public static void main(String[] args){
    Ruler ruler= new Ruler();
    Frame frame=new Frame();
    frame.setSize(1100,280);
    frame.add(ruler);
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter(){
   public void windowClosing(WindowEvent we){
      System.exit(0);}});
     }}
    
    