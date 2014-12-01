package eecs285.proj4.group.Ships;

import eecs285.proj4.group.Graphics.Sprite;

public class Battleship extends Ship
{
  private int health;
  private static final int ORIGINALHEALTH = 60;

  public Battleship(){
    super();
    health = ORIGINALHEALTH;
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
  public int getInitialHealth(){
	  return 60;
  }
  
  @Override
  public int getHealth()
  {
    return health;

  }

  @Override
  public void setHealth(int healthIn){
    health = healthIn;
    if(health < 0)
    {
      health = 0;
    }
  }
  
  
  // Radius from the center of the ship determined by the Location
  @Override
  public int getVisibilityRadius(){
    return 3;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 4;
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
    if(health <= 0)
    {
      return Sprite.TWOTILEGRAVEYARD;
    }
    return Sprite.BATTLESHIP;
  }

  @Override
  public int getOriginalHealth()
  {
    return ORIGINALHEALTH;
  }


}