package eecs285.proj4.group.Ships;

import java.util.ArrayList;

import eecs285.proj4.group.Board;
import eecs285.proj4.group.Player;

public class TotalHealth
{
  Board board = new Board();
  private ArrayList<Ship> shipList;
  //int health = 0;
  
  
  
  public TotalHealth()
  {
    shipList = new ArrayList<Ship>();
    
  }
  
//---------------------------------------------------------------
  public int getTotalFleetHealth()
  {
    return 325; 
  }
  
//---------------------------------------------------------------
  public int getFleetHealth(Player play)
  {
	int health = 0;
    shipList = play.getBoard().getShipArray();
    for(Ship currentShip: shipList)
    {
      health = currentShip.getHealth() + health;
    }
    return health;
  }
  
}
