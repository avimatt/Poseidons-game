/**
 * 
 */
package eecs285.proj4.group;

import eecs285.proj4.group.Ships.Ship;
import java.util.ArrayList;

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
  
  
}
