import java.awt.Point;
import java.net.Socket;

public class Player {
 private String playerName;
 private int id; 
 private boolean hasFlag;
 private Socket connection;
 private GeoCoordinate location;
 
 public GeoCoordinate getLocation() {
	return location;
}


public void setLocation(GeoCoordinate location) {
	this.location = location;
}


public Socket getConnection() {
	return connection;
}


public void setConnection(Socket connection) {
	this.connection = connection;
}

 
public Player(String playerName, int id, boolean hasFlag, Socket connection, GeoCoordinate location) {
	super();
	this.playerName = playerName;
	this.id = id;
	this.hasFlag = hasFlag;
	this.connection = connection;
	this.location = location;
} 
 
}
