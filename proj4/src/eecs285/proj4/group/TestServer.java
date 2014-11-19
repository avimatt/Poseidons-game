package eecs285.proj4.group;

public class TestServer {

	public static void main(String[] args) {
		ClientORServer server = new ClientORServer("10.0.0.29", 8080);
		
		server.startServer();		
		server.readMessage();
	
	}

}
