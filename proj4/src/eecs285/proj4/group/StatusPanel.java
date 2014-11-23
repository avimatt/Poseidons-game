package eecs285.proj4.group;

import java.awt.*;

import javax.swing.*;

import eecs285.proj4.group.Ships.Ship;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel
{
  JPanel mainPan;
  JPanel totalHealthPan;
  JLabel totalHealthLabel;
  JLabel StatusPanelTitle;
  JTextField totalHealthField;
  JLabel currentShipLabel;
  JButton attackButton;
  JButton moveButton;
  
  public StatusPanel()
  {
    mainPan = new JPanel (new GridLayout(7,1));
    
    Font TitleFont = new Font("Algerian", Font.BOLD, 24);    
    StatusPanelTitle = new JLabel("Status Panel");
    StatusPanelTitle.setFont(TitleFont);
    
    
    //total health 
    totalHealthPan = new JPanel (new FlowLayout());
    totalHealthLabel = new JLabel ("Total Fleet Health: ");
    //need a function getTotalFleetHealth that returns the total health points
    totalHealthField= new JTextField(5);
    totalHealthField.setText("100/100");
    totalHealthField.setBackground(Color.GREEN);
    totalHealthField.setEditable(false);
    totalHealthPan.add(totalHealthLabel);
    totalHealthPan.add(totalHealthField);
    mainPan.add(totalHealthPan);
    
    
    
    
    currentShipLabel = new JLabel ("Current Ship: ");
    mainPan.add(currentShipLabel);
    
   
    
    add(mainPan);
   
  }
  public void updateStatusPanel()
  {
   
    
  }
  
  public void updateStatusPanel(Ship ship)
  {
    
    
  }
  
  
  private static void getHealthColor(JTextField healthField)
  {
    /*  if (getTotalHealth() >= 70)
    {
      helthField.setBackground(Color.GREEN);
    }
    else if (getTotalHealth() < 70 && getTotalHealth() >= 30 )
    {
      healthField.setBackground(Color.YELLOW);
    }
    else
    {
      healthField.setBackground(Color.RED);
    }
*/ 
  }
}

 