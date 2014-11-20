package eecs285.proj4.group;

public class Location
{
  private double x;
  private double y;

  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public Location(double inX, double inY)
  {
    x = inX;
    y = inY;
  }

  // ---------------------------------------------------------------------------

  public double getX()
  {
    return x;
  }

  // ---------------------------------------------------------------------------

  public double getY()
  {
    return y;
  }
  
  public boolean compareLoc(Location inLoc){
    return (inLoc.getX() == x && inLoc.getY() == y);
  }
  
}