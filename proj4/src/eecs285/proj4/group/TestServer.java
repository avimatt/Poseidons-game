package eecs285.proj4.group;

public class TestServer {

	public static void main(String[] args) {
		ClientORServer server = new ClientORServer("35.2.47.230", 8080);
		
		server.startServer();
	}

}
