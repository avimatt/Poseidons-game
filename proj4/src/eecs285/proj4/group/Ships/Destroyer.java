package eecs285.proj4.group.Ships;

import eecs285.proj4.group.Graphics.Sprite;

public class Destroyer extends Ship
{
  private int health;
  private static final int ORIGINALHEALTH = 65;

  public Destroyer(){
    super();
    health = ORIGINALHEALTH;
  }
  
  @Override
  public int getSpeed()
  {
    return 3;
  }

  @Override
  public int getAttackPower()
  {
    return 7;
  }

  @Override
  public int getInitialHealth(){
	  return 65;
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
    return "Destroyer";
  }

  @Override
  public Sprite getSprite() {
    if(health <= 0)
    {
      return Sprite.TWOTILEGRAVEYARD;
    }
    return Sprite.DESTROYER;
  }

  @Override
  public int getOriginalHealth()
  {
    return ORIGINALHEALTH;
  }
}
