package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

public class Player
{
  // array to contain players ship.
  // private int damage_Dealt;
  private static int ID = 0;
  private Board myBoard;
  private int actionsLeft = 5;

  public Player()
  {
    ID++;
    myBoard = new Board();
  }

//---------------------------------------------------------------
  public int getID()
  {
    return ID;
  }
  
//---------------------------------------------------------------
  //called after ship and new location have been clicked
  public void moveShip(Ship curShip, Location newLoc)
  {
    curShip.setCurrentLocation(newLoc);
  }
  
//---------------------------------------------------------------
  public void attackLoc(Ship curShip, Location targetLoc, GamePlay game)
  {
      Ship targetShip = null;
      targetShip = myBoard.getShip(targetLoc);
      if ( targetShip != null)
      {
    	  game.getStatusPanel().setLog("Your attack has missed all enemy ships");
    	  game.getNetwork().sendAttackMiss();
          game.decrementActions();
    	  return;
      }
      targetShip = myBoard.getOpponentShip(targetLoc);
      if((targetShip != null))
      {
    	  if(targetShip.getHealth() <= 0){
    		//target ship is dead
    		  game.getStatusPanel().setLog("You have attacked an already dead ship");
    		  game.getNetwork().sendAttackMiss();
          game.decrementActions();
    		return;
    	  }
    	  if(targetShip.takeHit(curShip.getAttackPower()) == 0){
    		  game.getStatusPanel().setLog("You have sunk one of their ships");
    	  } else {
    		  game.getStatusPanel().setLog("You have hit one of their ships");
    	  }
    	  game.getNetwork().sendAttackHit(targetShip);
      }
      game.decrementActions();
      game.getStatusPanel().updateStatusPanel();
  }
 
//---------------------------------------------------------------
  public void setActionsLeft(int actions)
  {
    actionsLeft = actions;
  }

//---------------------------------------------------------------
  public int getActionsLeft()
  {
    return actionsLeft;
  }

//---------------------------------------------------------------
  public Board getBoard(){
	  return myBoard;
  }

}
