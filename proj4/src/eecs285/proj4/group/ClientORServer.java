package eecs285.proj4.group;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

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
			System.exit(7);
		}
	}
	
	public void startClient(){
		try {
			// connect with server
			socket = new Socket(ipAddress, portNum);
			// initialize data streams
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException ioe) {
			// Unable to connect 
			System.exit(10);
		}
	}
	
	
}
