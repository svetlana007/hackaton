import java.net.Socket;

public class Game {

	private int maxPlayers;
	private Player creator;
    public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}


	private GeoCoordinate blueBaseCoords;
    private GeoCoordinate redBaseCoords;
    private Team redTeam;
    public Team getRedTeam() {
		return redTeam;
	}

	public Team getBlueTeam() {
		return blueTeam;
	}


	private Team blueTeam;
    private boolean started; 
    
	public Game(int maxPlayers, Player creator, String creatorTeamColor, GeoCoordinate blueBaseCoords, GeoCoordinate redBaseCoords ) throws Exception {
		try {
			this.blueBaseCoords = blueBaseCoords;
			this.redBaseCoords = redBaseCoords;
			this.blueTeam = new Team(new Flag(null,"IS_IN_BLUE_BASE"),"blue",maxPlayers,blueBaseCoords);
			this.redTeam =  new Team(new Flag(null,"IS_IN_RED_BASE"),"red",maxPlayers, redBaseCoords);
			started = false;
			if( creatorTeamColor.equals("blue")) {
				this.blueTeam.addPlayer(creator);
			}else if( creatorTeamColor.equals("red")) {
				this.redTeam.addPlayer(creator);
			}else {
				throw new Exception("INVALID_TEAM_COLOR");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		
	}
    
	public void startGame() {
		started = true;
		
	}
	public Player getPlayerBySocket(Socket socket){
		Player red = redTeam.getPlayerBySocket(socket);
		Player blue = blueTeam.getPlayerBySocket(socket);
		if(red != null)
			return red;
		if(blue != null)
			return blue;
		
		return null;
	}

}
