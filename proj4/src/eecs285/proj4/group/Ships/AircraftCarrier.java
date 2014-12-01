package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Sprite;

public class AircraftCarrier extends Ship
{
  private int health;
  private static final int ORIGINALHEALTH = 50;

  public AircraftCarrier(){
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
    return 25;
  }

  @Override
  public int getInitialHealth(){
	  return 50;
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
    return 1;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 6;
  }

  @Override
  public int getSize()
  {
    return 3;
  }
  
  public String getShipType()
  {
    return "Aircraft Carrier";
  }

  @Override
  public Sprite getSprite() {
    if(health <= 0)
    {
      return Sprite.THREETILEGRAVEYARD;
    }
    return Sprite.AIRCRAFTCARRIER;
  }

  @Override
  public int getOriginalHealth()
  {
    return ORIGINALHEALTH;
  }

}