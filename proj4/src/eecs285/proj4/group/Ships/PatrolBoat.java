package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Sprite;

public class PatrolBoat extends Ship
{
  private int health;

  public PatrolBoat(){
    super();
    health = 75;
  }
  
  @Override
  public int getSpeed()
  {
    return 5;
  }

  @Override
  public int getAttackPower()
  {
    return 0;
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
    return 10;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 0;
  }

  @Override
  public int getSize()
  {
    return 1;
  }
  
  public String getShipType()
  {
    return "Patrol Boat";
  }

  @Override
  public Sprite getSprite() {
    return Sprite.PATROLBOAT;
  }

  @Override
  public int getInitialHealth() {
	return 75;
  }

}
