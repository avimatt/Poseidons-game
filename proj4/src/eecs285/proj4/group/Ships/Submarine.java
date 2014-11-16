package eecs285.proj4.group.Ships;

public class Submarine extends Ship
{
  private int health;

  public Submarine(){
    super();
    // health = TBD;
  }
  
  @Override
  public int getSpeed()
  {
    // Choose a value for speed and put it here
    return 0;
  }

  @Override
  public int getAttackPower()
  {
    // Choose a value for AttackPower and put it here
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
    // Choose a value for visibilityRadius and put it here
    return 0;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    // Choose a value for attackRadius and put it here
    return 0;
  }

  @Override
  public int getSize()
  {
    return 1;
  }

}