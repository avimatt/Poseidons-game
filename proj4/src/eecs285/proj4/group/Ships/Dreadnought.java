package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Sprite;

public class Dreadnought extends Ship
{
  private int health;
  private static final int ORGINALHEALTH = 70;

  public Dreadnought(){
    super();
    health = ORGINALHEALTH;
  }
  
  @Override
  public int getSpeed()
  {
    return 3;
  }

  @Override
  public int getAttackPower()
  {
    return 6;
  }

  @Override
  public int getInitialHealth(){
	  return 70;
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
    return 7;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 10;
  }

  @Override
  public int getSize()
  {
    return 5;
  }
  
  public String getShipType()
  {
    return "Dreadnought";
  }

  @Override
  public Sprite getSprite() {
    return null;
  }

  @Override
  public int getOrginalHealth()
  {
    return ORGINALHEALTH;
  }

}
