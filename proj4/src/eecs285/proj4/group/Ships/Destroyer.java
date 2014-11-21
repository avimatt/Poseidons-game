package eecs285.proj4.group.Ships;

public class Destroyer extends Ship
{
  private int health;

  public Destroyer(){
    super();
    health = 65;
  }
  
  @Override
  public int getSpeed()
  {
    return 4;
  }

  @Override
  public int getAttackPower()
  {
    return 7;
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
    return 5;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 20;
  }

  @Override
  public int getSize()
  {
    return 3;
  }

}