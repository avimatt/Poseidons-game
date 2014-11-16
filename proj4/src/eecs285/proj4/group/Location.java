package eecs285.proj4.group;

public class Location
{
  private int x;
  private int y;

  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------

  public Location(int inX, int inY)
  {
    x = inX;
    y = inY;
  }

  // ---------------------------------------------------------------------------

  public int getX()
  {
    return x;
  }

  // ---------------------------------------------------------------------------

  public int getY()
  {
    return y;
  }
  
  public boolean compareLoc(Location inLoc){
    return (inLoc.getX() == x && inLoc.getY() == y);
  }
  
}