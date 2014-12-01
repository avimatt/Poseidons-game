package eecs285.proj4.group.Graphics;

//import com.sun.prism.paint.*;
import eecs285.proj4.group.Board;
import eecs285.proj4.group.Location;
import eecs285.proj4.group.Player;
import eecs285.proj4.group.Ships.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends BufferedImage{
  private int width, height;
  public int[] pixels;

  private Graphics g;
  
  private Ship panelSelectedShip;
  @SuppressWarnings("unused")
  private Ship moveShip;
  private boolean move = false;
  private boolean attack = false;
  
  Ship patrol1 = new PatrolBoat();
  Ship destroyer = new Destroyer();
  Ship battleship = new Battleship();
  Ship submarine = new Submarine();
  Ship patrol2 = new PatrolBoat();
  Ship aircraft = new AircraftCarrier();

  //---------------------------------------------------------------
  public Screen(int inWidth, int inHeight)
  {
    super(inWidth, inHeight, BufferedImage.TYPE_INT_RGB);
    width = inWidth;
    height =inHeight;
    pixels = ((DataBufferInt)this.getRaster().getDataBuffer()).getData();

    g = getGraphics();
    panelSelectedShip = null;
    
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
    
    if(move && panelSelectedShip != null){
    	renderMovementRange(panelSelectedShip);
    }
    
    if(attack && panelSelectedShip != null){
    	renderAttackRange(panelSelectedShip);
    }

    if(!player.getBoard().opponentShipIsEmpty()){
      //System.out.println("opponentShips is not empty");
    }
  }
  
//---------------------------------------------------------------
  
  public void setPanelSelectedShip(Ship selectedShip){
	  panelSelectedShip = selectedShip;
  }

  public Ship getPanelSelecetedShip()
  {
    return panelSelectedShip;
  }
  
//---------------------------------------------------------------
  
  public void setMove(boolean moveIn){
	  move = moveIn;

    if(move)
    {
      moveShip = panelSelectedShip;
    }

  }
  
//---------------------------------------------------------------
  
  public void setAttack(boolean attackIn){
	  attack = attackIn;
  }
  
//---------------------------------------------------------------
  public boolean getAttackSelected(){
	  return attack;
  }
  
//---------------------------------------------------------------
  public boolean getMoveSelected(){
	  return move;
  }

//---------------------------------------------------------------
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

//---------------------------------------------------------------  
  /**
   * Makes the tiles surrounding the ship visible
   *
   * @param ship
   */
  public void renderVisibility(Ship ship, Board board)
  {
//    g.setColor(Color.BLUE);
//    g.fillOval((ship.getCurrentLocation().getX() - xRange) * 16, (ship.getCurrentLocation().getY() - yRange) * 16, 2 * xRange * 16, 2 * yRange * 16);

    if(ship.getHealth() == 0)
    {
      return;
    }

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
          Ship potential = board.getShip(checkUpLoc);
          if(potential != null)
          {

            render(potential, 0, 0);
          }
          else
          {
        		Ship potentialEnemy = board.getOpponentShip(checkUpLoc);
            if(potentialEnemy != null)
              render(potentialEnemy, 0, 0);

            else {
        		g.drawImage(Sprite.OCEANTILE, checkUpLoc.getX() * Sprite.getSPRITESIZE(), checkUpLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
            }
          }
          potential = board.getShip(checkDownLoc);
          if(potential != null)
          {
            render(potential, 0, 0);
          }
          else
          {
        		Ship potentialEnemy = board.getOpponentShip(checkDownLoc);
            if(potentialEnemy != null)
            {
              render(potentialEnemy, 0, 0);
          	}
            else
            {
          		g.drawImage(Sprite.OCEANTILE, checkDownLoc.getX() * Sprite.getSPRITESIZE(), checkDownLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}
          }
          potential = board.getShip(checkUpBackLoc);
          if(potential != null)
          {
            render(potential, 0, 0);
          }
          else
          {
            Ship potentialEnemy = board.getOpponentShip(checkUpBackLoc);
            if(potentialEnemy != null)
            {
              render(potentialEnemy, 0, 0);
            }
            else
            {
          		g.drawImage(Sprite.OCEANTILE, checkUpBackLoc.getX() * Sprite.getSPRITESIZE(), checkUpBackLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}
          }
          potential = board.getShip(checkDownBackLoc);
          if(potential != null)
          {
            render(potential, 0, 0);
          }
          else
          {
            Ship potentialEnemy = board.getOpponentShip(checkDownBackLoc);
            if(potentialEnemy != null)
            {
              render(potentialEnemy, 0, 0);
          	} else
            {
          		g.drawImage(Sprite.OCEANTILE, checkDownBackLoc.getX() * Sprite.getSPRITESIZE(), checkDownBackLoc.getY() * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), null);
          	}          
          }
        }
        yRange -= 1;
      }
    }
  }

//---------------------------------------------------------------
  public void renderMovementRange(Ship ship)
  {
    int xRange = ship.getSpeed();
    int yRange = xRange;
    Location shipLoc = ship.getCurrentLocation();
    Location loc = new Location(shipLoc.getX(), shipLoc.getY());

    if(ship.getSize() == 3)
    {
      loc.incrementX();
    }

      for (int x = 0; x <= xRange; ++x)
      {
        for (int y = 0; y <= yRange; ++y)
        {
          int xPix = (x + loc.getX()) * Sprite.getSPRITESIZE();
          int negXPix = (-x + loc.getX()) * Sprite.getSPRITESIZE();
          int yPix = (y + loc.getY()) * Sprite.getSPRITESIZE();
          int negYPix = (-y + loc.getY()) * Sprite.getSPRITESIZE();
          g.setColor(Color.GREEN);
          g.drawRect(xPix , yPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
          g.drawRect(negXPix , yPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
          g.drawRect(xPix , negYPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
          g.drawRect(negXPix , negYPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
        }
        --yRange;
      }
  }

//---------------------------------------------------------------  
  public void renderAttackRange(Ship ship)
  {
    int xRange = ship.getAttackRadius();
    int yRange;
    Location loc = ship.getCurrentLocation();

    for(int i = 0; i < ship.getSize(); ++i)
    {
      yRange = xRange;

      for (int x = 0; x <= xRange; ++x)
      {
        for (int y = 0; y <= yRange; ++y)
        {
          int xPix = (x + i + loc.getX()) * Sprite.getSPRITESIZE();
          int negXPix = (-x + i + loc.getX()) * Sprite.getSPRITESIZE();
          int yPix = (y + loc.getY()) * Sprite.getSPRITESIZE();
          int negYPix = (-y + loc.getY()) * Sprite.getSPRITESIZE();
          g.setColor(Color.YELLOW);
          g.drawRect(xPix , yPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
         // g.setColor(Color.YELLOW);
          g.drawRect(negXPix , yPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
          g.drawRect(xPix , negYPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
          g.drawRect(negXPix , negYPix, Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
        }
        --yRange;
      }
    }

  }

//---------------------------------------------------------------
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

    patrol1.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 0));
    destroyer.setCurrentLocation(new Location(Math.abs(twoOffset - 0), 1));
    battleship.setCurrentLocation(new Location(Math.abs(twoOffset - 0), 2));
    aircraft.setCurrentLocation(new Location(Math.abs(threeOffset - 0), 3));
    submarine.setCurrentLocation(new Location(Math.abs(oneOffset - 1), 4));
    patrol2.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 5));

    player.getBoard().addShip(patrol1);
    player.getBoard().addShip(patrol2);
    player.getBoard().addShip(destroyer);
    player.getBoard().addShip(battleship);
    player.getBoard().addShip(aircraft);
    player.getBoard().addShip(submarine);

    render(player);
  }

//---------------------------------------------------------------  
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
    

    patrol1.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 19));
    destroyer.setCurrentLocation(new Location(Math.abs(twoOffset - 0), 18));
    battleship.setCurrentLocation(new Location(Math.abs(twoOffset - 0), 17));
    aircraft.setCurrentLocation(new Location(Math.abs(threeOffset - 0), 16));
    submarine.setCurrentLocation(new Location(Math.abs(oneOffset - 1), 15));
    patrol2.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 14));

    player.getBoard().addShip(patrol1);
    player.getBoard().addShip(patrol2);
    player.getBoard().addShip(destroyer);
    player.getBoard().addShip(battleship);
    player.getBoard().addShip(aircraft);
    player.getBoard().addShip(submarine);

    render(player);
  }

//---------------------------------------------------------------  
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
    
    patrol1.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 1));
    destroyer.setCurrentLocation(new Location(Math.abs(twoOffset - 1), 11));
    battleship.setCurrentLocation(new Location(Math.abs(twoOffset - 0), 14));
    aircraft.setCurrentLocation(new Location(Math.abs(threeOffset - 0), 4));
    submarine.setCurrentLocation(new Location(Math.abs(oneOffset - 1), 16));
    patrol2.setCurrentLocation(new Location(Math.abs(oneOffset - 2), 7));

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

//---------------------------------------------------------------  
  public int getWidth()
  {
    return width;
  }

//---------------------------------------------------------------  
  public int getHeight()
  {
    return height;
  }

}