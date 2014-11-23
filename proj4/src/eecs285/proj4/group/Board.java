/**
 * 
 */
package eecs285.proj4.group;

import eecs285.proj4.group.Ships.AircraftCarrier;
import eecs285.proj4.group.Ships.Battleship;
import eecs285.proj4.group.Ships.Destroyer;
import eecs285.proj4.group.Ships.Dreadnought;
import eecs285.proj4.group.Ships.PatrolBoat;
import eecs285.proj4.group.Ships.Ship;
import eecs285.proj4.group.Ships.Submarine;

import java.util.ArrayList;

/**
 * @author agambatra and akanarek
 *
 */
public class Board
{

  private ArrayList<Ship> shipList;

  private Ship[] playerShips;
  private Ship[] opponentShips;
  
  
  /**
   * 
   */
  public Board()
  {
    for(int i = 0; i < 2; ++i)
    {
      shipList.add(new PatrolBoat());
      shipList.add(new Submarine());
      shipList.add(new Destroyer());
      shipList.add(new Battleship());
      shipList.add(new AircraftCarrier());
      shipList.add(new Dreadnought());
    }
    /*
    playerShips = new Ship[6];
    playerShips[0] = new PatrolBoat();
    playerShips[1] = new Submarine();
    playerShips[2] = new Destroyer();
    playerShips[3] = new Battleship();
    playerShips[4] = new AircraftCarrier();
    playerShips[5] = new Dreadnought();
    
    opponentShips = new Ship[6];
    opponentShips[0] = new PatrolBoat();
    opponentShips[1] = new Submarine();
    opponentShips[2] = new Destroyer();
    opponentShips[3] = new Battleship();
    opponentShips[4] = new AircraftCarrier();
    opponentShips[5] = new Dreadnought();
    */
  }


  public Ship getShip(int shipNumber)
  {
    return shipList.get(shipNumber);
  }
  
  public Ship getShip(Location curLoc)
  {
    for (Ship curShip : playerShips)
    {
      if (curShip.getCurrentLocation() == curLoc)
      {
        return curShip;
      }

      else
      {
        return null ;
      }
    }
    return null;
  }
  
  public void moveShip(Ship curShip, Location newLoc)
  {
    curShip.setCurrentLoaction(newLoc);
  }

}
