package eecs285.proj4.group;

public class Location
{
  private int x;
  private int y;


  //Tile Location List

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
  
  //---------------------------------------------------------------
  public boolean compareLoc(Location inLoc){
    return (inLoc.getX() == x && inLoc.getY() == y);
  }
  
  //---------------------------------------------------------------
  public Location add(int numTiles)
  {
    Location newLoc = new Location(this.x, this.y);
    for (int i = 0; i < numTiles; ++i)
    {
      newLoc.incrementX();
    }
    return newLoc;
  }
  
  //---------------------------------------------------------------
  public void incrementX()
  {
    ++x;
  }
  
}