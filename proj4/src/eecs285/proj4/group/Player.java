package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

public class Player
{
    private Ship[] playerShips;
    // array to contain players ship.
    private int damage_Dealt;
    private static int ID = 0;

    public Player()
    {
        ID++;

        playerShips = new Ship[6];
        playerShips[0] = new PatrolBoat();
        playerShips[1] = new Submarine();
        playerShips[2] = new Destroyer();
        playerShips[3] = new Battleship();
        playerShips[4] = new AircraftCarrier();
        playerShips[5] = new Dreadnought();
    }

    public Ship playerShip(int shipNumber)
    {
        //throws out of index reference
        return playerShips[shipNumber];
    }


}
