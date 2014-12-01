package eecs285.proj4.group;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import eecs285.proj4.group.Ships.Ship;
import eecs285.proj4.group.Ships.TotalHealth;

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
  JScrollPane statusLogScroll;
  JLabel currentShipLabel;
  JLabel attackPower;
  JLabel actionLogTitle;
  JButton attackButton;
  JButton moveButton;
  JButton cancelSelection;
  JButton helpButton;
  JButton endTurnButton;
  
  StatusAction listener;
  
  GamePlay game;
  Player player;
  
  Ship selectedShip;
  
  public StatusPanel(GamePlay gameIn, Player playerIn)
  {
	game = gameIn;  
	player = playerIn;
	  
    //This area sets up gaps in the layout to make things pretty
    GridLayout lowerButtons = new GridLayout(2,1);
    lowerButtons.setVgap(4);
    BorderLayout mainBorderLay = new BorderLayout();
    mainBorderLay.setVgap(11);
    BorderLayout totalBorderLay = new BorderLayout();
    totalBorderLay.setVgap(5);
    //////////////////////////////////////////////////////////////
    
    mainPan = new JPanel(mainBorderLay);
    upperPan = new JPanel(new GridLayout(2,1));
    lowerPan = new JPanel(lowerButtons);
    actionLogPan = new JPanel(new BorderLayout());
    totalPan = new JPanel(totalBorderLay);
    Font currentShipF = new Font("Times New Roman", Font.BOLD, 15);
    
    //adds a border around the status panel
    Border blackline;
    TitledBorder statusTitle;  
    blackline = BorderFactory.createLineBorder(Color.black);
    Font TitleFont = new Font("Times New Roman", Font.BOLD, 32);
    statusTitle = BorderFactory.createTitledBorder(blackline, "Status Panel");
    totalPan.setBorder(statusTitle);
    statusTitle.setTitleJustification(TitledBorder.CENTER);
    statusTitle.setTitleFont(TitleFont);
    
    //total health of fleet 
    totalHealthPan = new JPanel (new FlowLayout());
    totalHealthLabel = new JLabel ("Total Fleet Health: ");
    totalHealthField= new JTextField(5);
    totalHealthField.setText(" 100 %");
    totalHealthField.setBackground(Color.GREEN);
    totalHealthField.setEditable(false);
    totalHealthPan.add(totalHealthLabel);
    totalHealthPan.add(totalHealthField);
    upperPan.add(totalHealthPan);
    
    actionsLeftPan = new JPanel(new FlowLayout());
    actionLeftTitle = new JLabel("Actions Left: ");
    actionLeftField = new JLabel(Integer.toString(game.getActionsLeft()));
    actionLeftField.setFont(currentShipF);
    actionsLeftPan.add(actionLeftTitle);
    actionsLeftPan.add(actionLeftField);
    upperPan.add(actionsLeftPan);
    
    //Ship Info section/////////////////////////////////////////////////////
    //makes the gridlayout with gaps so buttons are not on top of each other
    GridLayout buttonGrid = new GridLayout(6,1);
    buttonGrid.setVgap(4);
    shipInfo = new JPanel(buttonGrid);    
    TitledBorder shipTitle;
    //adds the boarder around the ship selection area
    Font shipFont = new Font("Times New Roman", Font.BOLD, 17);    
    shipTitle = BorderFactory.createTitledBorder(blackline, "Ship Selected");
    shipInfo.setBorder(shipTitle);
    shipTitle.setTitleJustification(TitledBorder.CENTER);
    shipTitle.setTitleFont(shipFont);
    mainPan.add(shipInfo);
    
    //adds the buttons in the ship area
    listener = new StatusAction();
    currentShipLabel = new JLabel ("Ship: ");
    attackPower = new JLabel("Attack Strength: ");
    attackButton = new JButton("Attack!");
    attackButton.addActionListener(listener);
    cancelSelection = new JButton("Cancel Selection");
    cancelSelection.addActionListener(listener);
    attackButton.setEnabled(false);
    moveButton = new JButton("Move");
    moveButton.addActionListener(listener);
    moveButton.setEnabled(false);
    cancelSelection.setEnabled(false);
    currentShipLabel.setFont(currentShipF);
    attackPower.setFont(currentShipF);
    shipInfo.add(currentShipLabel);
    shipInfo.add(attackPower);
    shipInfo.add(attackButton);
    shipInfo.add(moveButton);
    shipInfo.add(cancelSelection);
    
    //health status of a ship
    healthOfShipPan = new JPanel(new FlowLayout());
    healthOfShipTitle = new JLabel("Current Health of Ship: ");
    healthOfShipField = new JTextField(5);
    healthOfShipField.setText("");
    healthOfShipField.setEditable(false);
    healthOfShipPan.add(healthOfShipTitle);
    healthOfShipPan.add(healthOfShipField);
    shipInfo.add(healthOfShipPan);
    ////////////////////////////////////////////////////////////////////////
    
    //adds buttons to lower panel
    endTurnButton = new JButton("End Turn");
    endTurnButton.addActionListener(listener);
    helpButton = new JButton("Help");
    helpButton.addActionListener(listener);
    lowerPan.add(endTurnButton);
    lowerPan.add(helpButton);
  
    //adds the status log
    statusLog = new JTextArea(10,5);
    statusLog.setEditable(false);
    actionLogTitle = new JLabel("Action Log: ");
    statusLogScroll = new JScrollPane(statusLog);
    statusLogScroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    actionLogPan.add(statusLogScroll, BorderLayout.SOUTH);
    actionLogPan.add(actionLogTitle, BorderLayout.NORTH);
    
    
    
    mainPan.add(upperPan, BorderLayout.NORTH);
    mainPan.add(shipInfo, BorderLayout.CENTER);
    mainPan.add(lowerPan, BorderLayout.SOUTH);
    
    totalPan.add(mainPan,BorderLayout.NORTH);
    totalPan.add(actionLogPan,BorderLayout.SOUTH);
   
    add(totalPan); 
   
  }
  
//---------------------------------------------------------------
  public void updateStatusPanel()
  {
    //totalHealthString needs a get totalHealth function
    //have it return an int to be used in getHealthColor 
    //function
    TotalHealth health = new TotalHealth();
    double fleetRatio = ((double)health.getFleetHealth(player)/
        (double)health.getTotalFleetHealth())*100;
    totalHealthField.setText((int)fleetRatio +" % ");
    getHealthColor(totalHealthField,fleetRatio);
    
    //needs a function that returns the amount of actions left
    //have it return an int.
    actionLeftField.setText(Integer.toString(game.getActionsLeft()));
    
  }
  
//---------------------------------------------------------------  
  public void updateStatusPanel(Ship ship)
  {
    //totalHealthString needs a get totalHealth function
    //have it return an int to be used in getHealthColor 
    //function  
    TotalHealth health = new TotalHealth();
    double fleetRatio = ((double)health.getFleetHealth(player)/
        (double)health.getTotalFleetHealth())*100;
    totalHealthField.setText((int)fleetRatio +" %");
    getHealthColor(totalHealthField,fleetRatio);
    
    //needs a function that returns the amount of actions left
    //have it return an int.
    actionLeftField.setText(Integer.toString(game.getActionsLeft()));
    
    currentShipLabel.setText("Ship: " + ship.getShipType());
    attackPower.setText("Attack Strength: " + ship.getAttackPower()
        + " /25");
    
    if(ship.getShipType() == "Patrol Boat")
    {
      attackButton.setEnabled(false);
    }
    else 
    {
      attackButton.setEnabled(true);
    }
    
    //disables the move and attack button for dead ships
    if(ship.getHealth() <= 0)
    {
      attackButton.setEnabled(false);
      moveButton.setEnabled(false);
      cancelSelection.setEnabled(false);
    }
    else
    {
      attackButton.setEnabled(true);
      moveButton.setEnabled(true);
      cancelSelection.setEnabled(true);
    }
   
    
    double healthShip = ((double)ship.getHealth()/(double)ship.getOriginalHealth()) * 100;
    healthOfShipField.setText(" " + (int)healthShip + " %");
    getHealthColor(healthOfShipField,healthShip);
    
    selectedShip = ship;
    
  }
  
//---------------------------------------------------------------   
  //sets the status log
  public void setLog(String status)
  {
    //keeps a history of the log
    String save = statusLog.getText();
    
    //if-else statements stops the first newline  
    if(statusLog.getText().isEmpty())
    {
      save = status;
    }
    else
    {
       save = save + "\n" + status;
    }
   
    statusLog.setText(save);  
  }

//---------------------------------------------------------------
  //used to change the background color of the health fields
  private static void getHealthColor(JTextField healthField, double health)
  { 
   double ratio;   
   ratio = health;
        
    int GREENBACK = 70;
    int YELLOWBACK = 30;
    //will be red once health is below YELLOWBACK
    
    if (ratio >= GREENBACK)
    {
      healthField.setBackground(Color.GREEN);
    }
    else if (ratio < GREENBACK && ratio >= YELLOWBACK )
    {
      healthField.setBackground(Color.YELLOW);
    }
    else
    {
      healthField.setBackground(Color.RED);
    }
 
  }
  
//---------------------------------------------------------------
  public void setNotYourTurn(){
	  attackButton.setEnabled(false);
	  moveButton.setEnabled(false);
	  cancelSelection.setEnabled(false);
	  endTurnButton.setEnabled(false);
  }
  
//---------------------------------------------------------------  
  public void setYourTurn(){
	  attackButton.setEnabled(true);
	  moveButton.setEnabled(true);
	  cancelSelection.setEnabled(true);
	  endTurnButton.setEnabled(true);
  }
  
  public void setEnd(){
	  System.out.println("enabling end turn button");
	  endTurnButton.setEnabled(true);
	  attackButton.setEnabled(false);
	  moveButton.setEnabled(false);
	  cancelSelection.setEnabled(false);
  }
  
//---------------------------------------------------------------  
  class StatusAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == moveButton){
			game.getBoardImage().getScreen().setMove(true);
			game.getBoardImage().getScreen().setAttack(false);
			game.getBoardImage().getScreen().setPanelSelectedShip(selectedShip);
			game.getBoardImage().getScreen().render(player);
			game.getBoardImage().paintComponent(game.getBoardImage().getGraphics());
			attackButton.setEnabled(false);
		}
		if(e.getSource() == attackButton){
			game.getBoardImage().getScreen().setMove(false);
			game.getBoardImage().getScreen().setAttack(true);
			game.getBoardImage().getScreen().setPanelSelectedShip(selectedShip);
			game.getBoardImage().getScreen().render(player);
			game.getBoardImage().paintComponent(game.getBoardImage().getGraphics());
			moveButton.setEnabled(false);
		}
		if(e.getSource() == cancelSelection){
			game.getBoardImage().getScreen().setMove(false);
			game.getBoardImage().getScreen().setAttack(false);
			game.getBoardImage().getScreen().setPanelSelectedShip(null);
			game.getBoardImage().getScreen().render(player);
			game.getBoardImage().paintComponent(game.getBoardImage().getGraphics());
			moveButton.setEnabled(true);
			attackButton.setEnabled(true);
		}
		if(e.getSource() == helpButton)
		{
		  new HelpGUI();
		}
		if(e.getSource() == endTurnButton){
			game.getNetwork().sendEndTurn();
			endTurnButton.setEnabled(false);
			game.setActionsLeft(3);
			game.setYourTurn(false);
		}
	}
	  
  }
}

 