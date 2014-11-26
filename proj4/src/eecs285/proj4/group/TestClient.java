package eecs285.proj4.group;

import java.util.ArrayList;

import eecs285.proj4.group.Ships.*;

public class TestClient {

	public static void main(String[] args) {
		ClientORServer client = new ClientORServer("10.0.0.14", 8080);
		
		System.out.println(ClientORServer.getIpAddress());
		
		ArrayList<Ship> locations = new ArrayList<Ship>();
		Ship one = new PatrolBoat();
		Ship two = new PatrolBoat();
		Ship three = new Submarine();
		Ship four = new AircraftCarrier();
		Ship five = new Battleship();
		Ship six = new Destroyer();
		
		one.setCurrentLoaction(new Location(0,0));
		two.setCurrentLoaction(new Location(1,1));
		three.setCurrentLoaction(new Location(2,2));
		four.setCurrentLoaction(new Location(3,3));
		five.setCurrentLoaction(new Location(4,4));
		six.setCurrentLoaction(new Location(5,5));
		
		locations.add(one);
		locations.add(two);
		locations.add(three);
		locations.add(four);
		locations.add(five);
		locations.add(six);
		
		client.startClient();
		client.sendStartLocations(locations);
	}

}
