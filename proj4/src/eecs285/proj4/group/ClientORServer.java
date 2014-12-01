package eecs285.proj4.group;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import eecs285.proj4.group.Ships.Ship;

public class ClientORServer {
	private String ipAddress;
	private int portNum;
	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;

//---------------------------------------------------------------
	public ClientORServer(String inIpAddress, int inPortNum){
		ipAddress = inIpAddress;
		portNum = inPortNum; 
		socket = null;
		output = null;
		input = null;
	}
	
//---------------------------------------------------------------	
	public static String getIpAddress(){
		try{
			InetAddress address;
			address = InetAddress.getLocalHost();
			return address.getHostAddress();
		} catch(Exception e){
			System.out.println("problem finding localhost");
			System.exit(10);
		}
		// will never get here
		return "";
	}
	
//---------------------------------------------------------------
	public int getPortNum(){
		return portNum;
	}
	
//---------------------------------------------------------------	
	public void startServer(){
		ServerSocket serverSock;
		
		try{
			System.out.println("Starting server");
			// connects the server to a specific port
			serverSock = new ServerSocket(portNum);
			// wait for someone to connect
			System.out.println("waiting for client...");
			socket = serverSock.accept();
			System.out.println("connected to a client, YAY!");
			// initialize data streams
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			
		} catch (IOException e){
			// server unable to connect 
			e.printStackTrace();
			System.exit(7);
		}
	}
	
//---------------------------------------------------------------	
	public void startClient(){
		try {
			System.out.println("Starting Client");
			// connect with server
			socket = new Socket(ipAddress, portNum);
			// initialize data streams
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException ioe) {
			// Unable to connect 
			ioe.printStackTrace();
			System.exit(10);
		}
	}
	
//---------------------------------------------------------------	
	/**
	 * Sends the initial setup to the other player
	 * 
	 * @param startLocations
	 */
	public void sendStartLocations(ArrayList<Ship> startLocations, boolean server){
		try
		{
			System.out.println("sending of initial setup started...");
			output.writeBytes("start_location");
			output.writeByte(0);
			for(Ship curShip : startLocations){
				output.writeBytes(curShip.getShipType());
				output.writeByte(0);
				output.writeInt(curShip.getID());
				output.writeInt(curShip.getCurrentLocation().getX());
				output.writeInt(curShip.getCurrentLocation().getY());	
			}
			output.writeBoolean(server);
			System.out.println("sending done...");
		}
		catch (IOException e)
		{
			System.out.println("Caught IOException sending start locations");
			System.exit(-1);
		}
	}
	
//---------------------------------------------------------------
	/**
	 * Sends the ship id of the attacked ship and its new health
	 * - Assumes "attackedShip" health has been updated 
	 * 
	 * @param attackedShip
	 */
	public void sendAttackHit(Ship attackedShip){
		try{
			System.out.println("sending attack hit started...");
			output.writeBytes("attack_hit_action");
			output.writeByte(0);
			output.writeInt(attackedShip.getID());
			output.writeInt(attackedShip.getHealth());
		} catch (IOException e){
			System.out.println("Caught IOException sending attack hit");
			System.exit(-1);
		}
		
	}
//---------------------------------------------------------------
	/**
	 * Sends message to alert player he was attacked but it missed
	 */	
	public void sendAttackMiss(){
		try{
			System.out.println("sending attack miss started...");
			output.writeBytes("attack_miss_action");
			output.writeByte(0);
		} catch (IOException e){
			System.out.println("Caught IOException sending attack miss");
			System.exit(-1);
		}
	}
	
//---------------------------------------------------------------
	/**
	 * Sends the ship id of the moved ship and its new location
	 * - Assumes "movedShip" location has been updated
	 * 
	 * @param attackedShip
	 */
	public void sendShipMove(Ship movedShip){
		try{
			System.out.println("Sending ship move started...");
			output.writeBytes("move_ship");
			output.writeByte(0);
			output.writeInt(movedShip.getID());
			output.writeInt(movedShip.getCurrentLocation().getX());
			output.writeInt(movedShip.getCurrentLocation().getY());
		} catch (IOException e){
			System.out.println("Caught IOException sending move ship");
			System.exit(-1);
		}
		
	}
	
//---------------------------------------------------------------	
	public void sendEndTurn(){
		try{
			System.out.println("Sending end turn started...");
			output.writeBytes("end_turn");
			output.writeByte(0);
		} catch (IOException e){
			System.out.println("Caught IOException sending end turn");
			System.exit(-1);
		}
	}

//---------------------------------------------------------------	
	/**
	 * Reads all types of messages that can be sent
	 * - Returns 1 for everything besides end turn
	 * 
	 * @param game
	 */
	public boolean readMessage(GamePlay game){
		Vector< Byte > byteVec = new Vector< Byte >();
		byte [] byteAry;
		byte recByte;
		String receivedString = "";
		try
		{
			// Get the initial string which tells you the type 
			// of message being sent
			recByte = input.readByte();
			while (recByte != 0)
			{
				byteVec.add(recByte);
				recByte = input.readByte();
			}
			byteAry = new byte[byteVec.size()];
			for (int ind = 0; ind < byteVec.size(); ind++)
			{
				byteAry[ind] = byteVec.elementAt(ind).byteValue();
			}
			receivedString = new String(byteAry);
			
			System.out.println("recieved string: " + receivedString);
			
			// Message for sending the starting locations of other players boats
			if(receivedString.contentEquals("start_location")){
				for(int i = 0; i < 6; ++i){
					// Receive Ship Type
					byteVec.clear();
					recByte = input.readByte();
					while (recByte != 0){
						byteVec.add(recByte);
						recByte = input.readByte();
					}
					byteAry = new byte[byteVec.size()];
					for (int ind = 0; ind < byteVec.size(); ind++){
						byteAry[ind] = byteVec.elementAt(ind).byteValue();
					}
					receivedString = new String(byteAry);
					
					// create opponents ships
					Ship curShip = Ship.shipFactory(receivedString);
					curShip.setID(input.readInt() + 6);
					System.out.println(curShip.getShipType() + " has id: " + curShip.getID());
					curShip.setCurrentLocation(new Location(input.readInt(),input.readInt()));
					game.getPlayer().getBoard().addOpponentShip(curShip);
				}
				boolean isServer = input.readBoolean();
				if(isServer){
					game.getStatusPanel().setLog("Your opponent has finished setting up");
					game.getStatusPanel().setLog("It is your turn");
				} else {
					game.getStatusPanel().setLog("Your opponent has finished setting up");
					game.getStatusPanel().setLog("Please wait for them to go");
				}
								
				return true;
			}
			if(receivedString.contentEquals("attack_hit_action")){
				int x = input.readInt();
				System.out.println(x);
				Ship attackedShip = game.getPlayer().getBoard().getOpponentShip(x - 6);
			
		        if(attackedShip == null)
		        {
		          System.out.println("Attacked Ship is Null");
		        }

				int newShipHealth = input.readInt();
				int damageTaken = attackedShip.getHealth() - newShipHealth;
				attackedShip.setHealth(newShipHealth);
				
				if(newShipHealth == 0){
					game.getStatusPanel().setLog("Your " + attackedShip.getShipType() + " has been sunk");
				} else {
					game.getStatusPanel().setLog("Your " + attackedShip.getShipType() + " has been hit");
					game.getStatusPanel().setLog("    It lost " + damageTaken + " health");
				}
				// update the log to say the ship has been hit 
				
				return true;
			}
			if(receivedString.contentEquals("attack_miss_action")){
				// update the log to say that there was an attack and it missed
				game.getStatusPanel().setLog("You were attacked but the attack missed");
				
				return true;
			}
			if(receivedString.contentEquals("move_ship")){
				Ship movedShip = game.getPlayer().getBoard().getOpponentShip(input.readInt() + 6);
				Location newLoc = new Location(input.readInt(),input.readInt());
				
				movedShip.setCurrentLocation(newLoc);
				
				game.getBoardImage().updateBoard(game.getPlayer());
				// may want to re-render the board to show the moved ship immediately as 
				// opposed to the next time they hover their mouse over a tile
				
				return true;
			}
			if(receivedString.contentEquals("end_turn")){
				return false;
			}
		}
		catch (IOException ioe)
		{
			System.out.println("ERROR: receiving string from socket");
			System.exit(8);
		}
		return true;
	}
	
}
