package eecs285.proj4.group;

public class TestClient {

	public static void main(String[] args) {
		ClientORServer client = new ClientORServer("35.2.47.230", 8080);
		
		client.startClient();
	}

}
