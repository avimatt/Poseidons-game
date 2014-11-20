package eecs285.proj4.group;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Vector;

public class ClientORServer {
	private String ipAddress;
	private int portNum;
	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	
	public ClientORServer(String inIpAddress, int inPortNum){
		ipAddress = inIpAddress;
		portNum = inPortNum; 
		socket = null;
		output = null;
		input = null;
	}
	
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
	
	public int getPortNum(){
		return portNum;
	}
	
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
	
	public void sendStartLocations(Location[] startLocations){
		try
		{
			System.out.println("sending started...");
			output.writeBytes("start_location");
			output.writeByte(0);
			for(Location loc : startLocations){
				output.writeDouble(loc.getX());
				output.writeDouble(loc.getY());
			}
			System.out.println("sending done...");
		}
		catch (IOException e)
		{
			System.out.println("Caught IOException sending start locations");
			System.exit(-1);
		}
	}
		
	public void readMessage(){
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
					System.out.println("Ship " + i + " is at x: " + input.readDouble() 
							+ " y: " + input.readDouble());
				}
			}
		}
		catch (IOException ioe)
		{
			System.out.println("ERROR: receiving string from socket");
			System.exit(8);
		}

	}
	
}
