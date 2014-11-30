/**
 * 
 */
package eecs285.proj4.group;

import eecs285.proj4.group.Ships.Ship;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Board
{
  private ArrayList<Ship> shipList;
  private ArrayList<Ship> opponentShipList;
  //TODO: add functionality to do something with dead ships
  
  /**
   * 
   */
  public Board()
  {
	shipList = new ArrayList<Ship>();
	opponentShipList = new ArrayList<Ship>();
  }

//---------------------------------------------------------------
  public Ship getShip(int shipId)
  {
	  for(Ship curShip : shipList){
		  if(curShip.getID() == shipId){
			  return curShip;
		  }
	  }
	  // should never reach here
	  return null;
  }
  
//--------------------------------------------------------------- 
  public Ship getShip(Location curLoc) throws Exception
  {
    for (Ship curShip : shipList)
    {
      for (Location newLoc = 
          new Location(curShip.getCurrentLocation().getX(), curShip.getCurrentLocation().getY()),
          endLoc = newLoc.add(curShip.getSize()); 
          !newLoc.compareLoc(endLoc); 
          newLoc.incrementX())
      {
        if (newLoc.compareLoc(curLoc))
        {
          return curShip;
        }  
      }
    }
    throw new Exception("No ship there");
  }
  
//---------------------------------------------------------------
  public Ship getOpponentShip(Location curLoc) throws Exception
  {
	  for (Ship curShip : opponentShipList)
	  {
	    for (Location newLoc = 
	        new Location(curShip.getCurrentLocation().getX(), curShip.getCurrentLocation().getY()),
	        endLoc = newLoc.add(curShip.getSize()); 
	        !newLoc.compareLoc(endLoc); 
	        newLoc.incrementX())
	    {
	      if (newLoc.compareLoc(curLoc)){
	        return curShip;
	      }  
	    }
	  }
	  throw new Exception("No ship there");
  }
  
//---------------------------------------------------------------  
  public Ship getOpponentShip(int id){
	  for(Ship curShip : opponentShipList){
		  if(curShip.getID() == id){
			  return curShip;
		  }
	  }
	  // should never reach here
	  return null;
  }

//---------------------------------------------------------------  
  //in case of things other than ships that occupy locations
  //and maybe to check if an attack was successful (TODO: confirm its a ship)
  public boolean isLocOccupied(Location curLoc)
  {
    for (Ship curShip : shipList)
    {
      if (curShip.getCurrentLocation().compareLoc(curLoc))
      {
        return true;
      }



    }

    for (Ship curShip : opponentShipList)
    {
      if (curShip.getCurrentLocation().compareLoc(curLoc))
      {
        return true;
      }
    }

    return false;
  }

//---------------------------------------------------------------  
  //TODO: replace raw adds with addShip
  /*public void resetBoard()
  {
    shipList.clear();
    for(int i = 0; i < 2; ++i)
    {
      shipList.add(new PatrolBoat());
      shipList.add(new Submarine());
      shipList.add(new Destroyer());
      shipList.add(new Battleship());
      shipList.add(new AircraftCarrier());
      shipList.add(new Dreadnought());
    }
  }*/

//---------------------------------------------------------------  
  //helper to set up ship locations initially
  public void addShip(Ship curShip, Location curLoc)
  {
    curShip.setCurrentLocation(curLoc);
    shipList.add(curShip);
  }
  
//---------------------------------------------------------------  
  public void addShip(Ship curShip)
  {
    shipList.add(curShip);
  }
  
//---------------------------------------------------------------  
  public void resetShips(){
	  shipList.clear();
  }

//---------------------------------------------------------------  
  public ArrayList<Ship> getShips(){
	  return shipList;
  }

//---------------------------------------------------------------  
  public boolean shipIsEmpty(){
	  return shipList.isEmpty();
  }

//---------------------------------------------------------------  
  public void addOpponentShip(Ship shipIn){
	  opponentShipList.add(shipIn);
  }

//---------------------------------------------------------------  
  public ArrayList<Ship> getOpponentShips(){
	  return opponentShipList;
  }

//---------------------------------------------------------------  
  public boolean opponentShipIsEmpty(){
	  return opponentShipList.isEmpty();
  }

  public boolean isAttacklocationInRange(Ship ship, Location location)
  {
        return true;
  }

  public boolean isMoveValid(Ship ship, Location location)
  {
    int size = ship.getSize();
    int y = location.getY();
    int x = location.getX();
    int yRange;
    int xRange = yRange = ship.getSpeed();
    Location basePoint = ship.getCurrentLocation();

    if(size == 3)
    {
      //compensate for aircraft carrier.
      location.decrementX();
      --x;
    }

    for(int i = 0; i < size; ++i)
    {
      if(((x + i) < 0) || (y < 0) || ((x + i) > 22) || (y > 19))
      {
        return false;
      }

      try
      {
        Ship checkShip = getShip(location);
        if(checkShip.getID() != ship.getID())
        {
        return false;
        }
      }

      catch (Exception exception)
      {
        
      }

    }

    for(int xx = 0; xx <= xRange; ++xx)
    {
      for(int yy = 0; yy <= yRange; ++yy)
      {
        Location checkUpLoc = new Location(basePoint.getX() + xx, basePoint.getY() + yy);
        Location checkDownLoc = new Location(basePoint.getX() + xx, basePoint.getY() - yy);
        Location checkUpBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() + yy);
        Location checkDownBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() - yy);

        if(location.compareLoc(checkUpLoc) || location.compareLoc(checkDownLoc)
            || location.compareLoc(checkUpBackLoc) || location.compareLoc(checkDownBackLoc))
        {
        	if(ship.getSize() == 1){
        		if(!isLocOccupied(location)){
        			return true;
        		}
        	} else if(ship.getSize() == 2){
        		Location upLoc = new Location(location.getX() + 1, location.getY());
        		if(!isLocOccupied(location) && !isLocOccupied(upLoc)){
        			return true;
        		}
        	} else if(ship.getSize() == 3){
        		Location upOneLoc = new Location(location.getX() + 1, location.getY());
        		Location upTwoLoc = new Location(location.getX() + 2, location.getY());
        		if(!isLocOccupied(location) && !isLocOccupied(upOneLoc) && !isLocOccupied(upTwoLoc)){
        			return true;
        		}
        	}
        }
      }
      --yRange;
    }

    return false;
  }

  public boolean moveShip(Ship ship, Location location)
  {
    if(isMoveValid(ship, location))
    {
      System.out.println("valid move!");
      ship.setCurrentLocation(location);
      return true;
    }
    //error message when a ship's move in invalid
    JOptionPane frame = new JOptionPane();
    JOptionPane.showMessageDialog(frame,
        "ERROR: Invalid Move! Please Choose a New Location in the Green Highlighted Tiles",
        "Move Error",
        JOptionPane.ERROR_MESSAGE);
   
    return false;

  }

  public void attack()
  {

  }
  
  
}
