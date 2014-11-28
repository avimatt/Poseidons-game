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
	public void sendStartLocations(ArrayList<Ship> startLocations){
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
	 * 
	 * @param attackedShip
	 */
	public void sendAttackHit(Ship attackedShip){
		try{
			System.out.println("sending attack hit started...");
			output.writeBytes("attack_hit_action");
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
		} catch (IOException e){
			System.out.println("Caught IOException sending attack miss");
			System.exit(-1);
		}
	}
	

//---------------------------------------------------------------	
	public void readMessage(GamePlay game){
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
					curShip.setID(input.readInt());
					curShip.setCurrentLoaction(new Location(input.readInt(),input.readInt()));
					game.getPlayer().getBoard().addOpponentShip(curShip);
				}
			}
			if(receivedString.contentEquals("attack_hit_action")){
				Ship attackedShip = game.getPlayer().getBoard().getOpponentShip(input.readInt());
				int newShipHealth = input.readInt();
				attackedShip.setHealth(newShipHealth);
				// update the log to say the ship has been hit 
			}
			if(receivedString.contentEquals("attack_miss_action")){
				// update the log to say that there was an attack and it missed
			}
		}
		catch (IOException ioe)
		{
			System.out.println("ERROR: receiving string from socket");
			System.exit(8);
		}

	}
	
}
