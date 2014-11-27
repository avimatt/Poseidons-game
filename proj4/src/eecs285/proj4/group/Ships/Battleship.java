package eecs285.proj4.group.Ships;

import eecs285.proj4.group.Graphics.Screen;
import eecs285.proj4.group.Graphics.Sprite;

public class Battleship extends Ship
{
  private int health;

  public Battleship(){
    super();
    health = 60;
  }
  
  @Override
  public int getSpeed()
  {
    return 2;
  }

  @Override
  public int getAttackPower()
  {
    return 8;
  }

  @Override
  public int getHealth()
  {
    return health;
  }

  @Override
  public void setHealth(int healthIn){
    health = healthIn;
  }
  
  
  // Radius from the center of the ship determined by the Location
  @Override
  public int getVisibilityRadius(){
    return 6;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 25;
  }

  @Override
  public int getSize()
  {
    return 2;
  }
  
  public String getShipType()
  {
    return "Battleship";
  }

  @Override
  public Sprite getSprite() {
    return Sprite.BATTLESHIP;
  }


}