package eecs285.proj4.group;

public class TestClient {

	public static void main(String[] args) {
		ClientORServer client = new ClientORServer("141.213.55.73", 8080);
		
		System.out.println(ClientORServer.getIpAddress());
		
		Location[] locations = new Location[6];
		locations[0] = new Location(0,0);
		locations[1] = new Location(1,1);
		locations[2] = new Location(2,2);
		locations[3] = new Location(3,3);
		locations[4] = new Location(4,4);
		locations[5] = new Location(5,5);
		
		client.startClient();
		client.sendStartLocations(locations);
	}

}
