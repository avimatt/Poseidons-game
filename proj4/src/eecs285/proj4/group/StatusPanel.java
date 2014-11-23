package eecs285.proj4.group;

import java.awt.*;

import javax.swing.*;

import eecs285.proj4.group.Ships.Ship;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel
{
  JPanel mainPan;
  JPanel totalHealthPan;
  JPanel actionsLeftPan;
  JPanel healthOfShipPan;
  JLabel totalHealthLabel;
  JLabel statusPanelTitle;
  JLabel actionLeftTitle;
  JLabel healthOfShipTitle;
  JTextField actionLeftField;
  JTextField totalHealthField;
  JTextField healthOfShipField;
  JLabel currentShipLabel;
  JButton attackButton;
  JButton moveButton;
  
  public StatusPanel()
  {
    mainPan = new JPanel (new GridLayout(7,1));
    
    Font TitleFont = new Font("Algerian", Font.BOLD, 24);    
    statusPanelTitle = new JLabel("Status Panel");
    statusPanelTitle.setFont(TitleFont);
    mainPan.add(statusPanelTitle);
    
    
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
    
    
    
    
    currentShipLabel = new JLabel ("  Current Ship: ");
    mainPan.add(currentShipLabel);
    
    attackButton = new JButton("Attack!");
    attackButton.setEnabled(false);
    moveButton = new JButton("Move");
    moveButton.setEnabled(false);
    mainPan.add(attackButton);
    mainPan.add(moveButton);
    
    healthOfShipPan = new JPanel(new FlowLayout());
    healthOfShipTitle = new JLabel("Current Health of Ship: ");
    healthOfShipField = new JTextField(5);
    healthOfShipField.setText("");
    healthOfShipField.setEditable(false);
    healthOfShipPan.add(healthOfShipTitle);
    healthOfShipPan.add(healthOfShipField);
    mainPan.add(healthOfShipPan);
    
    actionsLeftPan = new JPanel(new FlowLayout());
    actionLeftTitle = new JLabel("Actions Left: ");
    actionLeftField = new JTextField(5);
    actionLeftField.setText("");
    actionLeftField.setEditable(false);
    
    actionsLeftPan.add(actionLeftTitle);
    actionsLeftPan.add(actionLeftField);
    mainPan.add(actionsLeftPan);
    
  
    
    
   
    
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

 