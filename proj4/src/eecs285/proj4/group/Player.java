package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

public class Player
{
  // array to contain players ship.
  // private int damage_Dealt;
  private static int ID = 0;
  private Board myBoard;

  public Player()
  {
    ID++;
    myBoard = new Board();
  }

  public Ship getPlayerShip(int shipNumber) throws Exception
  {
    return myBoard.getShip(shipNumber, ID);
  }

  public int getID()
  {
    return ID;
  }
  
  //called after ship and new location have been clicked
  public void moveShip(Ship curShip, Location newLoc)
  {
    curShip.setCurrentLoaction(newLoc);
    //TODO: tell ImageBoard to update ?
    //I suggest that Ships have a broadcast function
    //which they use to tell ImageBoard to update
  }
  
  public void attackLoc(Ship curShip, Location targetLoc)
  {
    if (myBoard.isLocOccupied(targetLoc))
    {
      Ship targetShip = null;
      try
      {
        targetShip = myBoard.getShip(targetLoc);
        if (targetShip.getHealth() <= 0)
        {
          //target ship is dead
          return;
        }
      }
      catch( Exception e )
      {
        //something other than ship at targetLoc
      }
      targetShip.takeHit(curShip.getAttackPower());
      //TODO: If targetShip died, either it broadcasts its dead state
      //to ImageBoard or Player tells ImageBoard about it.
    }
  //TODO: output message about what happened
  }
  
  public Board getBoard(){
	  return myBoard;
  }
  
  
  
  

}
