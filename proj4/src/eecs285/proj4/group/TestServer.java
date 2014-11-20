package eecs285.proj4.group;

public class TestServer {

	public static void main(String[] args) {
		ClientORServer server = new ClientORServer(ClientORServer.getIpAddress(), 8080);
		
		System.out.println(ClientORServer.getIpAddress());
		
		server.startServer();		
		server.readMessage();
	
	}

}
