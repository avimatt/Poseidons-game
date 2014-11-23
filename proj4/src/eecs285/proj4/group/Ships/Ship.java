package eecs285.proj4.group.Ships;

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
  
  public int getID(){
    return id;
  }
  
  public Location getCurrentLocation(){
    return currentLoc;
  }
  
  public void setCurrentLoaction(Location newLoc)
  {
    currentLoc = newLoc;
  }
  
  public abstract int getSpeed();
  
  public abstract int getAttackPower();
  
  public abstract int getHealth();
  
  public abstract void setHealth(int healthIn);
  
  public abstract int getVisibilityRadius();
  
  public abstract int getAttackRadius();
  
  public abstract int getSize();
  
  public abstract String getShipType();
  
}
