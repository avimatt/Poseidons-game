package eecs285.proj4.group;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import eecs285.proj4.group.Ships.Ship;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel
{
  JPanel mainPan;
  JPanel actionLogPan;
  JPanel totalPan;
  JPanel upperPan;
  JPanel lowerPan;
  JPanel shipInfo;
  JPanel totalHealthPan;
  JPanel actionsLeftPan;
  JPanel healthOfShipPan;
  JLabel totalHealthLabel;
  JLabel statusPanelTitle;
  JLabel actionLeftTitle;
  JLabel healthOfShipTitle;
  JLabel actionLeftField;
  JTextField totalHealthField;
  JTextField healthOfShipField;
  JTextArea statusLog;
  JLabel currentShipLabel;
  JLabel actionLogTitle;
  JButton attackButton;
  JButton moveButton;
  JButton cancelSelection;
  JButton helpButton;
  JButton endTurnButton;
  
  public StatusPanel()
  {
    
    mainPan = new JPanel(new BorderLayout());
    upperPan = new JPanel(new GridLayout(3,1));
    lowerPan = new JPanel(new GridLayout(2,1));
    actionLogPan = new JPanel(new BorderLayout());
    totalPan = new JPanel(new BorderLayout());
    Font currentShipF = new Font("Times New Roman", Font.BOLD, 15);
    
    Font TitleFont = new Font("Times New Roman", Font.BOLD, 24);    
    statusPanelTitle = new JLabel("Status Panel");
    statusPanelTitle.setFont(TitleFont);
    upperPan.add(statusPanelTitle);
    
    
    //total health 
    totalHealthPan = new JPanel (new FlowLayout());
    totalHealthLabel = new JLabel ("Total Fleet Health: ");
    //need a function getTotalFleetHealth that returns the total health points
    totalHealthField= new JTextField(5);
    //needs a function to get a number for the total health of fleet
    String totalHealthString = "100";
    totalHealthField.setText(totalHealthString +"/100");
    totalHealthField.setBackground(Color.GREEN);
    totalHealthField.setEditable(false);
    totalHealthPan.add(totalHealthLabel);
    totalHealthPan.add(totalHealthField);
    upperPan.add(totalHealthPan);
    
    actionsLeftPan = new JPanel(new FlowLayout());
    actionLeftTitle = new JLabel("Actions Left: ");
    //need function that can get actions left////////////
    actionLeftField = new JLabel("5");
    actionLeftField.setFont(currentShipF);
    actionsLeftPan.add(actionLeftTitle);
    actionsLeftPan.add(actionLeftField);
    upperPan.add(actionsLeftPan);
    
    //Ship Info section/////////////////////////////////////////////////////
    shipInfo = new JPanel(new GridLayout(5,1));    
    Border blackline;
    TitledBorder shipTitle;
    Font shipFont = new Font("Times New Roman", Font.BOLD, 17);    
    blackline = BorderFactory.createLineBorder(Color.black);
    shipTitle = BorderFactory.createTitledBorder(blackline, "Ship Selected");
    shipInfo.setBorder(shipTitle);
    shipTitle.setTitleJustification(TitledBorder.CENTER);
    shipTitle.setTitleFont(shipFont);
    mainPan.add(shipInfo);
    
    currentShipLabel = new JLabel ("Ship: ");
    attackButton = new JButton("Attack!");
    cancelSelection = new JButton("Cancel Selection");
    attackButton.setEnabled(false);
    moveButton = new JButton("Move");
    moveButton.setEnabled(false);
    cancelSelection.setEnabled(false);
    currentShipLabel.setFont(currentShipF);
    shipInfo.add(currentShipLabel);
    shipInfo.add(attackButton);
    shipInfo.add(moveButton);
    shipInfo.add(cancelSelection);
    
    healthOfShipPan = new JPanel(new FlowLayout());
    healthOfShipTitle = new JLabel("Current Health of Ship: ");
    healthOfShipField = new JTextField(5);
    healthOfShipField.setText("");
    healthOfShipField.setEditable(false);
    healthOfShipPan.add(healthOfShipTitle);
    healthOfShipPan.add(healthOfShipField);
    shipInfo.add(healthOfShipPan);
    ////////////////////////////////////////////////////////////////////////
    
    endTurnButton = new JButton("End Turn");
    helpButton = new JButton("Help");
    lowerPan.add(endTurnButton);
    lowerPan.add(helpButton);
  
    statusLog = new JTextArea(10,10);
    statusLog.setEditable(false);
    actionLogTitle = new JLabel("Action Log: ");
    actionLogPan.add(statusLog, BorderLayout.SOUTH);
    actionLogPan.add(actionLogTitle, BorderLayout.NORTH);
    
    
    mainPan.add(upperPan, BorderLayout.NORTH);
    mainPan.add(shipInfo, BorderLayout.CENTER);
    mainPan.add(lowerPan, BorderLayout.SOUTH);
    
    totalPan.add(mainPan,BorderLayout.NORTH);
    totalPan.add(actionLogPan,BorderLayout.SOUTH);
   
    
    add(totalPan);
   
  }
  public void updateStatusPanel()
  {
    //totalHealthString needs a get totalHealth function
    //have it return an int to be used in getHealthColor 
    //function
    String totalHealthString = "100";
    totalHealthField.setText(totalHealthString +"/100");
    getHealthColor(totalHealthField,100);
    
    //needs a function that returns the amount of actions left
    //have it return an int.
    actionLeftField = new JLabel("5");
    
    //need function to that will say what ship has been hit
    statusLog.setText("NEED FUNCTION TO GET STATUS LOG!");
    
  }
  
  public void updateStatusPanel(Ship ship)
  {
    //totalHealthString needs a get totalHealth function
    //have it return an int to be used in getHealthColor 
    //function
    String totalHealthString = "100";
    totalHealthField.setText(totalHealthString +"/100");
    getHealthColor(totalHealthField,100);
    
    //needs a function that returns the amount of actions left
    //have it return an int.
    actionLeftField = new JLabel("5");
    
    
    currentShipLabel = new JLabel ("Ship: " + ship.toString());
    attackButton.setEnabled(true);
    moveButton.setEnabled(true);
    cancelSelection.setEnabled(true);
    
    String healthS = Integer.toString(ship.getHealth());
    healthOfShipField.setText(healthS + "/100");
    getHealthColor(healthOfShipField,ship.getHealth());
    
    //need function to that will say what ship has been hit
    statusLog.setText("NEED FUNCTION TO GET STATUS LOG!");
    
  }
  
  
  private static void getHealthColor(JTextField healthField, int healthPoints)
  {
      if (healthPoints >= 70)
    {
      healthField.setBackground(Color.GREEN);
    }
    else if (healthPoints < 70 && healthPoints >= 30 )
    {
      healthField.setBackground(Color.YELLOW);
    }
    else
    {
      healthField.setBackground(Color.RED);
    }
 
  }
}

 