import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AcceptThread extends Thread{
	private Socket socket; 
	public AcceptThread (Socket s) {
		this.socket = s; 
	}
	public void run() {
		System.out.println("Hallo!");
		//Create Input&Outputstreams for the connection
        
		try {
			InputStream inputToServer = socket.getInputStream();
	        OutputStream outputFromServer = socket.getOutputStream();
	        Scanner scanner = new Scanner(inputToServer, "UTF-8");
	        PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

	        serverPrintOut.println("Hello World! Enter Peace to exit.");

	        //Have the server take input from the client and echo it back
	        //This should be placed in a loop that listens for a terminator text e.g. bye
	        boolean done = false;
	        Game currentGame = null; 
	        int currentID = 0;
	        while(!done && scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            serverPrintOut.println("Echo from <Your Name Here> Server: " + line);

	            if(line.toLowerCase().trim().equals("game")) {
	                if(currentGame == null) {
	                	currentGame = createGame(scanner,socket);
	                	System.out.println(currentGame);
	                	serverPrintOut.println("You've created a new game");
	                	currentID++;
	                	serverPrintOut.println("You've created a new game");
	                }else {
	                	//serverPrintOut.println("There is already a game running ");
	                	//JOIN GAME 
	                	if(currentGame.isStarted()) {
	                		serverPrintOut.println("The game is already running");
	                	}
	                	joinGame(scanner, currentGame, currentID, socket);
	                	currentID++;
	                }
	            }else if(line.toLowerCase().trim().equals("startgame")) {
	            	currentGame.startGame();
	            }else if(line.toLowerCase().trim().equals("location")){
	            	if(!scanner.hasNextLine()) {
	            		serverPrintOut.println("INVALID_ARGS");
	            	}
	            	GeoCoordinate location = getPointFromString(scanner.nextLine());
	            	Player player = currentGame.getPlayerBySocket(socket);
	            	player.setLocation(location);
	            }
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
        
		
	}
	private Game createGame(Scanner scanner, Socket socket) throws Exception {
		int maxPlayers = 0;
		String playerName = "";
		String creatorTeamColor ="";
		GeoCoordinate blueBaseCoords = null;
		GeoCoordinate redBaseCoords = null;
		GeoCoordinate creatorLocation = null;
	    String args[] = {"maxPlayers", "playerName", "teamColor"};
        for( int i= 0; i< args.length ; i++) {
        	if(!scanner.hasNextLine()){
        		throw new Exception("ARGS_MISSING");
        	}
        	switch (i) {
	        	case 0: 
	        		String line = scanner.nextLine();
	        		maxPlayers = Integer.parseInt(line);
	        		break;
	        	case 1: 
	        		playerName = scanner.nextLine();
	        		break;
	        	case 2: 
	        		creatorTeamColor = scanner.nextLine();
	        		break;
	        	case 3:
	        		blueBaseCoords = getPointFromString(scanner.nextLine());
	        		break;
	        	case 4:
	        		redBaseCoords = getPointFromString(scanner.nextLine());
	        		break;
	        	case 5: 
	        		creatorLocation =  getPointFromString(scanner.nextLine());
        	}
        	
        	
        	
        }
        Player creator = new Player(playerName, 0, false, socket, creatorLocation);
        try{
        	Game game = new Game(maxPlayers, creator, creatorTeamColor, blueBaseCoords, redBaseCoords);
        	System.out.println("GAME_WAS_CREATED");
        	return game;
        }catch (Exception e){
        	throw e;
        }
	}
	private GeoCoordinate getPointFromString(String coords) throws Exception {
		// 5.3234N 3.231E  
		String coordsArray[] = coords.split(" ");
		if(coordsArray.length!= 2) {
			throw new Exception("INVALID_COORDINATES");
		}
		double latitude = Double.parseDouble(coordsArray[0].substring(0, coordsArray[0].length()-1));
		//double lat_direction = 
		//double longitude = Double.parseDouble(coordsArray[1].substring(1, coordsArray[1].length()-1));
		//double long_direction; 
		return null;
		//return new GeoCoordinate(Double.parseDouble(coordsArray[0]), Double.parseDouble(coordsArray[1])) ;
	}
	
	private void joinGame(Scanner scanner, Game game, int currentID , Socket socket) throws Exception {
		String playerName = "";
		String playerTeamColor =""; 
		GeoCoordinate playerLocation;
		if(!scanner.hasNextLine()){
    		throw new Exception("ARGS_MISSING");
    	}else {
    		playerName = scanner.nextLine();
    	}
		
		if(!scanner.hasNextLine()){
    		throw new Exception("ARGS_MISSING");
    	}else {
    		playerTeamColor = scanner.nextLine();
    	}
		
		if(!scanner.hasNextLine()){
    		throw new Exception("ARGS_MISSING");
    	}else {
    		playerLocation = getPointFromString(scanner.nextLine());
    	}
		
		Player player =new Player(playerName,currentID, false, socket, playerLocation);
		try {
			if(playerTeamColor == "red") {
				game.getRedTeam().addPlayer(player);
			}
			if(playerTeamColor == "blue") {
				game.getBlueTeam().addPlayer(player);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
    
}
