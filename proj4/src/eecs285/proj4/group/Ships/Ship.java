package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Sprite;
import eecs285.proj4.group.Location;

public abstract class Ship
{
  private static int nextID = 0;
  private int id;
  private Location currentLoc;
  
  public Ship()
  {
    id = nextID++;    
  }
  
//---------------------------------------------------------------
  public int getID(){
    return id;
  }

//---------------------------------------------------------------
  // only to be used for the opponents ships
  public void setID(int idIn){
	  id = idIn;
  }
 
//---------------------------------------------------------------
  public Location getCurrentLocation(){
    return currentLoc;
  }
  
//---------------------------------------------------------------
  public void setCurrentLoaction(Location newLoc)
  {
    currentLoc = newLoc;
  }
  
//---------------------------------------------------------------
  public void takeHit(int attackPower)
  {
    setHealth(getHealth() - attackPower);
  }
  
//---------------------------------------------------------------
  public static Ship shipFactory(String shipName){
	  if (shipName.equals("Patrol Boat")) {
		  return new PatrolBoat();
	  } else if (shipName.equals("Submarine")) {
		  return new Submarine();
	  } else if (shipName.equals("Destroyer")) {
		  return new Destroyer();
	  } else if (shipName.equals("Battleship")) {
		  return new Battleship();
	  } else if (shipName.equals("Aircraft Carrier")) {
		  return new AircraftCarrier();
	  } else {
		  // throw exception
	  }
	  // should never reach here  
	  return null;
  }
  
//---------------------------------------------------------------
  public abstract int getSpeed();
  
  public abstract int getAttackPower();

  public abstract int getInitialHealth();
  
  public abstract int getHealth();
  
  public abstract void setHealth(int healthIn);
  
  public abstract int getVisibilityRadius();
  
  public abstract int getAttackRadius();
  
  public abstract int getSize();
  
  public abstract String getShipType();

  public abstract Sprite getSprite();
  
}
