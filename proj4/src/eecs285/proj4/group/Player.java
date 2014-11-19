package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

public class Player
{
    // array to contain players ship.
    private int damage_Dealt;
    private static int ID = 0;
    private Board myBoard;

    public Player()
    {
        ID++;
        myBoard = new Board();
    }

    public Ship playerShip(int shipNumber)
    {
        //throws out of index reference
        return myBoard.getShip(shipNumber);
    }
    
    public void moveShip(Ship ship, Location location)
    {
      ship.setCurrentLoaction(location);
    }

}
