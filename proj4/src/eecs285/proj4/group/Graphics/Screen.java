package eecs285.proj4.group.Graphics;

import eecs285.proj4.group.Board;
import eecs285.proj4.group.Location;
import eecs285.proj4.group.Player;
import eecs285.proj4.group.Ships.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.*;

/**
 * Created by yossier on 11/23/14.
 */
public class Screen extends BufferedImage{
  private int width, height;
  public int[] pixels;
  public int[] tiles;

  private Random random = new Random();
  private Graphics g;

  //---------------------------------------------------------------
  public Screen(int inWidth, int inHeight)
  {
    super(inWidth, inHeight, BufferedImage.TYPE_INT_RGB);
    width = inWidth;
    height =inHeight;
    pixels = ((DataBufferInt)this.getRaster().getDataBuffer()).getData();

    g = getGraphics();

    tiles = new int[64 * 64];

    for( int i = 0; i < tiles.length; ++i)
    {
      tiles[i] = random.nextInt(0xffffff);
    }

  }

  //---------------------------------------------------------------
  public void render(Player player)
  {
    for (int y = 0; y < height; y += Sprite.FOGTILE.getHEIGHT())
    {

      for (int x = 0; x < width; x += Sprite.FOGTILE.getWIDTH())
      {
        g.drawImage(Sprite.FOGTILE, x, y, Sprite.FOGTILE.getWIDTH(), Sprite.FOGTILE.getHEIGHT(), null);
      }
    }

    if(!player.getBoard().shipIsEmpty()){
      for(Ship curShip : player.getBoard().getShips()){
        render(curShip, 0, 0);
        renderVisibility(curShip, player.getBoard());
      }
    }

    if(!player.getBoard().opponentShipIsEmpty()){
      System.out.println("opponentShips is not empty");
      /*for(Ship curShip : player.getBoard().getOpponentShips()){
        render(curShip, 0, 0);
      }*/
    }
    //Ship test = new Submarine();
    //test.setCurrentLoaction(new Location(3,2));

    //render(test);
  }


  /**
   *
   * Offsets will be used to animate movement, when we are up to it
   *
   * @param ship
   * @param xOffset
   * @param yOffset
   */
  public void render(Ship ship, int xOffset, int yOffset){
    Location shipLoc = ship.getCurrentLocation();
    Sprite shipSprite = ship.getSprite();


    int xStart = shipLoc.getX() * Sprite.getSPRITESIZE();
    int yStart = shipLoc.getY() * Sprite.getSPRITESIZE();

    int xWidth = shipSprite.getWIDTH();
    int yHeight = shipSprite.getHEIGHT();

    g.drawImage(shipSprite, xStart + xOffset, yStart + yOffset, xWidth, yHeight, null);

  }

  /**
   * Makes the tiles surrounding the ship visible
   *
   *
   *
   * @param ship
   */
  // there is a problem with the showing visibility of enemy ships 
  // b/c it will show the whole ship if even one part is in the range
  public void renderVisibility(Ship ship, Board board)
  {
//    g.setColor(Color.BLUE);
//    g.fillOval((ship.getCurrentLocation().getX() - xRange) * 16, (ship.getCurrentLocation().getY() - yRange) * 16, 2 * xRange * 16, 2 * yRange * 16);

    for(int s = 0; s < ship.getSize(); ++s)
    {
      Location basePoint = new Location(ship.getCurrentLocation().getX() + s, ship.getCurrentLocation().getY());
      int yRange;
      int xRange = yRange = ship.getVisibilityRadius();
      for(int x = 0; x <= xRange; ++x)
      {
        for(int y = 0; y <= yRange; ++y)
        {
          Location checkUpLoc = new Location(basePoint.getX() + x, basePoint.getY() + y);
          Location checkDownLoc = new Location(basePoint.getX() + x, basePoint.getY() - y);

          Location checkUpBackLoc = new Location(basePoint.getX() - x, basePoint.getY() + y);
          Location checkDownBackLoc = new Location(basePoint.getX() - x, basePoint.getY() - y);

          // Check upwards logic
          try
          {
            Ship potential = board.getShip(checkUpLoc);
            render(potential, 0, 0);
          }
          catch (Exception exception)
          {
        	try {
        		Ship potentialEnemy = board.getOpponentShip(checkUpLoc);
        		render(potentialEnemy, 0, 0);
        	} catch (Exception e){
        		g.drawImage(Sprite.OCEANTILE, checkUpLoc.getX() * Sprite.getSPRITESIZE(), checkUpLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
        	}
          }
          try
          {
            Ship potential = board.getShip(checkDownLoc);
            render(potential, 0, 0);
          }
          catch (Exception exception)
          {
        	try {
        		Ship potentialEnemy = board.getOpponentShip(checkDownLoc);
          		render(potentialEnemy, 0, 0);
          	} catch (Exception e){
          		g.drawImage(Sprite.OCEANTILE, checkDownLoc.getX() * Sprite.getSPRITESIZE(), checkDownLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}
          }
          try
          {
            Ship potential = board.getShip(checkUpBackLoc);
            render(potential, 0, 0);
          }
          catch (Exception exception)
          {
        	try {
          		Ship potentialEnemy = board.getOpponentShip(checkUpBackLoc);
          		render(potentialEnemy, 0, 0);
          	} catch (Exception e){
          		g.drawImage(Sprite.OCEANTILE, checkUpBackLoc.getX() * Sprite.getSPRITESIZE(), checkUpBackLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}          
          }
          try
          {
            Ship potential = board.getShip(checkDownBackLoc);
            render(potential, 0, 0);
          }
          catch (Exception exception)
          {
        	try {
          		Ship potentialEnemy = board.getOpponentShip(checkDownBackLoc);
          		render(potentialEnemy, 0, 0);
          	} catch (Exception e){
          		g.drawImage(Sprite.OCEANTILE, checkDownBackLoc.getX() * Sprite.getSPRITESIZE(), checkDownBackLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}          
          }
        }
        yRange -= 1;
      }
    }
  }


  public void renderOptionA(Player player, boolean server){
    player.getBoard().resetShips();
    int oneOffset;
    int twoOffset;
    int threeOffset;
    if(server){
    	oneOffset = 0; 
    	twoOffset = 0;
    	threeOffset = 0;
    } else {
    	oneOffset = 22;
    	twoOffset = 21;
    	threeOffset = 20;
    }

    Ship patrol1 = new PatrolBoat();
    patrol1.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),0));
    Ship destroyer = new Destroyer();
    destroyer.setCurrentLoaction(new Location(Math.abs(twoOffset - 0),1));
    Ship battleship = new Battleship();
    battleship.setCurrentLoaction(new Location(Math.abs(twoOffset - 0),2));
    Ship aircraft = new AircraftCarrier();
    aircraft.setCurrentLoaction(new Location(Math.abs(threeOffset - 0),3));
    Ship submarine = new Submarine();
    submarine.setCurrentLoaction(new Location(Math.abs(oneOffset - 1),4));
    Ship patrol2 = new PatrolBoat();
    patrol2.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),5));

    player.getBoard().addShip(patrol1);
    player.getBoard().addShip(patrol2);
    player.getBoard().addShip(destroyer);
    player.getBoard().addShip(battleship);
    player.getBoard().addShip(aircraft);
    player.getBoard().addShip(submarine);

    render(player);
  }

  public void renderOptionB(Player player, boolean server){
    player.getBoard().resetShips();
    int oneOffset;
    int twoOffset;
    int threeOffset;
    if(server){
    	oneOffset = 0; 
    	twoOffset = 0;
    	threeOffset = 0;
    } else {
    	oneOffset = 22;
    	twoOffset = 21;
    	threeOffset = 20;
    }
    
    // testing visibility of enemy ships
    // add these line and have one game use setup B and the other use C
    // Also there is a problem with the showing visibility of enemy ships 
    // b/c it will show the whole ship if even one part is in the range
    //Ship ship1 = new Destroyer();
    //ship1.setCurrentLoaction(new Location(7,7));
    //player.getBoard().addShip(ship1);
    
    Ship patrol1 = new PatrolBoat();
    patrol1.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),19));
    Ship destroyer = new Destroyer();
    destroyer.setCurrentLoaction(new Location(Math.abs(twoOffset - 0),18));
    Ship battleship = new Battleship();
    battleship.setCurrentLoaction(new Location(Math.abs(twoOffset - 0),17));
    Ship aircraft = new AircraftCarrier();
    aircraft.setCurrentLoaction(new Location(Math.abs(threeOffset - 0),16));
    Ship submarine = new Submarine();
    submarine.setCurrentLoaction(new Location(Math.abs(oneOffset - 1),15));
    Ship patrol2 = new PatrolBoat();
    patrol2.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),14));

    player.getBoard().addShip(patrol1);
    player.getBoard().addShip(patrol2);
    player.getBoard().addShip(destroyer);
    player.getBoard().addShip(battleship);
    player.getBoard().addShip(aircraft);
    player.getBoard().addShip(submarine);

    render(player);
  }

  public void renderOptionC(Player player, boolean server){
    player.getBoard().resetShips();
    int oneOffset;
    int twoOffset;
    int threeOffset;
    if(server){
    	oneOffset = 0; 
    	twoOffset = 0;
    	threeOffset = 0;
    } else {
    	oneOffset = 22;
    	twoOffset = 21;
    	threeOffset = 20;
    }
    
    Ship patrol1 = new PatrolBoat();
    patrol1.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),1));
    Ship destroyer = new Destroyer();
    destroyer.setCurrentLoaction(new Location(Math.abs(twoOffset - 1),11));
    Ship battleship = new Battleship();
    battleship.setCurrentLoaction(new Location(Math.abs(twoOffset - 0),14));
    Ship aircraft = new AircraftCarrier();
    aircraft.setCurrentLoaction(new Location(Math.abs(threeOffset - 0),4));
    Ship submarine = new Submarine();
    submarine.setCurrentLoaction(new Location(Math.abs(oneOffset - 1),16));
    Ship patrol2 = new PatrolBoat();
    patrol2.setCurrentLoaction(new Location(Math.abs(oneOffset - 2),7));

    player.getBoard().addShip(patrol1);
    player.getBoard().addShip(patrol2);
    player.getBoard().addShip(destroyer);
    player.getBoard().addShip(battleship);
    player.getBoard().addShip(aircraft);
    player.getBoard().addShip(submarine);

    render(player);
  }

  //---------------------------------------------------------------
  public void clear()
  {
    for (int i = 0; i < pixels.length; ++i)
    {
      pixels[i] = 0x000000;
    }
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

}
