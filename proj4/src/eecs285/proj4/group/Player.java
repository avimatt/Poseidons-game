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
  
  // TODO is this ever used anywhere??????????????
  
  /*public Ship getPlayerShip(int shipNumber) throws Exception
  {
    return myBoard.getShip(shipNumber, ID);
  }*/

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
    //TODO: tell ImageBoard to update ?
    //I suggest that Ships have a broadcast function
    //which they use to tell ImageBoard to update
  }
  
//---------------------------------------------------------------
  public void attackLoc(Ship curShip, Location targetLoc)
  {
    if (myBoard.isLocOccupied(targetLoc))
    {
      Ship targetShip = null;
        targetShip = myBoard.getShip(targetLoc);
        if ( targetShip != null)
        {
          return;
        }
      targetShip = myBoard.getOpponentShip(targetLoc);
      if((targetShip != null) && (targetShip.getHealth() <= 0))
        {
          //target ship is dead
          return;
        }
      targetShip.takeHit(curShip.getAttackPower());
      //TODO: If targetShip died, either it broadcasts its dead state
      //to ImageBoard or Player tells ImageBoard about it.
    }
  }
  //TODO: output message about what happened
 
//---------------------------------------------------------------
  public void setActionsLeft(int actions)
  {
    actionsLeft = actions;
  }

  public int getActionsLeft()
  {
    return actionsLeft;
  }

  public Board getBoard(){
	  return myBoard;
  }

}
