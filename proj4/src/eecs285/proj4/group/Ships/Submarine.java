package eecs285.proj4.group.Ships;

import eecs285.proj4.group.Graphics.Sprite;

public class Submarine extends Ship

{
  private int health;
  private static final int ORIGINALHEALTH = 60;

  public Submarine(){
    super();
    health = ORIGINALHEALTH;
  }
  
  @Override
  public int getSpeed()
  {
    return 5;
  }

  @Override
  public int getAttackPower()
  {
    return 10;
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
    return 2;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 3;
  }

  @Override
  public int getSize()
  {
    return 1;
  }
  
  public String getShipType()
  {
    return "Submarine";
  }

  @Override
  public Sprite getSprite() {
    if(health <= 0)
    {
      return Sprite.ONETILEGRAVEYARD;
    }
    return Sprite.SUBMARINE;
  }

  @Override
  public int getOriginalHealth()
  {
    return ORIGINALHEALTH;
  }

}